package com.bibekgupta.cleanarchitecture.domain.usecase

import android.util.Log
import com.bibekgupta.cleanarchitecture.domain.model.login.LoginResponse
import com.bibekgupta.cleanarchitecture.domain.repository.AuthRepository
import com.bibekgupta.cleanarchitecture.utility.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Resource<LoginResponse> {
        Log.d("LoginRequest", "Username: $username, Password: $password")
        return repository.login(username, password)
    }
}