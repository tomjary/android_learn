package com.team.httpurlconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var username: String? = null
    var password: String? = null
    val loginUrl = "http://api.xtgyl.cn/index.php/api_v1/api.app.login/login?v=240"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.onClick {
            Thread(Runnable {
                try {
                    username = et_username.text.toString()
                    password = et_password.text.toString()
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
                    username = et_username.text.toString()
                    password = et_password.text.toString()
                    // 如果提交的参数包含中文,必须先url编码
                    // val tempUrl = loginUrl + "?username=${username}&password=${password}"
                    val params = "username=$username&password=$password"
                    val url = URL(loginUrl)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.connectTimeout = 10000
                    // 设置post相关的请求头
                    // 设置请求方式
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
                    // 设置数据长度
                    connection.setRequestProperty("Content-Length", params.length.toString())
                    // 打开输出流
                    connection.doOutput = true
                    // 通过流把请求体写到服务端
                    connection.outputStream.write(params.toByteArray())
                    // 获取响应码
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
