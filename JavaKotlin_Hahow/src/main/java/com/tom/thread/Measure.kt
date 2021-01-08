package com.tom.thread

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    var counter = 1
    /*var ms = measureTimeMillis {
        // 計算時間
        for (n in 1..100_000) {
            thread { counter++ }
        }
    }*/

    var ms = measureTimeMillis {
        for (n in 1..100_000) {
            GlobalScope.launch {
                counter++
            }
        }
    }
    println(ms)
}