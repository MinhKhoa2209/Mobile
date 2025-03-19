package com.example.calculatetipapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculatetipapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calcButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val billAmountString = binding.billAmountField.text.toString()
        val billAmount = billAmountString.toDoubleOrNull() ?: 0.0

        val tipPercentageString = binding.tipAmountField.text.toString()
        val tipPercentage = tipPercentageString.toDoubleOrNull() ?: 0.0

        var tipAmount = billAmount * (tipPercentage / 100)

        if (binding.rowUpToggle.isActivated) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.resultText.text = formattedTip
    }
}
