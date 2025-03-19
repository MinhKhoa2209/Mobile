package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val datePickerButton = findViewById<Button>(R.id.datePickerButton)
        datePickerButton.setOnClickListener { view -> calculateAge(view) }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calculateAge(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val ageTextView = findViewById<TextView>(R.id.selectedDate) // TextView để hiển thị tuổi

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val birthDate = Calendar.getInstance()
            birthDate.set(selectedYear, selectedMonth, selectedDay)

            val currentDate = Calendar.getInstance()

            var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)

            // Kiểm tra nếu chưa đến sinh nhật trong năm nay thì trừ đi 1 tuổi
            if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                age--
            }

            ageTextView.text = "Your age is: ${age}"
        }, year, month, day)

        datePickerDialog.show()
    }
}
