////package com.bibekgupta.cleanarchitecture.ui.auth.otp
////
////import android.util.Log
////import android.widget.Toast
////import androidx.compose.runtime.Composable
////import androidx.hilt.navigation.compose.hiltViewModel
////import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinator
////import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.unit.dp
////import androidx.compose.runtime.setValue
////import androidx.compose.runtime.getValue
////import androidx.compose.ui.platform.LocalContext
////import androidx.navigation.NavBackStackEntry
////import com.bibekgupta.cleanarchitecture.utility.Resource
//
//
////@Composable
////fun OtpVerifyScreen(
////    coordinator: AppCoordinatorImpl,
////    phoneNumber: String
////
////) {
////
////    var otp by remember { mutableStateOf("") }
////
////
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .padding(16.dp),
////        verticalArrangement = Arrangement.Center,
////        horizontalAlignment = Alignment.CenterHorizontally
////    ) {
////        Text(
////            text = "Verify OTP for ",
////            style = MaterialTheme.typography.headlineSmall
////        )
////        Spacer(modifier = Modifier.height(16.dp))
////        TextField(
////            value = otp,
////            onValueChange = { otp = it },
////            label = { Text("Enter OTP") },
////            modifier = Modifier.fillMaxWidth()
////        )
////        Spacer(modifier = Modifier.height(16.dp))
////        Button(
////            onClick = {
////               // Todo:
////            },
////            modifier = Modifier.padding(top = 8.dp)
////        ) {
////            Text("Verify OTP")
////        }
////    }
////}
//
//
//package com.bibekgupta.cleanarchitecture.ui.auth.otp
//
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.bibekgupta.cleanarchitecture.ui.auth.otp.VerifyOtpState.*
//
//
//
//@Composable
//fun OtpVerifyScreen(
//    coordinator: AppCoordinatorImpl,
//    phoneNumber: String,
//    viewModel: OtpVerifyViewModel = hiltViewModel()
//) {
//    var otp by remember { mutableStateOf("") }
//    val context = LocalContext.current
//    val verifyOtpState by viewModel.verifyOtpState.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Verify OTP for $phoneNumber",
//            style = MaterialTheme.typography.headlineSmall
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        TextField(
//            value = otp,
//            onValueChange = { otp = it },
//            label = { Text("Enter OTP") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = {
//                if (otp.isNotEmpty()) {
//                    viewModel.verifyOtp(phoneNumber, otp)
//                } else {
//                    Toast.makeText(context, "Please enter the OTP", Toast.LENGTH_SHORT).show()
//                }
//            },
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text("Verify OTP")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // State handling
//        when (verifyOtpState) {
//            VerifyOtpState.Loading -> CircularProgressIndicator()
//            is VerifyOtpState.Success -> {
//                Toast.makeText(context, (verifyOtpState as VerifyOtpState.Success).message, Toast.LENGTH_SHORT).show()
//                coordinator.navigateToHome()
//            }
//            is VerifyOtpState.Error -> {
//                Toast.makeText(context, (verifyOtpState as VerifyOtpState.Error).message, Toast.LENGTH_SHORT).show()
//            }
//            VerifyOtpState.Idle -> { /* Do nothing */ }
//        }
//
//    }
//}


package com.bibekgupta.cleanarchitecture.ui.auth.otp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinator
import com.bibekgupta.cleanarchitecture.presentation.auth.otp.OtpVerifyViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OtpVerifyScreen(
    coordinator: AppCoordinator,
    phoneNumber: String,
    viewModel: OtpVerifyViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf(TextFieldValue("")) }
    val otpVerifyState by viewModel.otpVerifyState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Verifying OTP for $phoneNumber")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Enter OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.verifyOtp(phoneNumber, otp.text)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (otpVerifyState) {
            is OtpVerifyViewModel.OtpVerifyState.Loading -> CircularProgressIndicator()
            is OtpVerifyViewModel.OtpVerifyState.Success -> {
                Text("OTP Verified! Navigating to Home...")
                coordinator.navigateToHome()
            }
            is OtpVerifyViewModel.OtpVerifyState.Error -> {
                Text(
                    text = (otpVerifyState as OtpVerifyViewModel.OtpVerifyState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
