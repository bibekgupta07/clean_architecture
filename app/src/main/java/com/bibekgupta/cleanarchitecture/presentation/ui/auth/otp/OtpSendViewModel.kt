package com.bibekgupta.cleanarchitecture.ui.auth.otp

import androidx.lifecycle.ViewModel
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse
import com.bibekgupta.cleanarchitecture.domain.usecase.SendOtpUseCase
import com.bibekgupta.cleanarchitecture.utility.Resource
import androidx.lifecycle.viewModelScope
import com.bibekgupta.cleanarchitecture.ui.auth.otp.OtpSendState.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class OtpSendViewModel @Inject constructor(
    private val sendOtpUseCase: SendOtpUseCase
) : ViewModel() {

    private val _otpSendState = MutableStateFlow<OtpSendState>(OtpSendState.Idle)
    val otpSendState: StateFlow<OtpSendState> = _otpSendState

    fun sendOtp(phone: String) {
        viewModelScope.launch {
            _otpSendState.value = OtpSendState.Loading
            when (val result = sendOtpUseCase(phone)) {
                is Resource.Success -> {
                    _otpSendState.value = Success(result.data)
                }
                is Resource.Error -> {
                    _otpSendState.value = Error(result.message ?: "An unknown error occurred")
                }

                is Resource.Loading -> TODO()
            }
        }
    }
}

sealed class OtpSendState {
    object Idle : OtpSendState()
    object Loading : OtpSendState()
    data class Success(val otpResponse: OtpResponse?) : OtpSendState()
    data class Error(val message: String) : OtpSendState()
}
