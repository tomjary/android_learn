package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 应用的安装与卸载广播接收
 * @author: Administrator
 * @date: 2020/9/9 13:22
 */
class AppInstallOrUninstallReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val uri = intent?.data
        if (action.equals("android.intent.action.PACKAGE_ADDED")){
            Log.d("APP_INSTALL","您所安装的应用包名是$uri")
        } else if (action.equals("android.intent.action.PACKAGE_REMOVED")){
            Log.d("APP_INSTALL","您所卸载的应用包名是$uri")
        }
    }
}