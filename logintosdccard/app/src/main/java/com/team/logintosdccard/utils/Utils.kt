package com.team.logintosdccard.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object Utils {
    fun saveInfo2Sdcard(info: String, info2: String): Boolean {
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "/infos1.txt")
        return try {
            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write("$info##$info2".toByteArray())
            fileOutputStream.close()
            true
        }catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun saveInfoByContext(context: Context, info: String, info2: String): Boolean {
        return try {
            val fileOutputStream = context.openFileOutput("infos.txt", Context.MODE_PRIVATE)
            fileOutputStream.write("$info##$info2".toByteArray())
            fileOutputStream.close()
            true
        }catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
