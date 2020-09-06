package com.team520.projectrp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_check.onClick {
            val name = et_name.text.toString()
            var isMan = true
            if (rb_woman.isChecked) {
               isMan = false
            }
            if (name == ""){
                toast("请输入姓名")
                return@onClick
            }
            val tempIntent = Intent(applicationContext, ResultActivity::class.java)
            // putExtra方法可以传递数据,可以传递基本类型数据和数组等等
            tempIntent.putExtra("name", name)
            tempIntent.putExtra("isMan", isMan)
            startActivity(tempIntent)
        }
    }
}
