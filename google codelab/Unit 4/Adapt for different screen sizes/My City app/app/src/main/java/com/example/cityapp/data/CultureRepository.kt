package com.example.cityapp.data

import com.example.cityapp.model.Culture
import com.example.cityapp.R

object CultureRepository {
    fun getCultures(): List<Culture> {
        return listOf(
            Culture("Linh Ung Pagoda", R.drawable.linh_ung_pagoda),
            Culture("Marble Mountains", R.drawable.marble_mountains),
            Culture("Da Nang Museum", R.drawable.da_nang_museum)
        )
    }
}
