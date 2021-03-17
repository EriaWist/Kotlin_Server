package com.eria.database

import kotlinx.serialization.*
import kotlinx.serialization.json.*

class DatabaseJson {
    inline fun <reified T> getData(fileName: String):T{
        return Json.decodeFromString<T>(createFileManager().getDataOf(fileName))
    }
    fun savedata(){}
    fun updateDatabase(){}

}


fun main() {
//    print(FileName.GET_DATA.id)
//    print(createFileManager().getDataOf(FileName.GET_DATA.id))
    var aaa = mapOf("asd" to "asd")
    var ssss = arrayOf("ad")
    var sss = createFileManager().getDataOf(FileName.GET_DATA.id)//Json.encodeToString<Map<String,String>>(aaa)
    println(sss)
    try {
        var ddd = Json.decodeFromString<Map<String,ArrayList<String>>>(sss)
        print(ddd)
    }catch (e:Exception){
        print("json Error")
    }
        // print(Json.encodeToString<JsonDataType>(createFileManager().getDataOf(FileName.GET_DATA.id)))
}