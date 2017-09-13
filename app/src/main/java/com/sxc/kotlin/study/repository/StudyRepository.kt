package com.sxc.kotlin.study.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.App
import com.sxc.kotlin.R
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.bean.VideoInfo
import com.sxc.kotlin.network.RetrofitApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.util.*

/**
 * Created by jackey on 2017/9/1.
 */
class StudyRepository : ViewModel() {

    private val studyDatas = MutableLiveData<Array<String>>()

    private val htmlData=MutableLiveData<MutableList<VideoBean>>()

    private val videoInfo=MutableLiveData<VideoInfo>()

    fun getStudyDatas(): LiveData<Array<String>> {
        studyDatas.value = App.get().resources.getStringArray(R.array.study_content)
        return studyDatas
    }


    fun getHtmlData():LiveData<MutableList<VideoBean>>{
        RetrofitApp.get()
                .htmlInfo()
                .subscribeOn(Schedulers.io())
                .flatMap {
                    return@flatMap Observable.fromIterable(Jsoup.parse(it)
                            .select("div.category-list-wrap").select("li"))
                            .map {
                                val root=it.select("a")
                                val url=root.attr("href")
                                val percent= root.select("div.percent").text()
                                val duration=root.select("div.duration").text()
                                val img=root.select("img.tmb_img").attr("src")
                                var vid=""
                                root.select("img.tmb_img").attr("id")?.let {
                                    if(it.split("_").size>=2){
                                        vid=it.split("_")[1]
                                    }
                                }
                                VideoBean(url,vid,img,percent,duration)
                            }.toList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    htmlData.value= it
                },{
                    it.printStackTrace()
                })
        return htmlData
    }

    fun getVideoInfo(vid:String):LiveData<VideoInfo>{
        RetrofitApp.get()
                .videoInfo(vid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    videoInfo.value=it
                },{
                    it.printStackTrace()
                })

        return videoInfo
    }
}