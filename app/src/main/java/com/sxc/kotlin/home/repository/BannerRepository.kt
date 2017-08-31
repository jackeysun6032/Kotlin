package com.sxc.kotlin.home.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.R
import android.arch.lifecycle.MutableLiveData


/**
 * Created by sword on 2017/8/31.
 */
class BannerRepository : ViewModel() {


    private val imgsData = MutableLiveData<ArrayList<Int>>()

    val imgs: ArrayList<Int> = arrayListOf(R.mipmap.img1, R.mipmap.img5, R.mipmap.img_xms, R.mipmap.img_ys, R.mipmap.img_vn)

    fun getImgs(): LiveData<ArrayList<Int>> {
        imgsData.value = imgs
        return imgsData
    }

}