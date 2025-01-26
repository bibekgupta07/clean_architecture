package com.bibekgupta.cleanarchitecture.domain.repository

import com.bibekgupta.cleanarchitecture.data.model.register.RegisterRequest
import com.bibekgupta.cleanarchitecture.domain.model.login.LoginResponse
import com.bibekgupta.cleanarchitecture.domain.model.otp.OtpResponse
import com.bibekgupta.cleanarchitecture.domain.model.register.RegisterResponse
import com.bibekgupta.cleanarchitecture.domain.model.register.User
import com.bibekgupta.cleanarchitecture.utility.Resource

interface AuthRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
    suspend fun register(request: RegisterRequest): Resource<RegisterResponse>
    suspend fun sendOtp(phone: String): Resource<OtpResponse>
    suspend fun verifyOtp(phone: String, otp: String): Resource<OtpResponse>
}