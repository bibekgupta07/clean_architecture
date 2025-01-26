package com.bibekgupta.cleanarchitecture.domain.model.register

import java.util.Date

data class User(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profilePic: String,
    val createdAt: Date
)

data class RegisterResponse(
    val user: User
)