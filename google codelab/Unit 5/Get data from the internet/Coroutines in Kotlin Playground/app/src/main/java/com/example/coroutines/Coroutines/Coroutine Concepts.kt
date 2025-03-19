package com.example.coroutines.Coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking started")
        launch {
            println("${Thread.currentThread().name} - launch function started")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - Running on Default Dispatcher")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - End of launch function")
        }
        val result = async {
            println("${Thread.currentThread().name} - async function started")
            delay(500)
            "Data from async"
        }
        println("${Thread.currentThread().name} - Awaiting result: ${result.await()}")

        println("${Thread.currentThread().name} - runBlocking ended")
    }
}

class MyCoroutineScope : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun fetchData() {
        launch {
            println("${Thread.currentThread().name} - Fetching data...")
            val data = withContext(Dispatchers.IO) {
                delay(1000)
                "Fetched Data"
            }
            println("${Thread.currentThread().name} - Data: $data")
        }
    }

    fun cancelScope() {
        job.cancel()
        println("Coroutine scope cancelled.")
    }
}