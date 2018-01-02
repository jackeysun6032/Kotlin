package com.sxc.kotlin.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by sword on 2017/9/12.
 */
object RetrofitApp {

    @Volatile private var retrofit: RetrofitApi? = null

    @Volatile private var bmob: BMobApi? = null

    fun get(): RetrofitApi {
        if (retrofit == null) {
            retrofit = synchronized<RetrofitApi?>(RetrofitApp, {
                Retrofit.Builder()
                        .baseUrl("https://www.eee586.com")
                        .client(OkHttpHelper.get())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(RetrofitApi::class.java)
            })
        }
        return retrofit!!
    }

    fun getBmob(): BMobApi {
        if (bmob == null) {
            bmob = synchronized<BMobApi?>(RetrofitApp, {
                Retrofit.Builder()
                        .baseUrl("https://api.bmob.cn/1/classes/")
                        .client(OkHttpHelper.get())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(BMobApi::class.java)
            })
        }
        return bmob!!
    }
}
