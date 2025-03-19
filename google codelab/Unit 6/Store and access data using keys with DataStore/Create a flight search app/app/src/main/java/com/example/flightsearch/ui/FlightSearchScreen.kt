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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import com.example.flightsearch.data.DataStoreManager
import com.example.flightsearch.data.Airport
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    context: Context,
    flightViewModel: FlightViewModel = viewModel(factory = FlightViewModel.factory)
) {
    val dataStoreManager = remember { DataStoreManager(context) }
    val coroutineScope = rememberCoroutineScope()

    val savedQuery by dataStoreManager.searchQueryFlow.collectAsState(initial = "")
    var searchQuery by remember { mutableStateOf(savedQuery) }

    // Danh sách yêu thích khi mở app
    var favoriteFlights by remember { mutableStateOf(emptyList<Airport>()) }

    // Load danh sách yêu thích khi mở app
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            favoriteFlights = flightViewModel.getFavoriteAirports().first()
        }
    }

    // Danh sách kết quả tìm kiếm
    val searchResults by flightViewModel.getAllAirports(searchQuery).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flight Search", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(searchQuery) { query ->
                searchQuery = query
                coroutineScope.launch {
                    dataStoreManager.saveSearchQuery(query)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị danh sách yêu thích nếu có
            if (searchQuery.isEmpty() && favoriteFlights.isNotEmpty()) {
                Text(
                    text = "Favorite Flights",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                LazyColumn {
                    items(favoriteFlights) { flight ->
                        FlightItem(flight, flightViewModel) {
                            coroutineScope.launch {
                                favoriteFlights = flightViewModel.getFavoriteAirports().first()
                            }
                        }
                    }
                }
            } else {
                LazyColumn {
                    items(searchResults) { flight ->
                        FlightItem(flight, flightViewModel) {
                            coroutineScope.launch {
                                favoriteFlights = flightViewModel.getFavoriteAirports().first()
                            }
                        }
                    }
                }
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
fun FlightItem(flight: Airport, viewModel: FlightViewModel, onFavoriteChange: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(flight.id) {
        coroutineScope.launch {
            isFavorite = viewModel.isFavorite(flight.id)
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEFF)),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = flight.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.toggleFavorite(flight)
                        isFavorite = !isFavorite
                        onFavoriteChange()
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Yellow else Color.Gray
                )
            }
        }
    }
}
