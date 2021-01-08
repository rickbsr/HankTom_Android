package com.tom

import java.util.*

fun main() {
    val secret = Random().nextInt(10) + 1
    println(secret)
    val scanner = Scanner(System.`in`)
    for (i in 1..5) {
        print("Please enter a number: ")
        var number = scanner.nextInt()
        println("第 ${i}次 $number")

//        if (number == -1) break
        if (number > secret) {
            println("lowerr")
        } else if (number < secret) {
            println("higher")
        } else {
            println("Great, the number is " + secret)
            break
        }
    }
}