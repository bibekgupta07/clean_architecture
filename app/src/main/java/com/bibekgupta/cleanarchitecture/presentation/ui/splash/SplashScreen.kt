package com.bibekgupta.cleanarchitecture.presentation.splash


import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl

@Composable
fun SplashScreen(coordinator: AppCoordinatorImpl) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        coordinator.navigateToAuthFlow()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Splash Screen")
        Log.d(TAG, "SplashScreen: ")
    }
}
