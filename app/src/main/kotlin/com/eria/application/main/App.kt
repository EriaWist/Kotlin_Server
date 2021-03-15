/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.eria.application.main


import com.eria.http.createHttpServer
import tornadofx.*



fun main(args: Array<String>) {

    var enteredString= checkInputRange(1..2,"Select use gui or cli", arrayOf("gui","cli"))
        if (enteredString==1)
        {
            launch<GuiApp>(args)
            createHttpServer().stop()
        }
        else if (enteredString==2)
        {
            openCli()
        }


}


