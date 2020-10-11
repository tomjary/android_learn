package com.team520.videotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dueeeke.videocontroller.StandardVideoController
import com.dueeeke.videocontroller.component.LiveControlView
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory
import com.dueeeke.videoplayer.player.VideoViewConfig
import com.dueeeke.videoplayer.player.VideoViewManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //设置视频地址
        player.setUrl("http://220.161.87.62:8800/hls/0/index.m3u8")
        val controller = StandardVideoController(this)
        controller.addDefaultControlComponent("临时标题", true)
        //在LogCat显示调试信息
        controller.addControlComponent(PlayerMonitor())
        //根据是否为直播决定是否需要滑动调节进度
        controller.setCanChangePosition(false)

        controller.addControlComponent(LiveControlView(this))//直播控制条
        player.setVideoController(controller) //设置控制器
        player.start() //开始播放，不调用则不自动播放
        btn_start.onClick {
            val videoUrl = ed_url.text.toString()
            if (videoUrl.isEmpty()) {
                toast("请输入地址")
                return@onClick
            } else {
                player.release()
                player.setUrl(videoUrl)
                player.start()
            }

        }
        btn_clear.onClick {
            ed_url.setText("")
        }
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
