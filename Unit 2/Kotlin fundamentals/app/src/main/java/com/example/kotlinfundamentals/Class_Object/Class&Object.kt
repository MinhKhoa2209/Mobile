package com.example.kotlinfundamentals.Class_Object

class Person(val name: String, val age: Int) {
    fun introduce() {
        println("Hi, my name is $name and I am $age years old.")
    }
}

fun main() {
    val person1 = Person("John", 25)  // Creating an object
    person1.introduce() // Output: Hi, my name is John and I am 25 years old.
}
