package com.team520.projectrp

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*


class ResultActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        // 获取intent
        val isMan = intent.getBooleanExtra("isMan", true)
        val name = intent.getStringExtra("name")
        tv_info.text = if (isMan) "姓名:$name,性别:男" else "姓名:$name,性别:女"
        tv_result.text = "人品值${renpinCheck()}"
    }
    fun renpinCheck(): Int {
        val rd = Random()
        return rd.nextInt(100)
    }
}
