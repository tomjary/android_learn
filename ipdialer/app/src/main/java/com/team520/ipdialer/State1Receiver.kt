package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.toast


class State1Receiver: BroadcastReceiver() {
    override fun onReceive(content: Context?, intent: Intent?) {
        val resultData = resultData
        content?.toast("$resultData")
        setResultData("收到广播150")
        // 终止广播,只有有序广播可以中断
        abortBroadcast()
    }
}