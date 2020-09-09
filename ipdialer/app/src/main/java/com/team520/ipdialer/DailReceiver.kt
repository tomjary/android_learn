package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author: Administrator
 * @date: 2020/9/7 19:10
 */
class DailReceiver: BroadcastReceiver() {
    override fun onReceive(content: Context?, intent: Intent?) {
        val sp = content?.getSharedPreferences("info", Context.MODE_PRIVATE)
        val pre = sp?.getString("pre", "17951")
        val resultData = resultData
        Log.d("CALL_IN_NOW","onReceive:$resultData")
        setResultData("$pre$resultData")
    }
}