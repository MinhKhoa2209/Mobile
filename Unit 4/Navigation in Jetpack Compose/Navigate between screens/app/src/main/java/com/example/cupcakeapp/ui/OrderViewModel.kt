package com.example.cupcakeapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


private const val PRICE_PER_CUPCAKE = 2.00

private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00
class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(com.example.cupcakeapp.data.OrderData(pickupOptions = pickupOptions()))
    val uiState: StateFlow<com.example.cupcakeapp.data.OrderData> = _uiState.asStateFlow()
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    fun resetOrder() {
        _uiState.value = com.example.cupcakeapp.data.OrderData(pickupOptions = pickupOptions())
    }

    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * com.example.cupcakeapp.ui.PRICE_PER_CUPCAKE
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += com.example.cupcakeapp.ui.PRICE_FOR_SAME_DAY_PICKUP
        }
        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculatedPrice)
        return formattedPrice
    }

    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}