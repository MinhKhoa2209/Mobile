package com.example.morekotlinfundamentals.Generics

class Box<T>(val item: T) {
    fun getItem(): T {
        return item
    }
}

fun main() {
    val intBox = Box(10)
    val stringBox = Box("Hello")

    println(intBox.getItem())  // Output: 10
    println(stringBox.getItem())  // Output: Hello
}
