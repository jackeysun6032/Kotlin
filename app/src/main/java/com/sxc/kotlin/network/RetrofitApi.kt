package com.sxc.kotlin.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by sword on 2017/9/12.
 */
interface RetrofitApi {

    @GET("{htmlUrl}")
    fun htmlInfo(@Path("htmlUrl") url: String): Single<String>

    @GET("/{category}/{page}.htm")
    fun videoInfo(@Path("category") category: String
                  , @Path("page") page: Int): Single<String>
}