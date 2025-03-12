package com.example.collections_classes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val events = getEvents()
        findViewById<TextView>(R.id.textView).text = events.summary()
    }

    private fun getEvents() = mutableListOf(
        Event("Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event("Learn Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event("Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event("Watch DevBytes", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event("Check Jetpack", daypart = Daypart.EVENING, durationInMinutes = 45),
        Event("Go to sleep", "End of day", Daypart.EVENING, 0)
    )
}
