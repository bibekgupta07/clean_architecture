package com.bibekgupta.cleanarchitecture.presentation.coordinator


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState



data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String
)

val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.HOME, Icons.Filled.Home, "Home"),
    BottomNavItem(NavRoutes.ADD, Icons.Filled.Add, "Add"),
    BottomNavItem(NavRoutes.SETTINGS, Icons.Filled.Person, "Profile")

)


@Composable
fun MainScreenWithBottomBar(
    coordinator: AppCoordinatorImpl,
    content: @Composable () -> Unit
) {
    val navController = coordinator.navController

    Scaffold(
        bottomBar = { BottomNavigationBar(coordinator, navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    )
}


@Composable
fun BottomNavigationBar(coordinator: AppCoordinatorImpl, navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute?.startsWith(item.route) == true,
                onClick = { coordinator.navigateTo(item.route) },
                icon = { Icon(item.icon, contentDescription = item.contentDescription) }
            )
        }
    }
}
