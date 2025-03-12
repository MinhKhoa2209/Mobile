package com.example.Unit1.Lesson1

fun main() {
    var numberOfFish = 5
    if (numberOfFish < 0) {
        println("Empty tank")
    } else if (numberOfFish < 40) {
        println("Got fish!")
    } else {
        println("That's a lot of fish!")
    }
}