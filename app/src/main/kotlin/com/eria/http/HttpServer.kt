package com.eria.http
import io.javalin.Javalin
import io.javalin.http.Context

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

    private var httpData:ArrayList<HttpData> = ArrayList()
    fun addRouting(path:String,method:MethodType,response:(request:Request)->Response)
    {
        val httpData = HttpData(path,method,response)
        this.httpData.add(httpData)
    }

    fun getAndSetResponse(ctx:Context,response: ((Request) -> Response)):String{
        var response = response(Request(ctx.headerMap(),ctx.body()))
        //設定 ctx 的 Head
        for (head in response.head)
        {
            ctx.header(head.key,head.value)
        }
        return response.body
    }//更新動這

    fun star(port:Int){
        val app = Javalin.create().start(port)
        for (data in httpData)
        {
            val path = data.path
            val response = data.response
            val method = data.method
            if (path==null || response==null || method==null) {
                print("HttpServer:Error data.path , data.response , data.method is null")
                return
            }
            if (method == MethodType.GET)
            {
                app.get(path){ctx -> ctx.result(getAndSetResponse(ctx,response)) }
            }
            else{
                app.post(path){ctx -> ctx.result(getAndSetResponse(ctx,response)) }
            }
        }
    }


}


fun main(args: Array<String>) {
    //接受處理
//    val app = Javalin.create().start(7000)
//    app.get("/") { ctx ->
//
//        ctx.result("Hello World"+ctx.headerMap()) }
//    app.post("/"){ ctx -> ctx.result("Hello test"+ctx.body()) }
    var httpServer = createHttpServer()
    httpServer.addRouting("/",MethodType.GET){request ->
        var response = Response()
        request.body="test123"
        response
    }
    httpServer.star(5678)
}