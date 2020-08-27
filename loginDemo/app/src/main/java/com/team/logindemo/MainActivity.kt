package com.team.logindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import com.team.logindemo.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 加载界面
        setContentView(R.layout.activity_main)

        // 设置点击事件
        bt_login.setOnClickListener(MyListener())
        val s = Environment.getExternalStorageState()
        Log.d("------------","onCreate:$s")
        // 读取文件内容
        // val readText = Utils.readText()
        // val readText = Utils.readTextByContext(this)
        val readText = Utils.readTextBySdcard()


        // 设置edittext里面内容
        if (readText != null) {
            et_username.setText(readText[0])
            et_password.setText(readText[1])
        }




    }
    inner class MyListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            if (username == ""){
                toast("用户名不能为空")
                return
            }
            if (password == ""){
                toast("密码不能为空")
                return
            }
            if (cb_isSave.isChecked) {
                // val saveTextRes = Utils.saveText(username, password)
                // val saveTextRes = Utils.saveTextByContext(this@MainActivity, username, password)
                val saveTextRes = Utils.saveTextToSdcard(username, password)
                if (saveTextRes) {
                    toast("保存成功")
                }else {
                    toast("保存失败")
                }
                toast("正在登陆...")
                toast("登录成功")
            }else {
                toast("正在登陆...")
                toast("登录成功")
            }

        }

    }
}
