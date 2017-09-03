package com.sxc.kotlin

import android.annotation.SuppressLint
import android.app.Application
import com.sxc.kotlin.utils.SPUtil

/**
 * Created by sword on 2017/8/31.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        SPUtil.init()
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application

        @JvmStatic
        fun get(): Application = instance
    }
}