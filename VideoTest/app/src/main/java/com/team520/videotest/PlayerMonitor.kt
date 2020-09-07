package com.team520.videotest

import android.view.View
import android.view.animation.Animation
import com.dueeeke.videoplayer.controller.ControlWrapper
import com.dueeeke.videoplayer.controller.IControlComponent
import com.dueeeke.videoplayer.util.L

/**
 * @author: Administrator
 * @date: 2020/9/7 14:10
 */
class PlayerMonitor: IControlComponent {
    private var mControlWrapper: ControlWrapper? = null

    override fun attach(controlWrapper: ControlWrapper) {
        mControlWrapper = controlWrapper
    }

    override fun getView(): View? {
        return null
    }

    override fun onVisibilityChanged(isVisible: Boolean, anim: Animation) {
        L.d("onVisibilityChanged: $isVisible")
    }

    override fun onPlayStateChanged(playState: Int) {
        L.d("onPlayStateChanged: $playState")
    }

    override fun onPlayerStateChanged(playerState: Int) {
        L.d("onPlayerStateChanged: $playerState")
    }

    override fun setProgress(duration: Int, position: Int) {
        L.d("setProgress: duration: " + duration + " position: " + position + " buffered percent: " + mControlWrapper!!.bufferedPercentage)
        L.d("network speed: " + mControlWrapper!!.tcpSpeed)
    }

    override fun onLockStateChanged(isLocked: Boolean) {
        L.d("onLockStateChanged: $isLocked")
    }
}