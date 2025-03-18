package com.example.flightsearch.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import com.example.flightsearch.data.DataStoreManager
import com.example.flightsearch.data.Airport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    context: Context,
    flightViewModel: FlightViewModel = viewModel(factory = FlightViewModel.factory)
) {
    val dataStoreManager = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    val searchResults by flightViewModel.getAllAirports(searchQuery).collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        dataStoreManager.searchQueryFlow.collect { savedQuery ->
            if (searchQuery != savedQuery) {
                searchQuery = savedQuery
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flight Search", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(searchQuery, onQueryChange = { query ->
                searchQuery = query
                coroutineScope.launch {
                    dataStoreManager.saveSearchQuery(query)
                }
            })

            Spacer(modifier = Modifier.height(16.dp))

            if (searchResults.isNotEmpty()) {
                FlightList(searchResults)
            } else {
                Text(
                    "No results found.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search airport") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            IconButton(onClick = { /* TODO: Voice Search */ }) {
                Icon(Icons.Default.Mic, contentDescription = "Voice Search")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFEEEEFF),
            unfocusedContainerColor = Color(0xFFEEEEFF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun FlightList(flights: List<Airport>) {
    LazyColumn {
        items(flights) { flight ->
            FlightItem(flight)
        }
    }
}

@Composable
fun FlightItem(flight: Airport) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEFF)),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                flight.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Handle favorite */ }) {
                Icon(Icons.Default.Star, contentDescription = "Favorite", tint = Color.Gray)
            }
        }
    }
}
