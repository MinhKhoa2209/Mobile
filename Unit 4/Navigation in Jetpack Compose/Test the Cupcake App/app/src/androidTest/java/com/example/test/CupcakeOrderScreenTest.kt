package com.example.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcakeapp.R
import com.example.cupcakeapp.data.DataSource
import com.example.cupcakeapp.data.OrderData
import com.example.cupcakeapp.ui.OrderSummaryScreen
import com.example.cupcakeapp.ui.SelectOptionScreen
import com.example.cupcakeapp.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeOrderUiState = OrderData(
        quantity = 6,
        flavor = "Vanilla",
        date = "Wed Jul 21",
        price = "$100",
        pickupOptions = listOf()
    )

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        DataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithText(it.first.toString()).assertIsDisplayed()
        }
    }

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.next)).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_optionSelected_NextButtonEnabled() {
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subTotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subTotal, options = flavours)
        }

        composeTestRule.onNodeWithText("Vanilla").performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.next)).assertIsEnabled()
    }

    @Test
    fun summaryScreen_verifyContentDisplay() {
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderData = fakeOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> },
            )
        }

        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        ).assertIsDisplayed()
    }
}
