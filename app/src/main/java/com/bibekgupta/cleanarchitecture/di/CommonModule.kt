package com.bibekgupta.cleanarchitecture.di

import com.bibekgupta.cleanarchitecture.domain.usecase.common.ImagePickerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    fun provideImagePickerUseCase(): ImagePickerUseCase {
        return ImagePickerUseCase()
    }
}