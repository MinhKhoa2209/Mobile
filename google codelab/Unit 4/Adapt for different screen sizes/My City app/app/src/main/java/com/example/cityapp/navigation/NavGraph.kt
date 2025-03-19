package com.example.cityapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cityapp.ui.home.HomeScreen
import com.example.cityapp.ui.bridge.BridgeScreen
import com.example.cityapp.ui.beach.BeachScreen
import com.example.cityapp.ui.food.FoodScreen
import com.example.cityapp.ui.culture.CultureScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bridge : Screen("bridge")
    object Beach : Screen("beach")
    object Food : Screen("food")
    object Culture : Screen("culture")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Bridge.route) { BridgeScreen(navController) }
        composable(Screen.Beach.route) { BeachScreen(navController) }
        composable(Screen.Food.route) { FoodScreen(navController) }
        composable(Screen.Culture.route) { CultureScreen(navController) }
    }
}
