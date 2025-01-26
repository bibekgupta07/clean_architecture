package com.bibekgupta.cleanarchitecture.domain.model.login

data class User(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val profilePic: String,
    val createdAt: String
)

data class LoginResponse(
    val user: User,
    val token: String
)
