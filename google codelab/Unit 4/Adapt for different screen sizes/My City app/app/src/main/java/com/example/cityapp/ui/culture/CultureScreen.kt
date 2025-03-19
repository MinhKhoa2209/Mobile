package com.example.cityapp.ui.culture

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cityapp.components.ImageCard
import com.example.cityapp.data.CultureRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CultureScreen(navController: NavController) {
    val cultures = CultureRepository.getCultures()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Cultural Highlights")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(cultures.size) { index ->
                ImageCard(title = cultures[index].name, imageRes = cultures[index].imageRes)
            }
        }
    }
}
