package com.example.unscrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.unscrambleapp.ui.theme.UnscrambleAppTheme
import com.example.unscrambleapp.ui.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnscrambleAppTheme {
                Surface(
                    modifier =Modifier.fillMaxSize()
                    ) {
                        GameScreen()
                    }
                    }
                }
            }
        }

