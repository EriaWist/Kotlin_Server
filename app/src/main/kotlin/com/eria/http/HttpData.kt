package com.eria.http


class HttpData {
    constructor(path: String, method: MethodType, response: ((Request) -> Response)) {
        this.path = path
        this.response = response
        this.method = method
    }
    var path:String
    var method:MethodType
    var response: ((Request) -> Response)
}