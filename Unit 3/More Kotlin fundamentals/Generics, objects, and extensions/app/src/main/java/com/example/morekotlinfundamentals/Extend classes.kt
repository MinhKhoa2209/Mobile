package com.example.morekotlinfundamentals

open class Animal {
    open fun sound() {
        println("Some sound")
    }
}

class Dog : Animal() {
    override fun sound() {
        println("Bark!")
    }
}

fun main() {
    val myDog = Dog()
    myDog.sound()  // Output: Bark!
}
