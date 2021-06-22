package com.eria.http

import io.javalin.Javalin
import io.javalin.http.Context
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import tornadofx.alert

/**
 * 傳統的單例模式Singleton
 */
//fun createHttpServer( ): HttpServer {
//    if (httpServer == null) {
//        httpServer = HttpServer()
//    }
//    return httpServer as HttpServer
//}

//private val httpServer: HttpServer by lazy { HttpServer() }

class HttpServer private constructor() {
    companion object {
        val instance: HttpServer by lazy { HttpServer() }//Kotlin本身給的單例模式Singleton
    }
    private var httpData: MutableMap<String, HttpData> = mutableMapOf()
    fun addRouting(path: String, method: MethodType, response: (request: Request) -> Response) {
        val httpData = HttpData(path, method, response)
        this.httpData[path] = httpData
    }

    /**
     * 這個是將Context與response的函式融合 畢盡不同的httpServer提供Context的時機不同，以及Context內容也不同由此轉換
     */
    private fun getAndSetResponse(ctx: Context, response: ((Request) -> Response)): String {
        val res = response(Request(ctx.headerMap(), ctx.body(), ctx.fullUrl(), ctx.queryParamMap()))//會呼叫使用者寫的閉包
        //將使用者的 Head 設定到 ctx 的 Head
        for (head in res.head) {
            ctx.header(head.key, head.value)
        }
        return res.body
    }//更新動這

    /**
     * 連線的主幹
     */
    private var app: Javalin? = null

    /**
     * 當透過字串輸入port時
     */
    fun star(port: String) {
        if (port.toIntOrNull() != null) {
            star(port.toInt())
        } else {
            alert(Alert.AlertType.WARNING, "請輸入正確的port", "建議使用1000以上65535以下的port", ButtonType.OK)
        }
    }

    /**
     * 當透過int設定port時
     */
    fun star(port: Int) {
        app?.stop()
        app = Javalin.create().start(port)
        for (data in httpData.values) {
            val path = data.path//取得User的path
            val response = data.response//取得User的response回應func
            val method = data.method//取得是get or set
            if (path.isBlank()) {
                print("HttpServer:Error data.path , data.response , data.method is null")
                return
            }
            /**
             * 下列須依照不同httpServer框架自訂，包括getAndSetResponse也需要依照框架進行對ctx的設定
             */
            if (method == MethodType.GET)//判斷
            {
                app?.get(path) { ctx -> ctx.result(getAndSetResponse(ctx, response)) }
            } else {
                app?.post(path) { ctx -> ctx.result(getAndSetResponse(ctx, response)) }
            }
        }
    }

    /**
     * 輸入樣式 /path/test/{變數}/{變數2}
     * 將 Route Path 分割出動態變數回傳各個變數陣列Arrary[0]為path,Arrary[1]為第一個變數以此類推
     * 回傳 各個變數的陣列
     * Arrary[0] = "/path/test",
     * Arrary[1] = "變數",
     * Arrary[2] = "變數2"
     */
    fun routeParameters(path: String):List<String>{
        // 分割 /{ 和 } 並且將 "" 空字串 與 " " 空白字串 剔除 最後將 null 也剔除
        return path.split("/{","}").filter { !it.equals("") && !it.equals(" ") }.filterNotNull()
    }

    /**
     * 停下server
     */
    fun stop() {
        app?.stop()
    }


}


fun main(args: Array<String>) {
    //接受處理
//    val app = Javalin.create().start(7000)
//    app.get("/") { ctx ->
//
//        ctx.result("Hello World"+ctx.headerMap()) }
//    app.post("/"){ ctx -> ctx.result("Hello test"+ctx.body()) }
//    val httpServer = HttpServer()

    val httpServer = HttpServer.instance
    print(httpServer.routeParameters("/path/test/{變數}/{變數2}"))
//    httpServer.addRouting("/", MethodType.GET) { request ->
//        val response = Response()
//        response.body = "test123"
//        response
//    }
//    httpServer.star(5678)
}