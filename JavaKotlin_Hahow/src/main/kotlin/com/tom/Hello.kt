package com.tom

import com.kotlin.Student

fun main(args: Array<String>) {

    val stu = Student("Hank", 50, 60)

    var s: String? = "abcde" // ? 代表可能為 null
//    s = null // 因為 s 是不能為 null，除非給予 ?
    println(s!!.length) // !! 代表如果 NPE 就出錯誤訊息吧
    println(s?.get(3)) // ? 如果 NPE 就印出 null，不會出錯
    println(s?.substring(3))

    // 加入建構子
//    val h = Human(66.5f, 1.7f)
    val h = Human(height = 1.7f, weight = 66.5f) // 若不想依序放入，也可以直接指定
    println(h.bmi())

    val score = 88
    print(score > 60)

    val c: Char = 'A' // Kotlin 的 char 不屬於整數，所以要 toInt()
    println(c.toInt() > 60)

    /*
    val h = Human()
    h.hello()

    // kotlin 並沒有基本資料型別，宣告僅有 var 和 val，型別會自動推斷
    // 常數 val：不能改變
    val age: Int = 19
//    age = 20 // 編譯出錯，因為值不能改變

    // 變數 var：可以改變
    var weight = 66.5f
    */

    /*
    println("Hello kotlin")

    // Kotlin 呼叫建構子不需要 new
    Human().hello()
    */
}

// 將名稱換成 Human，避免跟 Java 中的 Person 衝突，主要原因是 Java 與 Kotlin 共用資源（同樣的 package name）的關係，不然改動 package 也行
// 主要建構子，可以省略 constructor，加上初始值代表建構子可以變成非必要
class Human constructor(var name: String? = "", var weight: Float, var height: Float) {

    // 在主要建構子完成後執行
    init {
        print("test $weight") // 可以用 $ 帶入參數
    }

    // 次要建構子：不可以使用 var 或 val
//    constructor(name: String, weight: Float, height: Float) : this(weight, height)

    // Kotlin 回傳的方式是在 () 後加上:
    fun bmi(): Float {
        var bmi = weight / (height * height)
        return bmi
    }

    // 若無回傳值，不需要加入 void
    fun hello() {
        println("Hello kotlin")
    }
}
