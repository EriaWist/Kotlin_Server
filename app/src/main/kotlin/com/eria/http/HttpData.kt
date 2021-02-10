package com.eria.http


class HttpData {
    constructor(path: String, method: Array<MethodType>, response: ((Request) -> Response)) {

        if (method.size==0) {
            print("HttpData : Error method is zero please use arrayof(MethodType.GET or MethodType.POST)")
            return
        }
        this.path = path
        this.response = response
        this.method = method
    }
    var path:String?=null
    var method:Array<MethodType>?=null
    var response: ((Request) -> Response)? =null
}