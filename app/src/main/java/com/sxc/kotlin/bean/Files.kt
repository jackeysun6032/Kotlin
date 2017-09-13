package com.sxc.kotlin.bean

import com.google.gson.annotations.SerializedName

data class Files(

	@field:SerializedName("lq")
	val lq: String? = null,

	@field:SerializedName("hq")
	val hq: String? = null
)