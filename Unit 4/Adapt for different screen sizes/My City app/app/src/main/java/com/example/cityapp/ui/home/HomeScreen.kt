package com.example.cityapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cityapp.R
import com.example.cityapp.components.ImageCard
import com.example.cityapp.navigation.Screen
import com.example.cityapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Da Nang City",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF00B4D8))
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.danang),
                contentDescription = "Background",
                modifier = Modifier
                    .fillMaxSize().align(Alignment.Center)
                    .matchParentSize(),
                contentScale = ContentScale.FillBounds ,
                alpha = 0.8f
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Explore famous places",
                    style = Typography.headlineMedium,
                    color = Color(0xFF0077b6),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                )

                val places = listOf(
                    "Bridges" to Screen.Bridge.route,
                    "Beaches" to Screen.Beach.route,
                    "Food" to Screen.Food.route,
                    "Culture" to Screen.Culture.route
                )

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(places) { (title, route) ->
                        ImageCard(
                            title = title,
                            onClick = { navController.navigate(route) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
