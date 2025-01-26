package com.bibekgupta.cleanarchitecture.domain.usecase

import android.util.Log
import com.bibekgupta.cleanarchitecture.data.model.register.RegisterRequest
import com.bibekgupta.cleanarchitecture.domain.model.register.RegisterResponse
import com.bibekgupta.cleanarchitecture.domain.model.register.User
import com.bibekgupta.cleanarchitecture.domain.repository.AuthRepository
import com.bibekgupta.cleanarchitecture.utility.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): Resource<RegisterResponse> {
        return repository.register(request)
    }
}