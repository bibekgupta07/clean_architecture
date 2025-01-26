package com.bibekgupta.cleanarchitecture.data.model.otp

data class OtpSendRequest(
    val phone: String
)


data class OtpVerifyRequest(
    val phone: String,
    val otp: String
)


data class OtpSendResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val error: String
)


data class OtpVerifyResponseDto(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: List<UserDataDto>
)

data class UserDataDto(
    val user: UserDto,
    val token: String
)

data class UserDto(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profile_pic: String?,
    val created_at: String
)