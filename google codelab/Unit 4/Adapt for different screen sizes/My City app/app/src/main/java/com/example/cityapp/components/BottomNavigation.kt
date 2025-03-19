package com.example.cityapp.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cityapp.navigation.Screen

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Screen.Home.route),
        BottomNavItem("Bridge", Icons.Filled.AccountBalanceWallet, Screen.Bridge.route),
        BottomNavItem("Beach", Icons.Filled.Waves, Screen.Beach.route),
        BottomNavItem("Food", Icons.Filled.Fastfood, Screen.Food.route),
        BottomNavItem("Culture", Icons.Filled.Landscape, Screen.Culture.route)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF00B4D8)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title, tint = Color.White) },
                label = { Text(item.title, color = Color.White) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

