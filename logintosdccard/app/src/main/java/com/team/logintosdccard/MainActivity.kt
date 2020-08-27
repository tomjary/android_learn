package com.team.logintosdccard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.team.logintosdccard.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_login.setOnClickListener(LoginListener())
    }
    inner class LoginListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            if (username == ""){
                toast("请输入用户名")
                return
            }
            if (password == ""){
                toast("请输入密码")
                return
            }
            if (is_save.isChecked){
                // 保存信息
                // 获取sd卡的状态,如果状态是Environment.MEDIA_MOUNTED,说明sd卡是可用的
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
                    // 保存到sd卡
                    val saveRes = Utils.saveInfo2Sdcard(username, password)
                    toast(if (saveRes) "保存sd卡成功" else "保存sd卡失败")

                }else {
                    // 保存到包名目录下
                    val saveRes = Utils.saveInfoByContext(this@MainActivity, username, password)
                    toast(if (saveRes) "保存包名目录成功" else "保存包名目录失败")
                }
                toast("登录成功")
            } else {
                toast("登录成功")
            }
        }

    }
}
