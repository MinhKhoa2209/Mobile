package com.example.superheroesapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import com.example.superheroesapp.R

@Composable
fun SuperheroScreen() {
    var darkTheme by remember { mutableStateOf(false) }
    val backgroundColor = if (darkTheme) DarkBackground else LightBackground
    val textColor = if (darkTheme) DarkText else LightText
    val cardColor = if (darkTheme) DarkCard else LightCard

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { darkTheme = !darkTheme },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = if (darkTheme) "Switch to Light Mode" else "Switch to Dark Mode")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Superheroes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(heroes) { hero ->
                SuperheroCard(hero, cardColor, textColor)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun SuperheroCard(hero: Hero, cardColor: Color, textColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp) // Thay thế clip bằng shape
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = hero.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp)), // Giữ clip cho ảnh
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = hero.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = textColor
                )
                Text(
                    text = hero.description,
                    fontSize = 14.sp,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

data class Hero(val name: String, val description: String, val imageRes: Int)

val heroes = listOf(
    Hero("Iron Man", "Genius, billionaire, playboy, philanthropist", R.drawable.superhero1),
    Hero("Thor", "God of Thunder, wields Mjolnir", R.drawable.superhero2),
    Hero("Hulk", "Unleashes unstoppable rage and strength", R.drawable.superhero3),
    Hero("Captain America", "Super soldier with an unbreakable shield", R.drawable.superhero4),
    Hero("Spider-Man", "Agile wall-crawler with spider senses", R.drawable.superhero5),
    Hero("Hawkeye", "Master marksman with unparalleled precision", R.drawable.superhero6)
)

// Định nghĩa màu sắc cho Dark/Light mode
val DarkBackground = Color(0xFF121212)
val LightBackground = Color(0xFFFFFFFF)
val DarkText = Color(0xFFFFFFFF)
val LightText = Color(0xFF000000)
val DarkCard = Color(0xFF1E1E1E)
val LightCard = Color(0xFFF5F5F5)
