//package com.bibekgupta.cleanarchitecture.ui.auth.otp
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.bibekgupta.cleanarchitecture.domain.usecase.VerifyOtpUseCase
//import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse
//import com.bibekgupta.cleanarchitecture.ui.auth.otp.VerifyOtpState.*
//import com.bibekgupta.cleanarchitecture.utility.Resource
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class OtpVerifyViewModel @Inject constructor(
//    private val verifyOtpUseCase: VerifyOtpUseCase
//) : ViewModel() {
//
//    private val _verifyOtpState = MutableStateFlow<VerifyOtpState>(VerifyOtpState.Idle)
//    val verifyOtpState: StateFlow<VerifyOtpState> get() = _verifyOtpState
//
//    fun verifyOtp(phone: String, otp: String) {
//        viewModelScope.launch {
//            _verifyOtpState.value = VerifyOtpState.Loading
//            when (val result = verifyOtpUseCase(phone, otp)) {
//                is Resource.Success -> {
//                    _verifyOtpState.value = Success(result.data)
//                }
//                is Resource.Error -> {
//                    _verifyOtpState.value = Error(result.message ?: "An unknown error occurred")
//                }
//
//                is Resource.Loading -> TODO()
//            }
//        }
//    }
//}
//
//sealed class VerifyOtpState {
//    object Idle : VerifyOtpState()
//    object Loading : VerifyOtpState()
//    data class Success(val otpResponse: OtpResponse?) : VerifyOtpState()
//    data class Error(val message: String) : VerifyOtpState()
//}

package com.bibekgupta.cleanarchitecture.presentation.auth.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibekgupta.cleanarchitecture.domain.usecase.VerifyOtpUseCase
import com.bibekgupta.cleanarchitecture.presentation.auth.otp.OtpVerifyViewModel.OtpVerifyState.*
import com.bibekgupta.cleanarchitecture.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerifyViewModel @Inject constructor(
    private val verifyOtpUseCase: VerifyOtpUseCase
) : ViewModel() {

    sealed class OtpVerifyState {
        object Loading : OtpVerifyState()
        object Success : OtpVerifyState()
        data class Error(val message: String) : OtpVerifyState()
    }

    private val _otpVerifyState = MutableStateFlow<OtpVerifyState>(OtpVerifyState.Loading)
    val otpVerifyState: StateFlow<OtpVerifyState> = _otpVerifyState

    fun verifyOtp(phoneNumber: String, otp: String) {
        viewModelScope.launch {
            _otpVerifyState.value = OtpVerifyState.Loading
            when (val result = verifyOtpUseCase(phoneNumber, otp)) {
                is Resource.Success -> _otpVerifyState.value = OtpVerifyState.Success
                is Resource.Error -> _otpVerifyState.value =
                    Error(result.message ?: "Error verifying OTP")

                is Resource.Loading -> TODO()
            }
        }
    }
}
