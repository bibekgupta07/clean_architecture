package com.bibekgupta.cleanarchitecture.data.mapper

import com.bibekgupta.cleanarchitecture.data.model.otp.OtpSendResponseDto
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpVerifyResponseDto
import com.bibekgupta.cleanarchitecture.data.model.otp.UserDto
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpSendResponse
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpVerifyResponse
import com.bibekgupta.cleanarchitecture.domain.model.otp.User

fun OtpSendResponseDto.toDomainModel(): OtpSendResponse {
    return OtpSendResponse(
        success = success,
        message = message,
        data = data,
        error = error
    )
}


fun UserDto.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        phone = phone,
        email = email,
        profilePic = profile_pic ?: "",
        createdAt = created_at
    )
}


fun OtpVerifyResponseDto.toDomainModel(): OtpVerifyResponse {
    val data = this.data.firstOrNull() ?: throw Exception("No user data found in response")
    return OtpVerifyResponse(
        success = success,
        message = message,
        error = error,
        user = data.user.toDomainModel(),
        token = data.token
    )
}
