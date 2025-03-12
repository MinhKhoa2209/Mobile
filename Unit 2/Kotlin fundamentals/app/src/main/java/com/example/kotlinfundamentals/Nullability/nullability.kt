package com.example.kotlinfundamentals.Nullability

fun main() {
    val name: String? = null
    println(name?.length) // Output: null (no error)
}
