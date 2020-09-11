package com.team520.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.onClick {
            val service = Intent(this@MainActivity, MyService::class.java)
            startService(service)
        }
        btn_stop.onClick {
            val service = Intent(this@MainActivity, MyService::class.java)
            stopService(service)
        }
    }
}
