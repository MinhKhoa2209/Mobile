package com.example.Unit1.Lesson3

fun buildAquarium() {
    val aquarium1 = Aquarium()
    aquarium1.printSize()
    val aquarium2 = Aquarium(width = 25)
    aquarium2.printSize()
    val aquarium3 = Aquarium(height = 35, length = 110)
    aquarium3.printSize()
    val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
    aquarium4.printSize()
}
fun main() {
    buildAquarium()
}