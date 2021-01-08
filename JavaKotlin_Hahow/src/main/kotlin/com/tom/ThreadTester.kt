package com.tom


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main() {
    Thread({
        for (i in 1..10) {
            println("Thread: ${i}")
            Thread.sleep(50)
        }
    }).start()

    thread {
        for (i in 1..10) {
            println("thread() ${i}")
            Thread.sleep(50)
        }
    }

    GlobalScope.launch {
        for(i in 1..10){
            println("Coroutines: ${i}")
            delay(50L)
        }
    }
}