package com.example.a30daysapp.model

import androidx.annotation.DrawableRes

data class FitnessTip(
    val day: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int
)