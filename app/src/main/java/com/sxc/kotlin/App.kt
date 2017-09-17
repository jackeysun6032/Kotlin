package com.sxc.kotlin

import android.annotation.SuppressLint
import android.app.Application
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.mob.MobSDK
import com.sxc.kotlin.map.APPLocation
import com.sxc.kotlin.utils.SPUtil

/**
 * Created by sword on 2017/8/31.
 */
class App : MultiDexApplication() {


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
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application

        @JvmStatic
        fun get(): Application = instance
    }
}