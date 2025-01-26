package com.bibekgupta.cleanarchitecture.domain.model.otp

data class OtpSendResponse(
    val success: Boolean,
    val message: String,
    val data: String?,
    val error: String?

)

data class User(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profilePic: String,
    val createdAt: String
)

data class OtpVerifyResponse(
    val success: Boolean,
    val message: String,
    val error: String?,
    val user: User,
    val token: String
)