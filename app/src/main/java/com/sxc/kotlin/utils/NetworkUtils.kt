package com.sxc.kotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import com.sxc.kotlin.App

object NetworkUtils {

    /**
     * 判断网络是否可用
     *
     * @return `true` 网络可用
     * `false` 网络不可用
     */
    val isNetworkReachable: Boolean
        get() {
            try {
                val cm = App.get()
                        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val current = cm.activeNetworkInfo
                return current != null && current.isAvailable
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return false
        }
}