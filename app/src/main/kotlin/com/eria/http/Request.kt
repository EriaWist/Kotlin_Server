package com.eria.http

class Request {

    constructor(head: Map<String, String>, body: String, url: String, query: Map<String, List<String>>) {
        this.head = head
        this.body = body
        this.url = url
        this.query = query
    }

    var head:Map<String, String>
    var body:String
    var url:String
    var query:Map<String, List<String>>
}