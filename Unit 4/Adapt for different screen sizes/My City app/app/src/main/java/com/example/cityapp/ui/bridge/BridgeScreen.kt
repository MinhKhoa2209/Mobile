package com.example.cityapp.ui.bridge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cityapp.components.ImageCard
import com.example.cityapp.data.BridgeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BridgeScreen(navController: NavController) {
    val bridges = BridgeRepository.getBridges()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Famous Bridges")
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
            items(bridges.size) { index ->
                ImageCard(title = bridges[index].name, imageRes = bridges[index].imageRes)
            }
        }
    }
}
