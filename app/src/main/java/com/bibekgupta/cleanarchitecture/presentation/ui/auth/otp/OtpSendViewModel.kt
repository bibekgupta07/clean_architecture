package com.bibekgupta.cleanarchitecture.ui.auth.otp

import androidx.lifecycle.ViewModel

import com.bibekgupta.cleanarchitecture.domain.usecase.SendOtpUseCase
import com.bibekgupta.cleanarchitecture.utility.Resource
import androidx.lifecycle.viewModelScope
import com.bibekgupta.cleanarchitecture.ui.auth.otp.OtpSendViewModel.OtpSendState.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class OtpSendViewModel @Inject constructor(
    private val sendOtpUseCase: SendOtpUseCase
) : ViewModel() {

    sealed class OtpSendState {
        object Loading : OtpSendState()
        object Success : OtpSendState()
        data class Error(val message: String) : OtpSendState()
    }

    private val _otpSendState = MutableStateFlow<OtpSendState>(OtpSendState.Loading)
    val otpSendState: StateFlow<OtpSendState> = _otpSendState

    fun sendOtp(phoneNumber: String) {
        viewModelScope.launch {
            _otpSendState.value = OtpSendState.Loading
            when (val result = sendOtpUseCase(phoneNumber)) {
                is Resource.Success -> _otpSendState.value = OtpSendState.Success
                is Resource.Error -> _otpSendState.value =
                    Error(result.message ?: "Error sending OTP")

                is Resource.Loading -> TODO()
            }
        }
    }
}
