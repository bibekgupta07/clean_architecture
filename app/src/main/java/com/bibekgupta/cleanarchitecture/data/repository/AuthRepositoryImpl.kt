package com.bibekgupta.cleanarchitecture.data.repository

import android.util.Log
import com.bibekgupta.cleanarchitecture.data.local.PreferenceManager
import com.bibekgupta.cleanarchitecture.data.mapper.toDomainModel
import com.bibekgupta.cleanarchitecture.data.model.login.LoginRequest
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpSendRequest
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpVerifyRequest
import com.bibekgupta.cleanarchitecture.data.model.register.RegisterRequest
import com.bibekgupta.cleanarchitecture.data.remote.ApiService
import com.bibekgupta.cleanarchitecture.domain.model.login.LoginResponse
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse
import com.bibekgupta.cleanarchitecture.domain.model.register.RegisterResponse
import com.bibekgupta.cleanarchitecture.domain.repository.AuthRepository
import com.bibekgupta.cleanarchitecture.utility.Resource
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferenceManager: PreferenceManager
) : AuthRepository {

    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.success) {
                val loginResponse = response.toDomainModel()
                preferenceManager.saveToken(loginResponse.token)
                Resource.Success(loginResponse)
            } else {
                Resource.Error(response.error ?: response.message ?: "An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred during login")
        }
    }

    override suspend fun register(request: RegisterRequest): Resource<RegisterResponse> {
        return try {
            // Prepare RequestBody for text fields
            val nameBody = request.name.toRequestBody("text/plain".toMediaTypeOrNull())
            val phoneBody = request.phone.toRequestBody("text/plain".toMediaTypeOrNull())
            val emailBody = request.email.toRequestBody("text/plain".toMediaTypeOrNull())
            val passwordBody = request.password.toRequestBody("text/plain".toMediaTypeOrNull())


            // Call the API service
            val response = apiService.register(
                name = nameBody,
                phone = phoneBody,
                email = emailBody,
                password = passwordBody,
                profilePic = request.profilePic // This should remain as MultipartBody.Part
            )

            Log.d("RegisterRequest", "Request: $request")
            Log.d("RegisterResponse", "Response: $response")

            if (response.success == "true") {
                Resource.Success(response.toDomainModel())
            } else {
                Resource.Error(response.error ?: response.message ?: "An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred during registration")
        }
    }

    override suspend fun sendOtp(phone: String): Resource<OtpResponse> {
        return try{
            val response = apiService.sendOtp(OtpSendRequest(phone))
            if (response.success) {
                Resource.Success(response.toDomainModel())
            } else {
                Resource.Error(response.error ?: response.message ?: "An unknown error occurred")
        }
    }catch (e: Exception){
        Resource.Error(e.message ?: "An error occurred during otp")
        }
    }

    override suspend fun verifyOtp(
        phone: String,
        otp: String
    ): Resource<OtpResponse> {
        return try {
            val response = apiService.verifyOtp(OtpVerifyRequest(phone, otp))
            if (response.success) {
                Resource.Success(response.toDomainModel())
            } else {
                Resource.Error(response.error ?: response.message ?: "An unknown error occurred")
            }
        }catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred during otp")
        }
    }
}

