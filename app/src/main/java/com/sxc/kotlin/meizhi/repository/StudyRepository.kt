package com.sxc.kotlin.meizhi.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.bean.meizhi.NewslistBean
import com.sxc.kotlin.network.RetrofitApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<List<NewslistBean>>()

    var page = 1

    fun registerMeiZhi(): MutableLiveData<List<NewslistBean>> {
        loadMeiZhi()
        return studyDatas
    }

    fun loadMeiZhi() {

        RetrofitApp.get().
                getGankMeiZhi(page)
                .subscribeOn(Schedulers.io())
                .map { it.transform() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    studyDatas.value = it
                    page++
                }, {
                    if (page > 1) {
                        page--
                    }
                })

    }

}