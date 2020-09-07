package com.team520.sendsms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_contact.onClick {
            val contactIntent = Intent(applicationContext, ContactActivity::class.java)
            startActivityForResult(contactIntent, Constants.CONTACT_REQUEST_CODE)
        }
        btn_reply.onClick {
            val replyIntent = Intent(applicationContext, ReplyActivity::class.java)
            startActivityForResult(replyIntent, Constants.REPLY_REQUEST_CODE)
        }

        btn_send.onClick {
            val smsManager = SmsManager.getDefault()
            val phone = et_number.text.toString()
            val smsBody = et_content.text.toString()
            if (phone == "") {
                toast("请输入手机号")
                return@onClick
            }
            if (smsBody == "") {
                toast("请输入短信内容")
                return@onClick
            }
            /**
             * scAddress 短信服务中心的号码.如果传入null,就使用默认的号码
             * sentIntent 发送回执
             * deliveryIntent 对方的接收回执
             */
            smsManager.sendTextMessage(phone, null, smsBody, null, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 当开启的activity关闭后,会走这个方法

        // 获取到联系人页面传回来的数据
        when (requestCode) {
            Constants.CONTACT_REQUEST_CODE -> {
                val phone = data?.getStringExtra("phone")
                et_number.setText(phone)
            }
            Constants.REPLY_REQUEST_CODE -> {
                val smsBody = data?.getStringExtra("sms_body")
                et_content.setText(smsBody)
            }
        }
    }
}
