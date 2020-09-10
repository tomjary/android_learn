package com.team520.ipdialer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.toast


class State3Receiver: BroadcastReceiver() {
    override fun onReceive(content: Context?, intent: Intent?) {
        val resultData = resultData
        content?.toast("$resultData")
    }
}