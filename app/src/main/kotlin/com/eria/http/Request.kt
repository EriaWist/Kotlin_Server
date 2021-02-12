package com.eria.http

class Request {
    constructor(head: Map<String, String>,body:String)
    {
        this.head=head
        this.body=body
    }

    var body:String
    var head:Map<String, String>

}