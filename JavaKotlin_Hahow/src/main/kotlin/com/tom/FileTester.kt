package com.tom

import java.io.File

fun main() {

    File("data.txt").bufferedReader().lines().forEach {
        println(it)
    }
//    write()
}

private fun write() {
    //    File("output.txt").writeText("abc123") // 直接寫出字串

    // 使用 use 會自動關閉所以可以省掉 flush() close()
    File("output.txt").printWriter().use {
        it.println("1st line")
        it.println("2nd line")
        it.println("3rd line")
    }

    File("output.txt").bufferedWriter().use {
        it.write("1st line\n")
        it.write("2nd line\n")
        it.write("3rd line\n")
    }
}