package com.eria.http

class Request {
    constructor(head: Map<String, String>,body: String, url: String) {
        this.head=head
        this.body=body
        this.url = url
    }

    var body:String
    var head:Map<String, String>
    var url:String
}