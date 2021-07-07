package com.eria.http

data class Response (
    var body:String="",
    var head:MutableMap<String, String> = mutableMapOf<String, String>()
    )