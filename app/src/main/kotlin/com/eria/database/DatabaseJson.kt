package com.eria.database

import kotlinx.serialization.*
import kotlinx.serialization.json.*

class DatabaseJson {
     fun getData(fileName: String, select:String?): String {
         val dataMap = Json.decodeFromString<Map<String,String>>(createFileManager().getDataOf(fileName))
         val data = dataMap[select]
         if (data!=null) {
             return data
         }
         else{
             return "can’t select '$select' data"
         }
     }
    fun getData(fileName: String): Map<String,String> {
        return   Json.decodeFromString<Map<String,String>>(createFileManager().getDataOf(fileName))
    }
    fun savedata(fileName: String,key:String,value:String){
        val dataMap = Json.decodeFromString<Map<String,String>>(createFileManager().getDataOf(fileName)).toMutableMap()
        dataMap[key]=value
        createFileManager().setDataOf(fileName, Json.encodeToString<Map<String,String>>(dataMap))
    }

}


fun main() {
//    print(FileName.GET_DATA.id)
//    print(createFileManager().getDataOf(FileName.GET_DATA.id))
    var aaa = mapOf("asd" to "asd")
    var ssss = arrayOf("ad")
    var sss = createFileManager().getDataOf(FileName.GET_DATA.id)//Json.encodeToString<Map<String,String>>(aaa)
    println(sss)
    try {
        var ddd = Json.decodeFromString<Map<String,JsonElement>>(sss)
        val tt = ddd["name"]
        val s=tt
        print(s.toString())
    }catch (e:Exception){
        print("json Error")
    }
        // print(Json.encodeToString<JsonDataType>(createFileManager().getDataOf(FileName.GET_DATA.id)))
}