package com.eria.application.main

import com.eria.database.DatabaseJson
import com.eria.database.FileName
import com.eria.http.HttpServer
import com.eria.http.MethodType
import com.eria.http.Response

fun openCli() {
    println("請輸入數字[1024..65535]")
    val port = checkInputRange(1024..65535)
    val http = HttpServer.instance
    println("開啟$port")
    http.addRouting("/", MethodType.GET) {
        Response().apply { body = DatabaseJson().getData(FileName.GET_DATA.id).toString() }
    }
    for (i in DatabaseJson().getData(FileName.GET_DATA.id)) {
        http.addRouting(i.key, MethodType.GET) {
            Response().apply {
                body = i.value
            }
        }
    }
    http.star(port)
}

fun checkInputRange(range: IntRange, title: String, optionsArray: Array<String>): Int {
    println("$title :")
    for ((index, value) in optionsArray.withIndex()) {
        println("  ${index + 1}: $value")
    }
    print("Enter selection [${range.first}..${range.last}] : ")
    return checkInputRange(range)
}

fun checkInputRange(range: IntRange): Int {
    var data = readLine()?.toIntOrNull()
    while (!range.contains(data) || data == null) {
        print("Please enter a value between ${range.first} and ${range.last} : ")
        data = readLine()?.toIntOrNull()
        println()
    }
    return data
}

fun main() {
    openCli()
}