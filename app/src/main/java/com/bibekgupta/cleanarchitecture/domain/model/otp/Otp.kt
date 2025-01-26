package com.bibekgupta.cleanarchitecture.domain.model.otp

data class OtpResponse(
    val success: Boolean,
    val message: String,
    val data: String?,
    val error: String?

)