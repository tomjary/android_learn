package com.team520.ipdialer

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        btn_set_pre.setOnClickListener {
            val pre = et_pre.text.toString().trim()
            if (pre == "") {
                toast("请输入前缀")
                return@setOnClickListener
            }
            edit.putString("pre", pre)
            edit.apply()
            toast("设置成功")
        }
        btn_sendbroadcast.onClick {
            // 发送无序广播
            val tempIntent = Intent()
            tempIntent.action = "com.team520.broadcast"
            // 携带数据
            tempIntent.putExtra("name", "fwr")
            sendBroadcast(tempIntent)
        }
        btn_sendorderedbroadcast.onClick {
            // 发送有序广播
            val tempIntent = Intent()
            tempIntent.action = "com.team520.sendrice"
            // 收到广播时需要的权限
            val receiverPermission: String? = null
            // 最终的广播接收者
            val resultReceiver: BroadcastReceiver? = null
            // 处理最终的广播接收者用单的handler,如果传null,会在主线程处理
            val scheduler: Handler? = null
            // 初始化的数据
            val initialData: String = "发送广播200"
            sendOrderedBroadcast(
                tempIntent,
                receiverPermission,
                resultReceiver,
                scheduler,
                Activity.RESULT_OK,
                initialData,
                null
            )
        }

    }
}
