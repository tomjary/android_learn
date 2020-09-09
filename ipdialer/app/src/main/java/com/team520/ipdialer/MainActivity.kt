package com.team520.ipdialer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        btn_set_pre.setOnClickListener {
            val pre = et_pre.text.toString().trim()
            if(pre == "") {
                toast("请输入前缀")
                return@setOnClickListener
            }
            edit.putString("pre", pre)
            edit.apply()
        }


    }
}
