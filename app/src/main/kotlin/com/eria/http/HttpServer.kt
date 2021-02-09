package com.eria.http
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

    fun addRouting(path:String,method:Array<MethodType>,response:(request:Request)->Response)
    {
        //接受處理
        val request=Request()
        val response = response(request)
        //回傳處理

    }
    fun star(port:Int){

    }
}