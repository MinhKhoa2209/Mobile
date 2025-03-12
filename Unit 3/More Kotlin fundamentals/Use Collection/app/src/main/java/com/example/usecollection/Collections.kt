package com.example.usecollection
class Collections() {

    fun main() {
        println("--- Kotlin Collections Example ---")

        // List
        val immutableList = listOf("Apple", "Banana", "Cherry")
        println("Immutable List: $immutableList")

        val mutableList = mutableListOf("Dog", "Cat", "Rabbit")
        mutableList.add("Hamster")
        println("Mutable List: $mutableList")

        // Set
        val immutableSet = setOf(1, 2, 3, 3, 4)
        println("Immutable Set: $immutableSet")

        val mutableSet = mutableSetOf("A", "B", "C")
        mutableSet.add("D")
        println("Mutable Set: $mutableSet")

        // Map
        val immutableMap = mapOf("name" to "John", "age" to 25)
        println("Immutable Map: $immutableMap")

        val mutableMap = mutableMapOf("brand" to "Toyota", "model" to "Corolla")
        mutableMap["year"] = "2025"
        println("Mutable Map: $mutableMap")

        // Conditions and Loops
        println("\nLooping through List:")
        for (item in immutableList) {
            println("Item: $item")
        }

        println("\nWhile Loop Example:")
        var counter = 5
        while (counter > 0) {
            println("Counter: $counter")
            counter--
        }

        // hashCode
        println("\nHashCode Examples:")
        val obj1 = "Hello"
        val obj2 = "Hello"
        println("HashCode of obj1: ${obj1.hashCode()}")
        println("HashCode of obj2: ${obj2.hashCode()}")
    }

}