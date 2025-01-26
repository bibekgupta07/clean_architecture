//package com.bibekgupta.cleanarchitecture.ui.auth.otp
//
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinator
//import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.runtime.setValue
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.platform.LocalContext
//import androidx.navigation.NavBackStackEntry
//import com.bibekgupta.cleanarchitecture.utility.Resource


//@Composable
//fun OtpVerifyScreen(
//    coordinator: AppCoordinatorImpl,
//    phoneNumber: String
//
//) {
//
//    var otp by remember { mutableStateOf("") }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Verify OTP for ",
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
//               // Todo:
//            },
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text("Verify OTP")
//        }
//    }
//}


package com.bibekgupta.cleanarchitecture.ui.auth.otp

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bibekgupta.cleanarchitecture.ui.auth.otp.VerifyOtpState.*



@Composable
fun OtpVerifyScreen(
    coordinator: AppCoordinatorImpl,
    phoneNumber: String,
    viewModel: OtpVerifyViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf("") }
    val context = LocalContext.current
    val verifyOtpState by viewModel.verifyOtpState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verify OTP for $phoneNumber",
            style = MaterialTheme.typography.headlineSmall
        )
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
                if (otp.isNotEmpty()) {
                    viewModel.verifyOtp(phoneNumber, otp)
                } else {
                    Toast.makeText(context, "Please enter the OTP", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Verify OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // State handling
        when (verifyOtpState) {
            is VerifyOtpViewModel.VerifyOtpState.Loading -> CircularProgressIndicator()
            is VerifyOtpViewModel.VerifyOtpState.Success -> {
                val successMessage = "OTP Verified Successfully"
                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
                coordinator.navigateToHome() // Navigate to Home
            }
            is VerifyOtpViewModel.VerifyOtpState.Error -> {
                val errorMessage = (verifyOtpState as VerifyOtpViewModel.VerifyOtpState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> {
                // Do nothing for Idle state
            }
        }
    }
}
