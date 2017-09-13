package com.sxc.kotlin.bean

import com.google.gson.annotations.SerializedName

data class Sizes(

	@field:SerializedName("lists")
	val lists: String? = null,

	@field:SerializedName("progress")
	val progress: String? = null
)