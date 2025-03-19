package com.example.clickbehavior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    var requiredSqueezes by remember { mutableStateOf(Random.nextInt(2, 5)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7E6A1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lemonade",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        when (step) {
            1 -> LemonadeStep(R.drawable.lemon_tree, "Tap the lemon tree to select a lemon") {
                step = 2
            }
            2 -> LemonadeStep(R.drawable.lemon, "Keep tapping the lemon to squeeze it") {
                squeezeCount++
                if (squeezeCount >= requiredSqueezes) {
                    step = 3
                    squeezeCount = 0
                }
            }
            3 -> LemonadeStep(R.drawable.lemonade_glass, "Tap the lemonade to drink it") {
                step = 4
            }
            4 -> LemonadeStep(R.drawable.empty_glass, "Tap the empty glass to start again") {
                step = 1
                requiredSqueezes = Random.nextInt(2, 5)
            }
        }
    }
}

@Composable
fun LemonadeStep(imageRes: Int, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier
                .size(150.dp)
                .clickable { onClick() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}
