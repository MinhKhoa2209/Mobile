
package com.example.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.example.addnavigation.model.MenuItem
import com.example.addnavigation.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(Order())
    val uiState: StateFlow<Order> = _uiState.asStateFlow()

    fun updateEntree(entree: MenuItem.EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: MenuItem.SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = Order()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is MenuItem.EntreeItem) newItem else currentState.entree,
                sideDish = if (newItem is MenuItem.SideDishItem) newItem else currentState.sideDish,
                accompaniment =
                    if (newItem is MenuItem.AccompanimentItem) newItem else currentState.accompaniment
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}
