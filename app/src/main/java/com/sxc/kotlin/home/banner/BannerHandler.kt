package com.sxc.kotlin.home.banner

import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.Log

/**
 * Created by jackey on 2017/8/25.
 */
class BannerHandler(viewPager: ViewPager) : Handler() {

    val TAG: String = BannerHandler::class.java.simpleName

    companion object {
        val STOP = 0
        val START = 1
        val DELAY = 2_000L
    }

    var viewPager: ViewPager? = null

    init {
        this.viewPager = viewPager
    }

    override fun handleMessage(msg: Message?) {
        when (msg?.what) {
            STOP -> stopScroll()
            START -> startScroll()
        }

    }

    private fun stopScroll() {
        Log.d(TAG, "STOP")
        this.removeMessages(START)
    }

    private fun startScroll() {
        Log.d(TAG, "START")
        this.removeMessages(START)

        var currentPager: Int = viewPager?.currentItem!!
        Log.d(TAG, currentPager.toString())

        viewPager?.setCurrentItem(currentPager + 1, true)
        this.sendEmptyMessageDelayed(START, DELAY)
    }
}