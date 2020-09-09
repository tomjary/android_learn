package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author: Administrator
 * @date: 2020/9/9 13:22
 */
class SDcardStateReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action.equals("android.intent.action.MEDIA_MOUNTED")){
            Log.d("SDCARD_STATE","SD卡安装上了")
        } else if (action.equals("android.intent.action.MEDIA_UNMOUNTED")){
            Log.d("SDCARD_STATE","SD卡卸下来了")
        }
    }
}