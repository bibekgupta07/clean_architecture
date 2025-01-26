package com.bibekgupta.cleanarchitecture.data.model.login

data class LoginRequest(
    val username: String,
    val password: String
)

data class UserDto(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profile_pic: String,
    val created_at: String
)

data class LoginResponseDto(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: List<DataDto>
)

data class DataDto(
    val user: UserDto,
    val token: String
)
