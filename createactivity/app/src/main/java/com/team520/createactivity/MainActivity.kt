package com.team520.createactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_call.setOnClickListener(BtnCallListener())
        btn_open_second.setOnClickListener {
            // 开启自己应用的activity
            val intent = Intent(applicationContext, SecondActivity::class.java)

            // 开启其他应用的activity
            // val intent = Intent()
            // intent.setClassName("com.team.msgddos", "com.team.msgddos.MainActivity")
            startActivity(intent)
        }
        btn_open_third.onClick {
            val intent = Intent()
            intent.action = "com.team520.thirdactivity"
            intent.data = Uri.parse("data_third:i love you")
            startActivity(intent)
        }
    }
    inner class BtnCallListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:" + 110)
            startActivity(intent)
        }
    }
}
