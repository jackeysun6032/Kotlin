package com.sxc.kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.mob.MobSDK
import com.sxc.kotlin.bean.meizhi.MyObjectBox
import com.sxc.kotlin.map.APPLocation
import com.sxc.kotlin.utils.SPUtil
import io.objectbox.BoxStore
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

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
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        MobSDK.init(this,"210901bebdc6a" , "c718f0fbde990d39ff554a6e50a836e5")

        boxStore = MyObjectBox.builder().androidContext(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        lateinit var instance: App

        @JvmStatic
        fun get(): App = instance

    }
}