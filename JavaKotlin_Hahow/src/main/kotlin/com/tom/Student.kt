package com.kotlin

import java.util.*

fun main() {
//    userInput()

    Student.pass = 50
    val stu = Student("Hank", 60, 99)
    val stu1 = Student("Jane", 44, 68)
    val stu2 = Student("Eric", 30, 49)

    val gstu = GraduateStudent("Jack", 55, 65, 60)
    gstu.print()

    stu.print()
    stu1.print()
    stu2.print()

    val test = 123
    println("Test is $test")
    println("High score: ${stu.highest()}")
}

class GraduateStudent(name: String?, english: Int, math: Int, thesis: Int) : Student(name, english, math) {
    companion object {
        var pass = 70
    }

    override fun print() {
        println("$name\t$english\t$math\t${getAverage()}\t${passOrFailed()}\t${grading()}")
    }

    override fun passOrFailed() = if (getAverage() >= pass) "PASS" else "FAILED"
}

// 預設是 final 的，所以要加 open 才能被繼承
open class Student(var name: String?, var english: Int, var math: Int) {

    // 類似 static
    companion object {

        @JvmStatic // 這樣在 java 中就可以直接存取，而不是透過 Companion.getPass()
        var pass = 60

        fun test() {
            "testing"
        }
    }

    // open 讓方法可以被覆寫
    open fun print() {
        println("$name\t$english\t$math\t${getAverage()}\t${passOrFailed()}\t${grading()}")
    }

    open fun passOrFailed() =
        if (getAverage() >= pass) "PASS" else "FAILED"

    // 簡潔寫法，讓 fun 直接等於運算結果
    // 在 kotlin 中存取權限修飾如果沒有寫預設是 public，與 java 不同
    // private protect 與 java 一樣
    // internal 代表是整個模組 module，kotlin 特有
    internal fun grading() = when (getAverage()) {
        in 90..100 -> 'A'
        in 80..89 -> 'B'
        in 70..79 -> 'C'
        in 60..69 -> 'D'
        else -> 'F'
    }

    /*fun grading(): Char {
        // when 語法，取代了 switch 語法
        return when (getAverage()) {
//            90, 91, 92, 93, 94 -> 'A' // 可以使用逗號隔開
            in 90..100 -> 'A' // 用 in 語法
            in 80..89 -> 'B'
            in 70..79 -> 'B'
            in 60..69 -> 'B'
            else -> 'F'
        }
    }*/

    fun getAverage() = (english + math) / 2

    /*fun getAverage(): Int {
        return (english + math) / 2
    }*/

    fun nameCheck() = name?.length
    /*fun nameCheck() {
        println(name?.length)
    }*/

    fun highest() = if (english > math) {
        println("english")
        english
    } else {
        println("math")
        math
    }

    /*fun highest(): Int {
        var max = if (english > math) {
            println("english")
            english // 最後一行會自動變成會傳值
        } else {
            println("math")
            math // 最後一行會自動變成會傳值
        }
        *//*if (english > math) {
            max = english
        } else {
            max = math
        }*//*
        return max
    }*/
}

private fun userInput() {
    // in 關鍵字，所以可以加入重音符號
    val scanner = Scanner(System.`in`)
    print("Please enter student's name: ")
    var name = scanner.next()
    print("Please enter student's english: ")
    var english = scanner.nextInt()
    print("Please enter student's math: ")
    var math = scanner.nextInt()

    val stu = Student(name, english, math)
    stu.print()
    stu.nameCheck()
}