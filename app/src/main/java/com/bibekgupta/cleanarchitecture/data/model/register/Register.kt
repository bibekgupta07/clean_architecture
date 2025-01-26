package com.bibekgupta.cleanarchitecture.data.model.register


import okhttp3.MultipartBody
import java.util.Date

data class RegisterRequest(
    val name: String,
    val phone: String,
    val email: String,
    val password: String,
    val profilePic: MultipartBody.Part
)

data class RegisterResponseDto(
    val success: String,
    val message: String,
    val error: String?,
    val data: UserDataDto?
)

data class UserDataDto(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profile_pic: String,
    val created_at: Date
)
