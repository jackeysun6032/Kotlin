package com.sxc.kotlin.network

import io.reactivex.Observable
import retrofit2.http.*


interface BMobApi {

    companion object {
        val TABLES_USER = "_User"
    }


    @GET("{tables}/{id}")
    fun search(@Path("tables") tables: String,
               @Path("id") id: String): Observable<String>

    @FormUrlEncoded
    @POST("{tables}")
    fun add(@Path("tables") tables: String,
            @Field("values") values: String): Observable<String>

    @FormUrlEncoded
    @PUT("{tables}/{id}")
    fun update(@Path("tables") tables: String,
               @Path("id") id: String,
               @Field("values") values: String): Observable<String>

    @FormUrlEncoded
    @DELETE("{tables}/{id}")
    fun delete(@Path("tables") tables: String,
               @Path("id") id: String): Observable<String>
}