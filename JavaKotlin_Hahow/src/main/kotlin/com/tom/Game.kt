package com.tom

import java.util.*

fun main() {
    val secret = Random().nextInt(10) + 1
    print(secret)
    val scanner = Scanner(System.`in`)
    var number = 0
    while (number != secret) {
        print("Please enter a number: ")
//        number = scanner.nextInt()
        number = readLine()!!.toInt() // Kotlin 本身的方法
        if(number>secret){
            println("height")
        }else if (number<secret){
            println("higher")
        }else{
            println("Great, the number is $number")
        }
    }
}