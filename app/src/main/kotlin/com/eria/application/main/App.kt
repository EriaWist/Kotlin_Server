/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.eria.application.main

import com.eria.http.MethodType
import com.eria.http.Response
import com.eria.http.createHttpServer
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import tornadofx.*

class MyApp : App(MyView::class)

fun main(args: Array<String>) {
    var enteredString:String?=null
    println("use gui?(y/n)")
    while (true){
        enteredString = readLine()
        if (enteredString.equals("y"))
        {
            launch<MyApp>(args)
            break
        }
        else if (enteredString.equals("n"))
        {
            var port = readLine()?.toIntOrNull()
            while (port==null)
            {
                println("請輸入數字")
                port = readLine()?.toIntOrNull()
            }
            var http = createHttpServer()
            http.addRouting("/admin", MethodType.GET) { request ->
                var response = Response()
                response.body = "test123"
                response
            }
            http.star(port)
        }
    }
    createHttpServer().stop()
}

class MyView : View() {
    var portField: TextField by singleAssign()
    val texasCities = FXCollections.observableArrayList("GET", "POST")
    val selectedCity = SimpleStringProperty()
    var methodType: MethodType = MethodType.GET
    override val root = vbox {
        hbox {
            label("輸入port")
            portField = textfield()

        }
        label ("")
        combobox(selectedCity, texasCities){
            text { value="請選擇GET,POST" }
        }
        label ("")
        hbox{
            label("請輸入json  : ")
            label ("    ")
            textarea()
        }
        label ("")
        hbox {
            button(" 開始") {
                action {
                    var http = createHttpServer()
                    if (selectedCity.value == "GET") {
                        methodType = MethodType.GET
                    } else {
                        methodType = MethodType.POST
                    }
                    http.addRouting("/", methodType) { request ->
                        var response = Response()
                        response.body = "test123"
                        response
                    }
                         http.star(portField.text)
                }
            }
            button(" 停止") {
                action {
                    var http = createHttpServer()
                    http.stop()

                }
            }
        }
    }

}

