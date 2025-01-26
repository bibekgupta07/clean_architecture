package com.bibekgupta.cleanarchitecture.presentation.main


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bibekgupta.cleanarchitecture.presentation.coordinator.AppCoordinatorImpl
import com.bibekgupta.cleanarchitecture.presentation.coordinator.NavRoutes


@Composable
fun HomeScreen(coordinator: AppCoordinatorImpl) {
    val items = listOf("Item1", "Item2", "Item3")

    Column {
        items.forEach { item ->
            Button(onClick = {
                coordinator.navigateTo("${NavRoutes.HOME}/$item")
            }) {
                Text("Go to $item Detail")
            }
        }
    }
}
