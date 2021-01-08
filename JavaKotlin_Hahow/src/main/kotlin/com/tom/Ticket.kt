package com.kotlin

import java.util.*

// 就像 java 中的 model , pojo, java bean
data class Ticket(val origin: Int, val destination: Int, val price: Int)

fun main() {
    var ticket = Ticket(20, 51, 420)
    val s = "abcdefg"
    println(s.validate())
    println((1..10).random())
}

// 擴充功能
fun String.validate(): Boolean {
    return this.length >= 6
}

// 整數範圍 1..10
fun IntRange.random(): Int {
    val r = Random().nextInt(endInclusive - start) + start
    return r
}