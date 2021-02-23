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

    /**
     * 這個是將Context與response的函式融合 畢盡不同的httpServer提供Context的時機不同，以及Context內容也不同由此轉換
     */
    private fun getAndSetResponse(ctx:Context,response: ((Request) -> Response)):String{

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
            val path = data.path//取得User的path
            val response = data.response//取得User的response回應func
            val method = data.method//取得是get or set
            if (path==null || response==null || method==null) {
                print("HttpServer:Error data.path , data.response , data.method is null")
                return
            }
            /**
             * 下列須依照不同httpServer框架自訂，包括getAndSetResponse也需要依照框架進行對ctx的設定
             */
            if (method == MethodType.GET)//判斷
            {

                app.get(path){ctx ->ctx.result(getAndSetResponse(ctx,response)) }
            }
            else{
                app.post(path){ctx -> ctx.result(getAndSetResponse(ctx,response)) }
            }
        }
    }
    fun stop(){

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
        response.body="test123"
        response
    }
    httpServer.star(5678)
}