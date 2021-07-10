package com.eria.http

/**
 * head request傳來時的head
 * bod request傳來時的body
 * url request的url
 * query request的的查詢
 * routeParameters request的path 動態變數查詢 參考網址 https://ktor.io/docs/requests.html#route_parameters
 */
data class Request(
        val head: Map<String, String>,
        val body: String,
        val url: String,
        val query: Map<String, List<String>>,
        val routeParameters: Map<String,String>,
        val cookie:Map<String,String>// 透過key撈資料 沒資料回傳null
)