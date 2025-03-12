package com.example.cityapp.data

import com.example.cityapp.model.Beach
import com.example.cityapp.R

object BeachRepository {
    fun getBeaches(): List<Beach> {
        return listOf(
            Beach("My Khe Beach", R.drawable.my_khe_beach),
            Beach("Non Nuoc Beach", R.drawable.non_nuoc_beach),
            Beach("Bac My An Beach", R.drawable.bac_my_an_beach)
        )
    }
}
