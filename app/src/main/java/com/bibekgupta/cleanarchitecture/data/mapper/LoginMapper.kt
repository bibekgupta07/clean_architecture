package com.bibekgupta.cleanarchitecture.data.mapper

import com.bibekgupta.cleanarchitecture.data.model.login.LoginResponseDto
import com.bibekgupta.cleanarchitecture.data.model.login.UserDto
import com.bibekgupta.cleanarchitecture.domain.model.login.LoginResponse
import com.bibekgupta.cleanarchitecture.domain.model.login.User


fun UserDto.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        phone = phone,
        email = email,
        profilePic = profile_pic,
        createdAt = created_at
    )
}

fun LoginResponseDto.toDomainModel(): LoginResponse {
    val data = this.data.firstOrNull() ?: throw Exception("Invalid response format")
    return LoginResponse(
        user = data.user.toDomainModel(),
        token = data.token
    )
}
