package com.sxc.kotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import cn.bmob.v3.Bmob
import com.alibaba.android.arouter.launcher.ARouter
import com.mob.MobSDK
import com.sxc.kotlin.bean.meizhi.MyObjectBox
import com.sxc.kotlin.map.APPLocation
import com.sxc.kotlin.utils.SPUtil
import io.objectbox.BoxStore

/***
* Created by sword on 2017/8/31.
*/
class App : MultiDexApplication() {

    private lateinit var boxStore: BoxStore

    fun getBoxStore()=boxStore

    override fun onCreate() {
        super.onCreate()
        instance = this
        SPUtil.init()
        //初始化定位
        APPLocation.initLocation()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        MobSDK.init(this,"210901bebdc6a" , "c718f0fbde990d39ff554a6e50a836e5")
        Bmob.initialize(this, "faf95a1df63626b8ba61563b1b288cdf")

        boxStore = MyObjectBox.builder().androidContext(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App

        @JvmStatic
        fun get(): App = instance
    }
}