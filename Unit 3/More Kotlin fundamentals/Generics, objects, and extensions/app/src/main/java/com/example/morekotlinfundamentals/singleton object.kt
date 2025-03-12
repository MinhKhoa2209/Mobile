package com.example.morekotlinfundamentals

object Logger {
    fun log(message: String) {
        println("Log: $message")
    }
}

fun main() {
    Logger.log("Application started")  // Output: Log: Application started
}
