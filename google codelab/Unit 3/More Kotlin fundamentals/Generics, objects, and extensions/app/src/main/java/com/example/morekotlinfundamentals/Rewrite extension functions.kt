package com.example.morekotlinfundamentals

interface Greetable {
    fun greet(): String
}

class Person(val name: String) : Greetable {
    override fun greet() = "Hello, my name is $name"
}

fun main() {
    val person = Person("John")
    println(person.greet())
}
