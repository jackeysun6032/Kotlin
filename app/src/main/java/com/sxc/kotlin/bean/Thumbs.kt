package com.sxc.kotlin.bean

import com.google.gson.annotations.SerializedName

data class Thumbs(

	@field:SerializedName("extension")
	val extension: String? = null,

	@field:SerializedName("common_url")
	val commonUrl: String? = null,

	@field:SerializedName("sizes")
	val sizes: Sizes? = null,

	@field:SerializedName("qty")
	val qty: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)