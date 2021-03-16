package com.eria.database

import java.io.File
import java.io.FileWriter
import java.util.*

class FileManager {

    private val dirPath = System.getProperty("user.dir")
    val path = "${dirPath}/dataBase"

    internal constructor(){
        var dirFile = File(path)
        dirFile.mkdirs()
        createFile("GetData")
        createFile("PostData")
        createFile("Schema")
    }

    fun createFile (fileName: String)
    {
       val dirFile = File(path+'/'+fileName)
        dirFile.createNewFile()
    }

    fun getDataDir():String{
        return dirPath
    }

    fun  getDataOf(fileName:String):String
    {
        val path = File(path+"/"+fileName)
        val reader =  Scanner(path);
        var strData = ""
        while (reader.hasNextLine()) {
            strData = strData+reader.nextLine();
        }
        reader.close();
        return strData
    }
    fun setDataOf(fileName:String,data:String)
    {
        var writer =  FileWriter(path+"/"+fileName);
        writer.write(data);
        writer.close();
    }

}
enum class FileName(name:String){
    GET_DATA("GetData"),
    POSTDATA("PostData"),
    SCHEMA("Schema")
}


private var fileManager:FileManager?=null
fun createFileManager():FileManager
{
    if (fileManager==null)
    {
        fileManager=FileManager()
    }
    return fileManager as FileManager
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