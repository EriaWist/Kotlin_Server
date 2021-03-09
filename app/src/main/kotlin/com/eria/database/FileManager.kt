package com.eria.database

import java.io.File
import java.io.FileWriter
import java.util.*

class FileManager {


}

fun main() {

    println(System.getProperty("user.dir"))
    println(System.getProperty("file.separator"))
    val dir = System.getProperty("user.dir")
    val sep = System.getProperty("file.separator")
    var ordForl = "${dir}/data"
    var ordFil = "${dir}/data/filename.txt"
    var file = File(ordForl)
    file.mkdirs()
    file = File(ordFil)
    file.createNewFile()
    var myWriter =  FileWriter(ordFil);
    myWriter.write("Files in Java might be tricky, but it is fun enough!");
    myWriter.close();
    var myReader =  Scanner(file);
    while (myReader.hasNextLine()) {
        var data = myReader.nextLine();
        println(data);
    }
    myReader.close();
    print("第二次")
     ordForl = "${dir}${sep}data"
     ordFil = "${dir}${sep}data${sep}filename.txt"
     file = File(ordForl)
    file.mkdirs()
    file = File(ordFil)
    file.createNewFile()
     myWriter =  FileWriter(ordFil);
    myWriter.write("Files in Java might be tricky, but it is fun enough!");
    myWriter.close();
     myReader =  Scanner(file);
    while (myReader.hasNextLine()) {
        var data = myReader.nextLine();
        println(data);
    }
    myReader.close();
}