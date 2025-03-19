package com.example.cityapp.data

import com.example.cityapp.model.Bridge
import com.example.cityapp.R

object BridgeRepository {
    fun getBridges(): List<Bridge> {
        return listOf(
            Bridge("Dragon Bridge", R.drawable.dragon_bridge),
            Bridge("Han River Bridge", R.drawable.han_bridge),
            Bridge("Tran Thi Ly Bridge", R.drawable.tran_thi_ly_bridge)
        )
    }
}
