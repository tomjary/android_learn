package com.team520.smscollection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val smsArray = arrayOf(
        "世间没有白走的路，白读的书，每一步都将融进你的骨子里，滋养全新的你。早安",
        "朝着梦想的方向走下去，把生活过成你想象的样子。早安",
        "过好自己的生活，该来的，都在路上。早安",
        "在成长的路上，没有人一下子从平地跃上山顶，大家都是做了万全的准备，再经过漫长地攀爬。早安"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arrayAdapter = ArrayAdapter(this, R.layout.item, smsArray)
        lv_smss.adapter = arrayAdapter
        lv_smss.setOnItemClickListener { adapterView, view, i, l ->
            val smsContent = smsArray[i]
            val tempIntent = Intent()
            tempIntent.action = "android.intent.action.SEND"
            tempIntent.type = "text/plain"
            tempIntent.putExtra("sms_body", smsContent)
            startActivity(tempIntent)
        }
    }
}
