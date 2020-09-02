package com.team.httpurlconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val loginUrl = "http://api.xtgyl.cn/index.php/api_v1/api.app.login/login?v=240"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        btn_login.setOnClickListener {
            Thread(Runnable {
                try {
                    // 如果提交的参数包含中文,必须先url编码
                    // val tempUrl = loginUrl + "?username=${username}&password=${password}"
                    val tempUrl = loginUrl + "?username="+ URLEncoder.encode(username,"utf-8") +"&password="+ URLEncoder.encode(password,"utf-8")
                    val url = URL(tempUrl)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connectTimeout = 10000
                    val code = connection.responseCode
                    if (code == 200) {
                        val inputStream = connection.inputStream
                        val result = Utils.getStringFromStream(inputStream)
                        runOnUiThread { toast(result) }
                    }
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }).start()
        }
        btn_loginPost.setOnClickListener {
            Thread(Runnable {
                try {
                    // 如果提交的参数包含中文,必须先url编码
                    // val tempUrl = loginUrl + "?username=${username}&password=${password}"
                    val tempUrl = loginUrl + "?username="+ URLEncoder.encode(username,"utf-8") +"&password="+ URLEncoder.encode(password,"utf-8")
                    val url = URL(tempUrl)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.connectTimeout = 10000
                    val code = connection.responseCode
                    if (code == 200) {
                        val inputStream = connection.inputStream
                        val result = Utils.getStringFromStream(inputStream)
                        runOnUiThread { toast(result) }
                    }
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }).start()
        }
    }
}
