package com.example.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferredResult = async {
        delay(2000) // Giả lập công việc mất 2 giây
        "Hello from coroutine!"
    }

    println("Waiting for result...")
    val result = deferredResult.await() // Chờ kết quả từ async
    println("Received: $result")
}
