package com.sxc.kotlin.video.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.network.RetrofitApp
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
 * Created by jackey on 2017/9/1.
 */
class VideoRepository : ViewModel() {


    private val htmlData = MutableLiveData<MutableList<VideoBean>>()

    private val videoInfo = MutableLiveData<VideoBean>()

    fun getCategory(): LiveData<MutableList<VideoBean>> {
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

    fun getVideoList(category: String, page: Int): LiveData<MutableList<VideoBean>> {
        loadVideoData(category, page)
        return htmlData
    }

    fun loadVideoData(category: String, page: Int) {
        RetrofitApp.get()
                .videoInfo(category, page)
                .subscribeOn(Schedulers.io())
                .flatMap {

                    val result = Jsoup.parse(it)
                    val mainArea = result.select("div.mainArea.px17")
                    val category = mainArea.select("ul.movieList").select("li")

                    return@flatMap Observable.fromIterable(category)
                            .map {
                                val root = it.select("a")
                                val url = root.attr("href")
                                val name = root.select("h3").text()
                                val subTitle = root.select("p").text()
                                val date = root.select("p").select("span").text()
                                val img = root.select("img").attr("src")

                                VideoBean(url, name, img, subTitle, date)
                            }.toList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    htmlData.value = it
                }, {
                    it.printStackTrace()
                })
    }

    fun onVideoGet(): LiveData<VideoBean> = videoInfo


    fun getVideoData(url: String) {
        RetrofitApp.get()
                .htmlInfo(url)
                .subscribeOn(Schedulers.io())
                .map {
                    val result = Jsoup.parse(it)
                    val mainArea = result.select("div.mainArea")
                    val list = mainArea.select("div.endpage.clearfixpage")
                            .select("div.mox").select("div.play-list").select("a")
                    val element: Element = list.filter { it.text().equals("手机版播放") }[0]
                    return@map VideoBean(element.attr("href"))
                }.flatMap {
                    RetrofitApp.get()
                            .htmlInfo(it.url)
                            .map {
                                System.out.println(it)
                                val result = Jsoup.parse(it)
                                val aList = result.select("script")[3].
                                        childNode(0).outerHtml().split(";")
                                val resData= aList.filter { it.contains("playurl") }[0]
                               val videoUrl = resData?.split("+")[1]?.replace("'","")
                                return@map VideoBean("http://925hh.com"+videoUrl)
                            }
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    videoInfo.value = it
                }, {
                    it.printStackTrace()
                })
    }

}