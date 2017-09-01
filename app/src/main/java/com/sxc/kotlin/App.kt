package com.sxc.kotlin

import android.annotation.SuppressLint
import android.app.Application

/**
 * Created by sword on 2017/8/31.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application

        @JvmStatic
        fun get(): Application = instance
    }
}