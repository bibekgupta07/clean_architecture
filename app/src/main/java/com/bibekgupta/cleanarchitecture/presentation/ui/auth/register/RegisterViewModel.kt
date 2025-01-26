package com.bibekgupta.cleanarchitecture.ui.auth.register

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibekgupta.cleanarchitecture.data.model.register.RegisterRequest
import com.bibekgupta.cleanarchitecture.domain.usecase.RegisterUseCase
import com.bibekgupta.cleanarchitecture.domain.usecase.common.ImagePickerUseCase
import com.bibekgupta.cleanarchitecture.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import kotlin.io.extension
import kotlin.text.isNullOrEmpty
import kotlin.text.toLowerCase

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val imagePickerUseCase: ImagePickerUseCase
) : ViewModel() {

    // UI states for registration
    sealed class RegisterUiState {
        object Idle : RegisterUiState()
        object Loading : RegisterUiState()
        data class Success(val message: String) : RegisterUiState()
        data class Error(val message: String) : RegisterUiState()
    }

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState

    // Form fields
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    // Profile picture URI
    private val _profilePicUri = MutableStateFlow<Uri?>(null)
    val profilePicUri: StateFlow<Uri?> = _profilePicUri

    // Update field values
    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun onPhoneChanged(newPhone: String) {
        _phone.value = newPhone
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onProfilePicChanged(newUri: Uri?) {
        _profilePicUri.value = newUri
        Log.d("RegisterViewModel", "Profile Pic URI set to: $newUri")
    }

    // Handle user registration
    fun registerUser(context: Context) {
        val profileUri = _profilePicUri.value
        if (profileUri == null) {
            _uiState.value = RegisterUiState.Error("Profile picture is required")
            return
        }

        // Get file path or cache file from URI
        val filePath = imagePickerUseCase.getRealPathFromURI(profileUri, context)
        Log.d("RegisterViewModel", "Profile URI: $profileUri, File Path: $filePath")


        if (filePath.isNullOrEmpty()) {
            _uiState.value = RegisterUiState.Error("Invalid profile picture URI")
            return
        }

        val file = File(filePath)
        if (!file.exists() || !file.isFile) {
            _uiState.value = RegisterUiState.Error("Invalid profile picture file")
            return
        }

        Log.d("RegisterViewModel", "Profile picture file: ${file.absolutePath}, MIME type: image/*")



        val mimeType = when (file.extension.toLowerCase()) {
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            else -> "application/octet-stream" // Fallback for unknown types
        }

        val profilePicPart = MultipartBody.Part.createFormData(
            "profile_pic",
            file.name,
            file.asRequestBody(mimeType.toMediaTypeOrNull())
        )

        Log.d("RegisterViewModel", "File name: ${file.name}, File extension: ${file.extension}, MIME type: $mimeType")

        // Prepare registration request
        val request = RegisterRequest(
            name = _name.value,
            phone = _phone.value,
            email = _email.value,
            password = _password.value,
            profilePic = profilePicPart
        )

        // Make API call
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            Log.d("RegisterViewModel", "Register request: $request")
            when (val result = registerUseCase(request)) {
                is Resource.Success -> _uiState.value =
                    RegisterUiState.Success("Registered successfully!")
                is Resource.Error -> _uiState.value =
                    RegisterUiState.Error(result.message ?: "An unknown error occurred")
                else -> Unit
            }
        }
    }
}
