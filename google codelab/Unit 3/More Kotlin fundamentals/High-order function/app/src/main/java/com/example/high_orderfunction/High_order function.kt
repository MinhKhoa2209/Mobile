package com.example.high_orderfunction

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 2. forEach() with string templates
    numbers.forEach { println("Number: $it") }

    // 3. map() - Square each number
    val squaredNumbers = numbers.map { it * it }
    println("Squared Numbers: $squaredNumbers")

    // 4. filter() - Get even numbers
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even Numbers: $evenNumbers")

    // 5. groupBy() - Group numbers by odd and even
    val groupedNumbers = numbers.groupBy { if (it % 2 == 0) "Even" else "Odd" }
    println("Grouped Numbers: $groupedNumbers")

    // 6. fold() - Sum all numbers with an initial value of 0
    val sum = numbers.fold(0) { acc, num -> acc + num }
    println("Sum of Numbers: $sum")

    // 7. sortedBy() - Sort numbers in descending order
    val sortedDescending = numbers.sortedByDescending { it }
    println("Sorted Numbers Descending: $sortedDescending")
}
