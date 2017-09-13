package com.sxc.kotlin.network

import com.sxc.kotlin.bean.VideoInfo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by sword on 2017/9/12.
 */
interface RetrofitApi {

    @GET("hd/3")
    fun htmlInfo():Single<String>

    @GET("player_config_json/")
    fun videoInfo(@Query("vid") vid:String):Observable<VideoInfo>
}