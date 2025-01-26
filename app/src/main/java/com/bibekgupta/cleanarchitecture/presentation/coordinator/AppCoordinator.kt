package com.bibekgupta.cleanarchitecture.presentation.coordinator

interface AppCoordinator {
    fun navigateToAuthFlow()
    fun navigateToMainFlow()
    fun navigateToLogin()
    fun navigateToRegister()
    fun navigateToForgotPassword()
    fun navigateToOtpSend()
    fun navigateToOtpVerify(phoneNumber: String)
    fun navigateToHome()
    fun navigateToSettings()
    fun navigateToAdd()
    fun navigateTo(route: String, inclusivePopUpTo: String? = null)
    fun navigateBack(): Boolean
    fun navigateUp(): Boolean
}



