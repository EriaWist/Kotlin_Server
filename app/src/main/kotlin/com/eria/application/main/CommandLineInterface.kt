package com.eria.application.main
import com.eria.database.DatabaseJson
import com.eria.database.FileName
import com.eria.http.MethodType
import com.eria.http.Response
import com.eria.http.createHttpServer

fun openCli(){


    println("請輸入數字[1024..65535]")
    val port = checkInputRange(1024..65535)

    val http = createHttpServer()
    println("開啟$port")
    http.addRouting("/", MethodType.GET) { request ->
        val response = Response()
        response.body =  DatabaseJson().getData(FileName.GET_DATA.id).toString()
        response
    }
    for(i in DatabaseJson().getData(FileName.GET_DATA.id))
    {
        http.addRouting(i.key,MethodType.GET){
            val response = Response()
            response.body = i.value
            response
        }
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