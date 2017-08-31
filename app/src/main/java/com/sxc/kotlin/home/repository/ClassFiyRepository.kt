package com.sxc.kotlin.home.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.res.Resources
import com.sxc.kotlin.App
import com.sxc.kotlin.R
import java.security.AccessControlContext

/**
 * Created by sword on 2017/8/31.
 */
class ClassFiyRepository : ViewModel() {


    private val classFiyData = MutableLiveData<Array<String>>()

    fun getClassFiyData(): LiveData<Array<String>> {
        classFiyData.value = App.get().resources.getStringArray(R.array.home_menu)
        return classFiyData
    }
}