package com.team.httpurlconnection

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


/**
 * @author: Administrator
 * @date: 2020/9/2 16:21
 */
object Utils {

    @Throws(IOException::class)
    fun getStringFromStream(inputStream: InputStream): String {
        val baso = ByteArrayOutputStream()
        var len = -1
        val buffer = ByteArray(1024)
        while (inputStream.read(buffer).also({ len = it }) != -1) {
            baso.write(buffer, 0, len)
        }
        inputStream.close()
        val byteArray: ByteArray = baso.toByteArray()
        return String(byteArray)
    }
}
