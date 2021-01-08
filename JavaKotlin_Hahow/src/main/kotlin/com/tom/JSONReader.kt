package com.tom

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val url =
        URL("http://data.tycg.gov.tw/opendata/datalist/datasetMeta/download?id=f4cc0b12-86ac-40f9-8745-885bddc18f79&rid=0daad6e6-0632-44f5-bd25-5e1de1e9146f")

    // 連到網路，直接讀取網址內容
    url.readText()
    println(url.readText())

    /*// 在 with 區塊內可以直接使用物件的方法
    with(url.openConnection() as HttpURLConnection) {

//        val br = BufferedReader(InputStreamReader(inputStream))
        val br = inputStream.bufferedReader() // 與上一行相同意思
            .lines() // 呼叫串流
            .forEach {
                println(it)
            }
    }*/

//    val connection = url.openConnection() as HttpURLConnection
//    val br = BufferedReader(InputStreamReader(connection.inputStream))
//    var line = br.readLine()
//    val json = StringBuffer()
//    while (line != null) {
//        json.append(line)
//        line = br.readLine()
//    }
//    println(json.toString())
}