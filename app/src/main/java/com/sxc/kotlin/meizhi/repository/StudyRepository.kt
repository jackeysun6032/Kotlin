package com.sxc.kotlin.meizhi.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.amap.api.col.sln3.bo
import com.sxc.kotlin.App
import com.sxc.kotlin.bean.meizhi.NewsListBean
import com.sxc.kotlin.network.RetrofitApp
import com.sxc.kotlin.utils.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/***
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<List<NewsListBean>>()

    var page = 1

    var box = App.get().getBoxStore().boxFor(NewsListBean::class.java)

    fun registerMeiZhi(): MutableLiveData<List<NewsListBean>> {
        loadMeiZhi()
        return studyDatas
    }

    fun loadMeiZhi() {

        if(NetworkUtils.isNetworkReachable){
            RetrofitApp.get().
                    getGankMeiZhi(page)
                    .subscribeOn(Schedulers.io())
                    .map { it.transform() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        studyDatas.value = it
                        page++

                        box.put(it)
                    }, {
                        if (page > 1) {
                            page--
                        }
                    })
        }else{

        }

    }

}