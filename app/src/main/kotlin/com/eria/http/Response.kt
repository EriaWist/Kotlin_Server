package com.eria.http

data class Response (
    var body:String="",
    var head:MutableMap<String, String> = mutableMapOf<String, String>(),
    val cookie:MutableMap<String,String?> = mutableMapOf<String, String?>()// 透過key value 設定  透過將vlue設定為null 為刪除
    )