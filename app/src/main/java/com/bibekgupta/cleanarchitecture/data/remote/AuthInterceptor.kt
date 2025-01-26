package com.bibekgupta.cleanarchitecture.data.remote

import com.bibekgupta.cleanarchitecture.data.local.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val preferenceManager: PreferenceManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferenceManager.getToken()
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")

        }
        return chain.proceed(requestBuilder.build())
    }
}