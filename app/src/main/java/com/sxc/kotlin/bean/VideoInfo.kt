package com.sxc.kotlin.bean

import com.google.gson.annotations.SerializedName

data class VideoInfo(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("video_key")
	val videoKey: String? = null,

	@field:SerializedName("files")
	val files: Files? = null,

	@field:SerializedName("has_hq")
	val hasHq: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster")
	val poster: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbs")
	val thumbs: Thumbs? = null
)