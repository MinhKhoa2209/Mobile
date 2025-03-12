package com.example.kotlinfundamentals.Additional

fun main() {
//Using if/else statements to express conditions
    val age = 18
    if (age >= 18) {
        println("You are an adult.")
    } else {
        println("You are a minor.")
    }

//Using a when statement for multiple branches
    val day = 3
    val dayName = when (day) {
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day"
    }
    println("Today is $dayName.")

// Using if/else and when as expressions
    val number = 10
    val result = if (number % 2 == 0) "Even" else "Odd"
    println("The number is $result.")

    val grade = 85
    val performance = when {
        grade >= 90 -> "Excellent"
        grade >= 75 -> "Good"
        grade >= 50 -> "Average"
        else -> "Fail"
    }
    println("Your performance is $performance.")
}