package com.sxc.kotlin.network

import okhttp3.OkHttpClient

object OkHttpHelper {

    fun get(): OkHttpClient {
        val httpClient =  OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("X-Bmob-Application-Id", "faf95a1df63626b8ba61563b1b288cdf")
                    .header("X-Bmob-REST-API-Key", "99e98a0df2e67e955d41465e8019e715")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}