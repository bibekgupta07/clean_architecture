package com.bibekgupta.cleanarchitecture.presentation.auth

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
import com.bibekgupta.cleanarchitecture.ui.auth.register.RegisterViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import coil.compose.rememberAsyncImagePainter
import coil.transform.CircleCropTransformation
import coil.request.ImageRequest



//@Composable
//fun RegisterScreen(
//    coordinator: AppCoordinatorImpl,
//    viewModel: RegisterViewModel = hiltViewModel()
//) {
//    val uiState by viewModel.uiState.collectAsState()
//    val name by viewModel.name.collectAsState()
//    val phone by viewModel.phone.collectAsState()
//    val email by viewModel.email.collectAsState()
//    val password by viewModel.password.collectAsState()
//    val profilePicUri by viewModel.profilePicUri.collectAsState()
//
//    val context = LocalContext.current
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            Log.d("ImagePicker", "Selected URI: $uri")
//            viewModel.onProfilePicChanged(uri) // Ensure this is called with the selected URI
//        } else {
//            // Handle case where no URI was selected
//        }
//    }
//
//    LaunchedEffect(uiState) {
//        if (uiState is RegisterViewModel.RegisterUiState.Success) {
//            // Navigate to the login screen using the coordinator
//            coordinator.navigateToLogin()
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TextField(
//            value = name,
//            onValueChange = { viewModel.onNameChanged(it) },
//            label = { Text("Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = phone,
//            onValueChange = { viewModel.onPhoneChanged(it) },
//            label = { Text("Phone") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = email,
//            onValueChange = { viewModel.onEmailChanged(it) },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = password,
//            onValueChange = { viewModel.onPasswordChanged(it) },
//            label = { Text("Password") },
//            modifier = Modifier.fillMaxWidth(),
//            visualTransformation = PasswordVisualTransformation()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { launcher.launch("image/*") }) {
//            Text(text = "Select Profile Picture")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display the selected image if the URI is not null
//        profilePicUri?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(
//                    model = ImageRequest.Builder(context)
//                        .data(uri)
//                        .transformations(CircleCropTransformation())
//                        .build()
//                ),
//                contentDescription = "Profile Picture",
//                modifier = Modifier
//                    .size(100.dp) // Adjust size as needed
//                    .padding(8.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { viewModel.registerUser(context) },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(text = "Register")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        when (uiState) {
//            is RegisterViewModel.RegisterUiState.Loading -> CircularProgressIndicator()
//            is RegisterViewModel.RegisterUiState.Error -> {
//                Text(
//                    text = (uiState as RegisterViewModel.RegisterUiState.Error).message,
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier.padding(top = 8.dp)
//                )
//            }
//            is RegisterViewModel.RegisterUiState.Success -> {
//                // Navigation handled in LaunchedEffect
//            }
//            else -> {}
//        }
//    }
//}

@Composable
fun RegisterScreen(
    coordinator: AppCoordinatorImpl,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    // Collecting state variables from the ViewModel
    val uiState by viewModel.uiState.collectAsState()
    val name by viewModel.name.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val profilePicUri by viewModel.profilePicUri.collectAsState()

    val context = LocalContext.current

    // Launcher for selecting an image from the gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent() // Opens file picker for specific MIME type
    ) { uri: Uri? ->
        if (uri != null) {
            Log.d("ImagePicker", "Selected URI: $uri")
            viewModel.onProfilePicChanged(uri) // Update ViewModel with selected URI
        } else {
            // Handle case where no image was selected
            Log.d("ImagePicker", "No image selected")
        }
    }

    // Navigating to the login screen upon successful registration
    LaunchedEffect(uiState) {
        if (uiState is RegisterViewModel.RegisterUiState.Success) {
            coordinator.navigateToLogin()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input fields for name, phone, email, and password
        TextField(
            value = name,
            onValueChange = { viewModel.onNameChanged(it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phone,
            onValueChange = { viewModel.onPhoneChanged(it) },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { viewModel.onEmailChanged(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // Masks password input
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Button to launch image picker
        Button(onClick = { launcher.launch("image/*") }) { // Restrict picker to images only
            Text(text = "Select Profile Picture")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display selected profile picture (if available)
        profilePicUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(uri) // Load the image using the selected URI
                        .transformations(CircleCropTransformation()) // Apply circular crop
                        .build()
                ),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp) // Adjust size for profile picture display
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register button to initiate user registration
        Button(
            onClick = { viewModel.registerUser(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display appropriate UI based on registration state
        when (uiState) {
            is RegisterViewModel.RegisterUiState.Loading -> CircularProgressIndicator() // Show loading indicator
            is RegisterViewModel.RegisterUiState.Error -> {
                Text(
                    text = (uiState as RegisterViewModel.RegisterUiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            is RegisterViewModel.RegisterUiState.Success -> {
                // Navigation is handled in LaunchedEffect above
            }
            else -> {}
        }
    }
}
