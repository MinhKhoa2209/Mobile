package com.example.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Task 1 started")

    launch {
        delay(2000) // Giả lập công việc mất 2 giây
        println("Task 1 completed")
    }

    launch {
        println("Task 2 started")
        delay(1000) // Giả lập công việc mất 1 giây
        println("Task 2 completed")
    }

    println("Main program continues...")
}
