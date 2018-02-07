package com.sxc.kotlin.meizhi.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.amap.api.col.sln3.bo
import com.sxc.kotlin.App
import com.sxc.kotlin.bean.meizhi.NewsListBean
import com.sxc.kotlin.network.RetrofitApp
import com.sxc.kotlin.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/***
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<List<NewsListBean>>()

    var page = 1

    var box = App.get().getBoxStore().boxFor(NewsListBean::class.java)

    var isLoad = false

    val _disps = CompositeDisposable()

    fun registerMeiZhi(): MutableLiveData<List<NewsListBean>> {
        loadMeiZhi()
        return studyDatas
    }

    fun loadMeiZhi() {

        if (NetworkUtils.isNetworkReachable) {
            val disp = RetrofitApp.get().getGankMeiZhi(page)
                    .subscribeOn(Schedulers.io())
                    .map { it.transform() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        studyDatas.value = it
                        page++
                        box.put(it)
                    }, {
                        it.printStackTrace()
                    })
            _disps.add(disp)
        } else {
            if (isLoad) {
                return
            }
            val disp = Observable.just("")
                    .map { box.all }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        studyDatas.value = it
                        isLoad = true
                    }, {
                        isLoad = false
                        it.printStackTrace()
                    })
            _disps.add(disp)
        }

    }

    fun onDestroy() {
        _disps.clear()
    }

}