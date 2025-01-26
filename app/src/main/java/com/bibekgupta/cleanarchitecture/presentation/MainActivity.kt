package com.bibekgupta.cleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
import com.bibekgupta.cleanarchitecture.presentation.coordinator.NavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                val navController = rememberNavController()
                val coordinator = AppCoordinatorImpl(navController)
                NavGraph(coordinator)
        }
    }
}

