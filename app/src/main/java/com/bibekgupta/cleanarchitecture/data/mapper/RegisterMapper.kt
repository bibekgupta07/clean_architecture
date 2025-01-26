package com.bibekgupta.cleanarchitecture.data.mapper

import com.bibekgupta.cleanarchitecture.data.model.register.RegisterResponseDto
import com.bibekgupta.cleanarchitecture.data.model.register.UserDataDto
import com.bibekgupta.cleanarchitecture.domain.model.register.RegisterResponse
import com.bibekgupta.cleanarchitecture.domain.model.register.User

fun UserDataDto.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        phone = phone,
        email = email,
        profilePic = profile_pic,
        createdAt = created_at
    )
}

fun RegisterResponseDto.toDomainModel(): RegisterResponse {
    val data = this.data ?: throw Exception("Invalid response format")
    return RegisterResponse(
        user = data.toDomainModel() // Correct function call
    )
}