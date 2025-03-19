package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = listOf(
        Artwork(R.drawable.art1, "Starry Night", "Vincent van Gogh", "1889"),
        Artwork(R.drawable.art2, "Mona Lisa", "Leonardo da Vinci", "1503"),
        Artwork(R.drawable.art3, "The Scream", "Edvard Munch", "1893")
    )

    var currentArtworkIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = artworks[currentArtworkIndex].imageRes),
            contentDescription = artworks[currentArtworkIndex].title,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = artworks[currentArtworkIndex].title, fontSize = 24.sp)
        Text(text = "${artworks[currentArtworkIndex].artist} - ${artworks[currentArtworkIndex].year}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                currentArtworkIndex = if (currentArtworkIndex > 0) currentArtworkIndex - 1 else artworks.size - 1
            }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
            }) {
                Text("Next")
            }
        }
    }
}