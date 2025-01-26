package com.bibekgupta.cleanarchitecture.di

import com.bibekgupta.cleanarchitecture.data.local.PreferenceManager
import com.bibekgupta.cleanarchitecture.data.remote.ApiService
import com.bibekgupta.cleanarchitecture.data.remote.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor


//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthInterceptor(preferenceManager: PreferenceManager): AuthInterceptor {
//        return AuthInterceptor(preferenceManager)
//    }
//
//    @Provides
//    @Singleton
//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return loggingInterceptor
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(authInterceptor: AuthInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)  // Add your custom AuthInterceptor for authorization
//            .addInterceptor(loggingInterceptor)  // Add HttpLoggingInterceptor for logging
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://dev-api.lnp.olive.media")  // Your base URL
//            .client(okHttpClient)  // Add the OkHttpClient with interceptors
//            .addConverterFactory(GsonConverterFactory.create())  // Gson converter for parsing JSON
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//}




@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dev-api.lnp.olive.media"

    @Provides
    @Singleton
    fun provideAuthInterceptor(preferenceManager: PreferenceManager): AuthInterceptor {
        return AuthInterceptor(preferenceManager)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            // Avoid logging sensitive information
            if (!message.contains("Authorization")) {
                println(message)
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // Add your custom AuthInterceptor for authorization
            .addInterceptor(loggingInterceptor) // Add HttpLoggingInterceptor for logging
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Use a constant for the base URL to avoid hardcoding
            .client(okHttpClient) // Add the OkHttpClient with interceptors
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter for parsing JSON
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
