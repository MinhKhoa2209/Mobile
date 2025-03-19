package com.example.a30daysapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30daysapp.data.DataSource
import com.example.a30daysapp.model.FitnessTip
import com.example.a30daysapp.ui.theme._30daysappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30daysappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FitnessTipsList(tips = DataSource.fitnessTips)
                }
            }
        }
    }
}

@Composable
fun FitnessTipsList(tips: List<FitnessTip>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "30 Days of Fitness",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tips) { tip ->
                FitnessTipCard(tip)
            }
        }
    }
}

@Composable
fun FitnessTipCard(tip: FitnessTip) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Day ${tip.day}: ${tip.title}",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = tip.imageResId),
                contentDescription = null,
                modifier = Modifier.size(120.dp).align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tip.description,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
