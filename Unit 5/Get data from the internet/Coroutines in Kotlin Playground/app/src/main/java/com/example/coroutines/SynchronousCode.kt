package com.example.coroutines

fun main() {
    println("Task 1 started")
    Thread.sleep(2000) // Giả lập công việc mất 2 giây
    println("Task 1 completed")

    println("Task 2 started")
    Thread.sleep(1000) // Giả lập công việc mất 1 giây
    println("Task 2 completed")
}
