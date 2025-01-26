package com.bibekgupta.cleanarchitecture.di


import com.bibekgupta.cleanarchitecture.data.repository.AuthRepositoryImpl
import com.bibekgupta.cleanarchitecture.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
