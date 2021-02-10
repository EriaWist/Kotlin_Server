package com.eria.http
import io.javalin.Javalin
fun createHttpServer():HttpServer
{
    if (httpServer==null)
    {
        httpServer= HttpServer()
    }
    return httpServer as HttpServer
}
private var httpServer : HttpServer? =null
class HttpServer {

    private
    fun addRouting(path:String,method:Array<MethodType>,response:(request:Request)->Response)
    {


        val request=Request()
        val response = response(request)
        //回傳處理

    }
    fun star(port:Int){

    }


}

fun main(args: Array<String>) {
    //接受處理
    val app = Javalin.create().start(7000)
    app.get("/") { ctx ->


        ctx.result("Hello World") }
    app.get("/t"){ ctx -> ctx.result("Hello test") }
}