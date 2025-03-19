package com.example.Unit1.Lesson3

class Aquarium(length: Int = 100, width: Int = 20, height: Int = 40) {
    var length: Int = length
    var width: Int = width
    var height: Int = height


    fun printSize() {
        println("Width: $width cm " + "Length: $length cm " + "Height: $height cm ")
    }

}