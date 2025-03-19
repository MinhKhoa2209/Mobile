package com.example.morekotlinfundamentals

data class User(val name: String, val age: Int)

fun main() {
    val user = User("Alice", 25)
    println(user)  // Output: User(name=Alice, age=25)
}
