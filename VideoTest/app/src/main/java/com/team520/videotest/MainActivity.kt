package com.team520.videotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dueeeke.videocontroller.StandardVideoController
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory
import com.dueeeke.videoplayer.player.VideoViewConfig
import com.dueeeke.videoplayer.player.VideoViewManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        VideoViewManager.setConfig(
            VideoViewConfig.newBuilder()
            //使用使用IjkPlayer解码
            .setPlayerFactory(IjkPlayerFactory.create())
            .build())
        //设置视频地址
//        player.setUrl("rtmp://pull.zichuyun.com/live/3244574_1599356602?txSecret=dc642eb7188925170491753dd6dbe8a2&txTime=5F548B83")
        player.setUrl("http://220.161.87.62:8800/hls/0/index.m3u8")
//        player.setUrl("https://joyexpress.oss-cn-hangzhou.aliyuncs.com/C0040S03_1.mp4")
        val controller = StandardVideoController(this)
        controller.addDefaultControlComponent("临时标题", false)
        player.setVideoController(controller) //设置控制器
        player.start() //开始播放，不调用则不自动播放
    }
    override fun onPause(){
        super.onPause()
        player.pause()
    }
    override fun onResume(){
        super.onResume()
        player.resume()
    }
    override fun onDestroy(){
        super.onDestroy()
        player.release()
    }
    override fun onBackPressed(){
        if (!player.onBackPressed()) {
            super.onBackPressed()
        }
    }
}
