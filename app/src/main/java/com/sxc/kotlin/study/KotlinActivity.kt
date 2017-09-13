package com.sxc.kotlin.study

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.amap.api.col.sln3.by

import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.bean.VideoInfo
import com.sxc.kotlin.study.repository.StudyRepository
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.toolbar.*
import com.bumptech.glide.Glide
import com.dou361.ijkplayer.listener.OnShowThumbnailListener
import com.dou361.ijkplayer.widget.PlayStateParams
import com.dou361.ijkplayer.widget.PlayerView



@Route(path = "/study/kotlin")
 class KotlinActivity : BaseActivity() {

     val vid by lazy { intent.getStringExtra(TITLE_TAG) as String }

    override fun initData() {

       val studyRepository=ViewModelProviders.of(this).get(StudyRepository::class.java)
            studyRepository.getVideoInfo(vid).observe(this@KotlinActivity,
                    Observer<VideoInfo> {

            val rootView = LayoutInflater.from(this@KotlinActivity).inflate(R.layout.simple_player_view_player, null)
            addContentView(rootView,ViewGroup.LayoutParams(-1,-1))
            val url = it?.files?.hq
                        val  player = PlayerView(this, rootView)
                                .setTitle(it?.title)
                                .setScaleType(PlayStateParams.fitparent)
                                .hideMenu(true)
                                .forbidTouch(false)
                                .showThumbnail { ivThumbnail ->
                                    Glide.with(this@KotlinActivity)
                                            .load(it?.thumbs?.url)
                                            .into(ivThumbnail)
                                }
                                .setPlaySource(url)
                                .startPlay()
            })

    }


    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int = R.layout.activity_kotlin

    override fun isTintStatusBar() = true

    companion object {
        val TITLE_TAG: String = "TITLE_TAG"
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
