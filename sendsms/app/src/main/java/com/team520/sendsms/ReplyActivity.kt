package com.team520.sendsms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_reply.*

class ReplyActivity : AppCompatActivity() {
    private val replies = arrayOf(
        "你好",
        "我现在有事，稍后再和你联系",
        "明天早上一起吃饭",
        "好的，多谢。下次有空一起吃饭"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply)
        lv_reply.adapter = ArrayAdapter(applicationContext, R.layout.item_reply, replies)
        lv_reply.setOnItemClickListener { adapterView, view, posion, id ->
            // 获取内容
            val smsBody = replies[posion]
            val data = Intent()
            data.putExtra("sms_body", smsBody)
            setResult(Constants.REPLY_RESULT_CODE, data)
            // 关闭页面回传数据
            finish()
        }
    }

}
