package com.example.morekotlinfundamentals

enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun main() {
    val today = Day.WEDNESDAY
    println("Today is $today") // Output: Today is WEDNESDAY
}
