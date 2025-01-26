package com.bibekgupta.cleanarchitecture.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
import com.bibekgupta.cleanarchitecture.ui.auth.login.LoginViewModel
import com.bibekgupta.cleanarchitecture.utility.Resource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue




@Composable
fun LoginScreen(
    coordinator: AppCoordinatorImpl,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.collectAsState().value
    val navigateToHome = viewModel.navigateToHome.collectAsState().value

    // Navigate to home on successful login
    if (navigateToHome) {
        coordinator.navigateToHome() // Call your navigation method
        // Reset navigation state
        viewModel.resetNavigation()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (loginState) {
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    Text(text = "Welcome, ${loginState.data?.user?.name}!")
                }
                is Resource.Error -> {
                    Text(text = loginState.message ?: "An error occurred")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.login(username, password) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { coordinator.navigateToRegister() }) {
                Text("Register")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { coordinator.navigateToForgotPassword() }) {
                Text("Forgot Password")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { coordinator.navigateToOtpSend() }) {
                Text("Login With OTP")
            }
        }
    }




}