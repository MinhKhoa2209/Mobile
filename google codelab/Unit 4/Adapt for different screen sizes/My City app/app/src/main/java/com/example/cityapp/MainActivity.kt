package com.example.cityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cityapp.components.BottomNavigationBar
import com.example.cityapp.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { paddingValues ->
                NavGraph(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
