package com.example.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                println("Task running: $i")
                delay(500)
            }
        } catch (e: CancellationException) {
            println("Coroutine was cancelled!")
        } finally {
            println("Cleanup after cancellation")
        }
    }

    delay(1200)
    println("Cancelling coroutine...")
    job.cancel()
    job.join()
    println("Main program exits")
}
