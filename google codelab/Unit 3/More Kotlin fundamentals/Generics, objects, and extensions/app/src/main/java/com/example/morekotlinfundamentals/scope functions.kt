package com.example.morekotlinfundamentals

data class Car(var brand: String, var speed: Int)

fun main() {
    val myCar = Car("Toyota", 100).apply {
        speed += 20
    }

    println(myCar) 
}
