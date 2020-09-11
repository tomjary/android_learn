package com.team520.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @author: Administrator
 * @date: 2020/9/10 16:36
 */
class MyService: Service() {
    val TAG = "SERVICE"
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"onBind:service")
        return null
    }

    override fun onCreate() {
        Log.d(TAG,"onCreate:service")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"onStartCommand:service")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy:service")
        super.onDestroy()
    }
}