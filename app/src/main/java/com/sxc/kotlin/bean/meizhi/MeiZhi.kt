package com.sxc.kotlin.bean.meizhi

/***
 *
 */
data class MeiZhi (

    /**
     * code : 200
     * msg : success
     */

    var code: Int = 0,
    var msg: String? = null,
    var newslist: List<NewsListBean>? = null

)
