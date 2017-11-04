package com.sxc.kotlin.bean.meizhi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class NewsListBean(
    /**
     * ctime : 2016-03-06 14:11
     * title : Beautyleg &#8211; Arvi 私房美腿写真
     * description : 美女图片
     * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2015/05/m.xxxiao.com_e7e731faf790487ccaf90d11774fae6b-760x500.jpg
     * url : http://m.xxxiao.com/1353
     */
    @Id
    @Expose
    var id: Long = 0,
    @SerializedName(value = "id")
    var dId:String?=null,
    var ctime: String? = null,
    var title: String? = null,
    var description: String? = null,
    var picUrl: String? = null,
    var url: String? = null
)