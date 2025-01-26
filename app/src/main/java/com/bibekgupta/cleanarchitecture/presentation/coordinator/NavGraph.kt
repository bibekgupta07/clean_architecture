package com.bibekgupta.cleanarchitecture.presentation.coordinator


import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bibekgupta.cleanarchitecture.presentation.auth.ForgotPasswordScreen
import com.bibekgupta.cleanarchitecture.presentation.auth.LoginScreen
import com.bibekgupta.cleanarchitecture.presentation.auth.RegisterScreen
import com.bibekgupta.cleanarchitecture.presentation.main.AddScreen
import com.bibekgupta.cleanarchitecture.presentation.main.HomeScreen
import com.bibekgupta.cleanarchitecture.presentation.main.SettingsScreen
import com.bibekgupta.cleanarchitecture.presentation.splash.SplashScreen
import com.bibekgupta.cleanarchitecture.ui.auth.otp.OtpSendScreen
import com.bibekgupta.cleanarchitecture.ui.auth.otp.OtpVerifyScreen


@Composable
fun NavGraph(coordinator: AppCoordinatorImpl) {
    NavHost(
        navController = coordinator.navController,
        startDestination = NavRoutes.SPLASH
    ) {
        composable(NavRoutes.SPLASH) {
            SplashScreen(coordinator)
        }

        navigation(
            startDestination = NavRoutes.LOGIN,
            route = NavRoutes.AUTH_FLOW
        ) {
            composable(NavRoutes.LOGIN) {
                LoginScreen(coordinator)
            }
            composable(NavRoutes.REGISTER) {
                RegisterScreen(coordinator)
            }
            composable(NavRoutes.FORGOT_PASSWORD) {
                ForgotPasswordScreen(coordinator)
            }
            composable(NavRoutes.OTP_SEND) {
                OtpSendScreen(coordinator)
            }
//            composable(
//                NavRoutes.OTP_VERIFY,
//                arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
//                ) { backStackEntry ->
//                val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
//                OtpVerifyScreen(coordinator, phoneNumber.toString())
//            }

            composable(
                route = "otp_verify/{phoneNumber}",
                arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType })
            ) { backStackEntry ->
                val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
                OtpVerifyScreen(coordinator, phoneNumber)
            }

        }

            navigation(
                startDestination = NavRoutes.HOME,
                route = NavRoutes.MAIN_FLOW
            ) {
                // Home Screen
                composable(NavRoutes.HOME) {
                    MainScreenWithBottomBar(coordinator) {
                        HomeScreen(coordinator)
                    }
                }
                // Detail Screen, nested under Home
//            composable("${NavRoutes.HOME}/{itemId}") { backStackEntry ->
//                val itemId = backStackEntry.arguments?.getString("itemId")
//                MainScreenWithBottomBar(coordinator) {
//                    // Pass the itemId to the DetailScreen
//                    HomeDetailScreen(coordinator, itemId)
//                }
//            }


                // Profile Screen
                composable(NavRoutes.SETTINGS) {
                    MainScreenWithBottomBar(coordinator) {
                        SettingsScreen(coordinator)
                    }
                }

                // Add Screen
                composable(NavRoutes.ADD) {
                    MainScreenWithBottomBar(coordinator) {
                        AddScreen(coordinator)
                    }
                }

            }
        }
}


