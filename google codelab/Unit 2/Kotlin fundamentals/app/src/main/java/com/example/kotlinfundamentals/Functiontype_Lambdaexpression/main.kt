package com.example.kotlinfundamentals.Functiontype_Lambdaexpression

fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main() {
    val sum = calculate(5, 3) { x, y -> x + y }
    println("Sum: $sum") // Output: Sum: 8
}
