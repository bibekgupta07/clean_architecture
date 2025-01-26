package com.bibekgupta.cleanarchitecture.data.model.otp

data class OtpSendRequest(
    val phone: String
)


data class OtpVerifyRequest(
    val phone: String,
    val otp: String
)


data class OtpResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val error: String
)