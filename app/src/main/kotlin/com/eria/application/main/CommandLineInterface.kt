package com.eria.application.main
import com.eria.database.FileName
import com.eria.database.createFileManager
import com.eria.http.MethodType
import com.eria.http.Response
import com.eria.http.createHttpServer
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

fun openCli(){


    println("請輸入數字[1024..65535]")
    val port = checkInputRange(1024..65535)

    val http = createHttpServer()
    println("開啟$port")
    http.addRouting("/", MethodType.GET) { request ->
        val response = Response()
        val data = createFileManager().getDataOf(FileName.GET_DATA.name)
        if (request.query.isEmpty())
        response.body = data
         print(Json.encodeToString<String>(data))
        response
    }
    http.star(port)
}
fun checkInputRange(range: IntRange,title:String,optionsArray: Array<String>):Int
{
    println("$title :")
    for ((index, value) in optionsArray.withIndex())
    {
        println("  ${index+1}: ${value}")
    }
    print("Enter selection [${range.start}..${range.endInclusive}] : ")
    return checkInputRange(range)
}
fun checkInputRange(range: IntRange):Int
{
    var data =  readLine()?.toIntOrNull()
    while (!range.contains(data)||data==null)
    {
        print("Please enter a value between ${range.start} and ${range.endInclusive} : ")
        data =  readLine()?.toIntOrNull()
        println()
    }
    return data
}
fun main() {
    openCli()
}