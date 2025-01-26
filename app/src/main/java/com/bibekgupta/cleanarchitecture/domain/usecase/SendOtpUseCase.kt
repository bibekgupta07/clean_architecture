package com.bibekgupta.cleanarchitecture.domain.usecase

import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse
import com.bibekgupta.cleanarchitecture.domain.repository.AuthRepository
import com.bibekgupta.cleanarchitecture.utility.Resource
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(phone: String) : Resource<OtpResponse> {
        return repository.sendOtp(phone)
    }
}