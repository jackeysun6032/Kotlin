package com.sxc.kotlin.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by sword on 2017/9/12.
 */
object RetrofitApp {

    @Volatile private var retrofit: RetrofitApi? = null

    fun get(): RetrofitApi {
        if (retrofit == null) {
            retrofit = synchronized<RetrofitApi?>(RetrofitApp, {
                Retrofit.Builder()
                        .baseUrl("https://www.eee881.com")
                        .client(OkHttpClient())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(RetrofitApi::class.java)
            })
        }
        return retrofit!!
    }
}