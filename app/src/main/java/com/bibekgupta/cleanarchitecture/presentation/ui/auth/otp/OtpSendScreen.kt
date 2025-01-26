package com.bibekgupta.cleanarchitecture.ui.auth.otp
//
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
//import androidx.compose.runtime.setValue
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavBackStackEntry
//import com.bibekgupta.cleanarchitecture.presentation.coordinator.NavRoutes
//import com.bibekgupta.cleanarchitecture.utility.Resource


//@Composable
//fun OtpSendScreen(
//    coordinator: AppCoordinatorImpl,
//
//) {
//    var phoneNumber by remember { mutableStateOf("") }
//
//
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            label = { Text("Phone Number") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//               //Todo:
//            },
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text("Send OTP")
//        }
//    }
//}


//@Composable
//fun OtpSendScreen(
//    coordinator: AppCoordinatorImpl,
//    viewModel: OtpSendViewModel = hiltViewModel()
//) {
//    var phoneNumber by remember { mutableStateOf("") }
//    val context = LocalContext.current
//    val otpSendState by viewModel.otpSendState.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            label = { Text("Phone Number") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                if (phoneNumber.isNotEmpty()) {
//                    viewModel.sendOtp(phoneNumber)
//                } else {
//                    Toast.makeText(context, "Please enter a phone number", Toast.LENGTH_SHORT).show()
//                }
//            },
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text("Send OTP")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Observe the state and handle loading, success, and error
//        when (otpSendState) {
//            is OtpSendState.Loading -> {
//                CircularProgressIndicator()
//            }
//            is OtpSendState.Success -> {
//                val otpResponse = (otpSendState as OtpSendState.Success).otpResponse
//                Toast.makeText(context, "OTP sent successfully", Toast.LENGTH_SHORT).show()
//                Log.d("OtpSendScreen", "OTP Sent: $otpResponse")
//
//                // Navigate to the OTP verification screen and pass the phone number
//               coordinator.navigateToOtpVerify(phoneNumber)
//            }
//            is OtpSendState.Error -> {
//                val errorMessage = (otpSendState as OtpSendState.Error).message
//                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//            else -> {
//                // Default state, do nothing
//            }
//        }
//    }
//}



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinator
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OtpSendScreen(
    coordinator: AppCoordinator,
    viewModel: OtpSendViewModel = hiltViewModel()
) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    val otpSendState by viewModel.otpSendState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.sendOtp(phoneNumber.text)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (otpSendState) {
            is OtpSendViewModel.OtpSendState.Loading -> CircularProgressIndicator()
            is OtpSendViewModel.OtpSendState.Success -> {
                coordinator.navigateToOtpVerify(phoneNumber.text) // Navigate to OtpVerifyScreen
            }
            is OtpSendViewModel.OtpSendState.Error -> {
                Text(
                    text = (otpSendState as OtpSendViewModel.OtpSendState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
