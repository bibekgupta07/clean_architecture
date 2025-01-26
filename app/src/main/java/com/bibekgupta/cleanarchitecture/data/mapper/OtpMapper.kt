package com.bibekgupta.cleanarchitecture.data.mapper

import com.bibekgupta.cleanarchitecture.data.model.otp.OtpResponseDto
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse

fun OtpResponseDto.toDomainModel(): OtpResponse {
    return OtpResponse(
        success = success,
        message = message,
        data = data,
        error = error
    )
}