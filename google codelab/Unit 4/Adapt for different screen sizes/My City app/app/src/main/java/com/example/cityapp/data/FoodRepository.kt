package com.example.cityapp.data

import com.example.cityapp.model.Food
import com.example.cityapp.R

object FoodRepository {
    fun getFoods(): List<Food> {
        return listOf(
            Food("Mi Quang", R.drawable.mi_quang),
            Food("Banh Xeo", R.drawable.banh_xeo),
            Food("Bun Cha Ca", R.drawable.bun_cha_ca)
        )
    }
}
