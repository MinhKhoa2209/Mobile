package com.example.Unit1.Lesson2

import kotlin.random.Random

fun main() {
    feedTheFish()
}

fun feedTheFish() {
    val day = randomDay()
    val food = "pellets"
    println ("Today is $day and the fish eat $food")
}
fun randomDay() : String {
    val week = arrayOf ("Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday")
    return week[Random.nextInt(week.size)]
}
