package com.bibekgupta.cleanarchitecture.data.remote

import android.provider.ContactsContract.CommonDataKinds.Email
import com.bibekgupta.cleanarchitecture.data.model.login.LoginRequest
import com.bibekgupta.cleanarchitecture.data.model.login.LoginResponseDto
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpResponseDto
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpSendRequest
import com.bibekgupta.cleanarchitecture.data.model.otp.OtpVerifyRequest
import com.bibekgupta.cleanarchitecture.data.model.register.RegisterRequest
import com.bibekgupta.cleanarchitecture.data.model.register.RegisterResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("/user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponseDto

    @Multipart
    @POST("/user/register")
    suspend fun register(
        @Part("name") name: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part profilePic: MultipartBody.Part
    ): RegisterResponseDto

    @POST("/user/login/send-otp")
    suspend fun sendOtp(@Body request: OtpSendRequest): OtpResponseDto

    @POST("/user/login/verify-otp")
    suspend fun verifyOtp(@Body request: OtpVerifyRequest): OtpResponseDto

}