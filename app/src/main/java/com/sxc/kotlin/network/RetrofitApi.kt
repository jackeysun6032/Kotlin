package com.sxc.kotlin.network

import com.sxc.kotlin.bean.meizhi.GankMeiZhi
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
* Created by sword on 2017/9/12.
* @author sword
* Created Time 上午10:40
*/
interface RetrofitApi {

    @GET("{htmlUrl}")
    fun htmlInfo(@Path("htmlUrl") url: String): Single<String>

    @GET("/{category}/{page}.htm")
    fun videoInfo(@Path("category") category: String, @Path("page") page: Int): Single<String>

    @GET("http://gank.io/api/data/福利/10/{page}")
    fun getGankMeiZhi(@Path("page") page: Int): Observable<GankMeiZhi>
}