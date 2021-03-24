package com.eria.http

class Request(
        var head: Map<String, String>,
        var body: String,
        var url: String,
        var query: Map<String, List<String>>
)