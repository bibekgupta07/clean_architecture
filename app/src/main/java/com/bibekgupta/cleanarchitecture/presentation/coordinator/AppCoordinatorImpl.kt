//package com.bibekgupta.cleanarchitecture.presentation.coordinator
//
//
//import androidx.navigation.NavHostController
//
//
//class AppCoordinatorImpl(
//    val navController: NavHostController
//) : AppCoordinator {
//
//    override fun navigateTo(route: String) {
//        navController.navigate(route) {
//            launchSingleTop = true
//        }
//    }
//
//    override fun navigateToAuthFlow() = navigateTo(NavRoutes.AUTH_FLOW)
//    override fun navigateToMainFlow() = navigateTo(NavRoutes.MAIN_FLOW)
//    override fun navigateToLogin() = navigateTo(NavRoutes.LOGIN)
//    override fun navigateToRegister() = navigateTo(NavRoutes.REGISTER)
//    override fun navigateToForgotPassword() = navigateTo(NavRoutes.FORGOT_PASSWORD)
//    override fun navigateToHome() = navigateTo(NavRoutes.HOME)
//    override fun navigateToSettings() = navigateTo(NavRoutes.SETTINGS)
//    override fun navigateToAdd() = navigateTo(NavRoutes.ADD)
//    override fun navigateToOtpSend() = navigateTo(NavRoutes.OTP_SEND)
//    override fun navigateToOtpVerify() = navigateTo(NavRoutes.OTP_VERIFY)
//    override fun navigateBack() = navController.popBackStack()
//    override fun navigateUp(): Boolean = navController.navigateUp()
//}


package com.bibekgupta.cleanarchitecture.presentation.coordinator

import androidx.navigation.NavHostController

class AppCoordinatorImpl(
    val navController: NavHostController
) : AppCoordinator {

    override fun navigateTo(route: String, inclusivePopUpTo: String?) {
        navController.navigate(route) {
            launchSingleTop = true
            inclusivePopUpTo?.let {
                popUpTo(it) { inclusive = true }
            }
        }
    }

    override fun navigateToAuthFlow() = navigateTo(NavRoutes.AUTH_FLOW, inclusivePopUpTo = NavRoutes.AUTH_FLOW)
    override fun navigateToMainFlow() = navigateTo(NavRoutes.MAIN_FLOW, inclusivePopUpTo = NavRoutes.AUTH_FLOW)
    override fun navigateToLogin() = navigateTo(NavRoutes.LOGIN)
    override fun navigateToRegister() = navigateTo(NavRoutes.REGISTER)
    override fun navigateToForgotPassword() = navigateTo(NavRoutes.FORGOT_PASSWORD)
    override fun navigateToHome() = navigateTo(NavRoutes.HOME)
    override fun navigateToSettings() = navigateTo(NavRoutes.SETTINGS)
    override fun navigateToAdd() = navigateTo(NavRoutes.ADD)
    override fun navigateToOtpSend() = navigateTo(NavRoutes.OTP_SEND)
    override fun navigateToOtpVerify() = navigateTo(NavRoutes.OTP_VERIFY)
    override fun navigateBack() = navController.popBackStack()
    override fun navigateUp(): Boolean = navController.navigateUp()
}
