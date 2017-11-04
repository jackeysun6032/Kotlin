package com.sxc.kotlin.bean.meizhi


import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.TimeZone


/***
* Created by Alpha on 2016/12/6.
*/
data class GankMeiZhi(var error: Boolean = false,
                      val results: List<ResultsBean>? = null)
    : EntityTrans<ArrayList<NewsListBean>>{



    @SuppressLint("SimpleDateFormat")
    override fun transform(): ArrayList<NewsListBean> {
        val meiZhis = ArrayList<NewsListBean>()
        val size = results!!.size
        for (i in 0 until size) {
            val resultsBean = results[i]
            val newslistBean = NewsListBean()
            newslistBean.picUrl = resultsBean.url
            newslistBean.url = resultsBean.source

            try {

                val df = SimpleDateFormat("yyyy-MM-dd")
                df.timeZone = TimeZone.getTimeZone("UTC")
                val time = df.format(df.parse(resultsBean.createdAt))
                newslistBean.ctime = time
            } catch (e: Exception) {
                e.printStackTrace()
            }

            meiZhis.add(newslistBean)
        }
        return meiZhis
    }


}
