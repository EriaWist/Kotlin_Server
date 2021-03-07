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



fun main(args: Array<String>) {

    var enteredString= checkInputRange(1..2,"Select use gui or cli", arrayOf("gui","cli"))
        if (enteredString==1)
        {
            launch<GuiApp>(args)
        }
        else if (enteredString==2)
        {
            openCli()
        }

    createHttpServer().stop()
}


