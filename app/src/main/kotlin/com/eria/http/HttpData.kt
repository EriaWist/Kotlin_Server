package com.eria.http

/**
 * @param path:http路由的path
 * @param method:http method
 *
 */
class HttpData(var path: String, var method: MethodType, var response: ((Request) -> Response))

enum class MethodType {
    POST,GET
}