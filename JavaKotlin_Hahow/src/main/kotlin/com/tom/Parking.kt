package com.tom

import java.time.Duration
import java.time.LocalDateTime

fun main() {
    val enter: LocalDateTime = LocalDateTime.of(
        2018, 12, 25, 8, 0, 0
    )

    val leave: LocalDateTime = LocalDateTime.of(
        2018, 12, 25, 10, 8, 0
    )

    var car = Car("AAA-0001", enter)
    car.leave = leave // 會去呼叫 leave 的 setter 方法
    println(car.duration())
    var hours = Math.ceil(car.duration()/60.0).toLong()
    println(hours)

}

class Car(val id: String, val enter: LocalDateTime) {
    var leave: LocalDateTime? = null
        set(value) { // 客製化 leave 的 setter
            if (enter.isBefore(value))
            // 這裡不可以使用 this 去呼叫，要用 field
//                this.leave = value // 這樣的語法還是會去呼叫 setter 方法，會變成無窮迴圈
                field = value
        }

    fun duration() = Duration.between(enter, leave).toMinutes()
}