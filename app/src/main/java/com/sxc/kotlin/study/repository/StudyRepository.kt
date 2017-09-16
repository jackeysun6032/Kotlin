package com.sxc.kotlin.study.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.App
import com.sxc.kotlin.R
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.network.RetrofitApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup

/**
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<Array<String>>()

    private val htmlData = MutableLiveData<MutableList<VideoBean>>()

   // private val videoInfo = MutableLiveData<VideoInfo>()

    fun getStudyDatas(): LiveData<Array<String>> {
        studyDatas.value = App.get().resources.getStringArray(R.array.study_content)
        return studyDatas
    }

    fun getHtmlData(): LiveData<MutableList<VideoBean>> {
        RetrofitApp.get()
                .htmlInfo("/htm/sp.htm")
                .subscribeOn(Schedulers.io())
                .flatMap {

                    val result = Jsoup.parse(it)
                    val mainArea = result.select("div.mainArea.px17")
                    val category = mainArea.select("table").select("td")

                    return@flatMap Observable.fromIterable(category)
                            .map {
                                val root = it.select("a")
                                val url = root.attr("href")
                                val img = root.select("img").attr("src")

                                VideoBean(url, "", img, "", "")
                            }.toList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    htmlData.value = it
                }, {
                    it.printStackTrace()
                })
        return htmlData
    }

}