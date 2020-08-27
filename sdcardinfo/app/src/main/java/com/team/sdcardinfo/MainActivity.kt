package com.team.sdcardinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.format.Formatter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        val freeSpace = externalStorageDirectory.freeSpace
        val totalSpace = externalStorageDirectory.totalSpace
        val freeSize = Formatter.formatFileSize(this, freeSpace)
        val totalSize = Formatter.formatFileSize(this, totalSpace)
        tv_sd_free_space.text = "剩余空间:$freeSize"
        tv_sd_total_space.text = "总共空间:$totalSize"
    }
}
