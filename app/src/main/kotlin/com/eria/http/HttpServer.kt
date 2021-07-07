package com.eria.http

import io.javalin.Javalin
import io.javalin.http.Context
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import tornadofx.alert

//使用 object 是為了實現 單例模式Singleton
object HttpServer  {
    private var httpData: HashSet<HttpData> = hashSetOf()
    fun addRouting(path: String, method: MethodType, response: (request: Request) -> Response) {
        val httpData = HttpData(path, method, response)
        this.httpData.add(httpData)
    }

    /**
     * 這個是將Context與response的函式融合 畢盡不同的httpServer提供Context的時機不同，以及Context內容也不同由此轉換
     * ctx 是抓 Javalin的標頭
     * routeParametersList 是抓 route 的動態變數
     * response 使用者的 func
     */
    private fun getAndSetResponse(ctx: Context,routeParametersList:List<String>, response: ((Request) -> Response)): String {
        val pathMap = mutableMapOf<String,String>()
        for (data in routeParametersList){
            if (data!=routeParametersList.first())//過濾第一筆資料 因為第一筆為路徑 第二筆才開始為變數
            {
                pathMap[data] = ctx.pathParam(data)//去ctx取資料
            }
        }
        val res = response(Request(ctx.headerMap(), ctx.body(), ctx.fullUrl(), ctx.queryParamMap(),pathMap))//會呼叫使用者寫的閉包
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
        for (data in httpData) {
            var path = ""//取得User的path
            val listPath = routeParameters(data.path)
            //動態變數設定
            for(data in listPath)
            {
                if (data==listPath.first()) {//因為第一個元素是正常的
                    path = data
                }
                else{//後面才是route變數的部分
                    path += "/:${data}"//依照使用的http服務做更改
                }
            }
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
                app?.get(path) { ctx -> ctx.result(getAndSetResponse(ctx,listPath, response)) }
            } else {
                app?.post(path) { ctx -> ctx.result(getAndSetResponse(ctx,listPath, response)) }
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
        var pathList = path.split("/{","}").filter { !it.equals("") && !it.equals(" ") }.filterNotNull().toMutableList()
        if (!pathList.first().contains("/"))//判斷第一行是否為正確的路徑
        {
            pathList.add(0, "/") //當path只有這樣 /{變數} 時 / 會被切割
        }
        return pathList
    }
    /**
     * 停下server
     */
    fun stop() {
        app?.stop()
    }
}

