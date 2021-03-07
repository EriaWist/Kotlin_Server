package com.eria.application.main

import com.eria.http.MethodType
import com.eria.http.Response
import com.eria.http.createHttpServer
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import tornadofx.*

class GraphicalUserInterface {
}
class GuiApp : App(MyView::class)
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
            label("請輸入json \n依照以下格式 \n欄位名稱 : SQL資料型態")
            label ("    ")
            textarea()
            label ("    ")
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
                    http.addRouting("/admin", methodType) { request ->
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