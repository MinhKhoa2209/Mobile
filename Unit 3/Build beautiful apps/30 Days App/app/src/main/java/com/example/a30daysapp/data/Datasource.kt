package com.example.a30daysapp.data

import com.example.a30daysapp.R
import com.example.a30daysapp.model.FitnessTip

object DataSource {
    val fitnessTips = listOf(
        FitnessTip(1, "Morning Stretch", "Start your day with 10 minutes of stretching.", R.drawable.stretching),
        FitnessTip(2, "Hydration", "Drink at least 8 glasses of water a day.", R.drawable.water),
        FitnessTip(3, "Cardio", "Do 30 minutes of cardio exercise.", R.drawable.running),
        FitnessTip(4, "Strength Training", "Include bodyweight exercises like push-ups.", R.drawable.strength),
        FitnessTip(5, "Yoga", "Try 15 minutes of yoga to improve flexibility.", R.drawable.yoga),
        FitnessTip(6,"Go to gym","Do 1h30 exercise", R.drawable.gym)
    )
}