package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author: Administrator
 * @date: 2020/9/9 13:22
 */
class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("BOOT_START","手机启动了")
        // 开机启动
        val tempIntent = Intent(context, MainActivity::class.java)
        // 现在是在广播接收者中创建一个activity,当前应用没有任何activity在运行,所以不存在任务栈
        // 需要通过制定一个flag,在创建activity的同时创建一个任务栈
        tempIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(tempIntent)
    }
}