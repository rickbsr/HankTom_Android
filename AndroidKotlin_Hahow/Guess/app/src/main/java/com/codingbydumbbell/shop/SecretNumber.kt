package com.codingbydumbbell.shop

import java.util.*

class SecretNumber {
    var secret = Random().nextInt(10) + 1 // 在 1 到 10 中產生一個隨機數字
    var count = 0

    fun validate(number: Int): Int {
        count++
        return number - secret
    }

    fun reset() {
        secret = Random().nextInt(10) + 1
        count = 0
    }
}

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(2)}, count: ${secretNumber.count}")
}