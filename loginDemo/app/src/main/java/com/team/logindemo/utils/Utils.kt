package com.team.logindemo.utils

import android.content.Context
import android.os.Environment
import java.io.*

/**
 * 工具类
 */
object Utils {

    /**
     * 保存文本到文件里
     * @param text 文本1
     * @param text2 文本2
     * @return 是否保存成功
     */
    fun saveText(text:String, text2:String): Boolean {
        val info = "$text##$text2"
        val file = File("data/data/com.team.logindemo/info.txt")
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(info.toByteArray())
            fileOutputStream.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
    fun saveTextToSdcard(text:String, text2:String): Boolean {
        val info = "$text##$text2"
        // val file = File("mnt/sdcard/info.txt")
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "/info2.txt")
        try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(info.toByteArray())
            fileOutputStream.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
    fun saveTextByContext(context: Context, text:String, text2:String): Boolean {
        val info = "$text##$text2"
        try {
            // val file = File("data/data/com.team.logindemo/info.txt")
            // 使用上下文获取应用相关的私有路径,路径地址为包名/files/文件名
            // val file = File(context.filesDir.absolutePath + "/info2.txt")
            // val fileOutputStream = FileOutputStream(file)
            val fileOutputStream = context.openFileOutput("info3.txt", Context.MODE_PRIVATE)
            fileOutputStream.write(info.toByteArray())
            fileOutputStream.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
    /**
     * 读取文件
     */
    fun readText(): List<String>? {
        val file = File("data/data/com.team.logindemo/info.txt")
        try {
            val fileInputStream = FileInputStream(file)
            val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
            val readRes = bufferedReader.readLine()
            return readRes.split("##")
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    fun readTextBySdcard(): List<String>? {
        try {
            val file = File(Environment.getExternalStorageDirectory().absolutePath + "/info2.txt")
            val fileInputStream = FileInputStream(file)
            val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
            val readRes = bufferedReader.readLine()
            return readRes.split("##")
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    fun readTextByContext(context: Context): List<String>? {

        try {
            // val file = File("data/data/com.team.logindemo/info.txt")
            // val file = File(context.filesDir.absolutePath + "/info2.txt")
            // val fileInputStream = FileInputStream(file)
            val fileInputStream = context.openFileInput("info2.txt")
            val bufferedReader = BufferedReader(InputStreamReader(fileInputStream))
            val readRes = bufferedReader.readLine()
            return readRes.split("##")
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
