package com.sxc.kotlin.meizhi

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route

import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*
import com.dou361.ijkplayer.widget.PlayStateParams
import com.dou361.ijkplayer.widget.PlayerView
import com.sxc.kotlin.view.MyPlayerView

@Route(path = "/study/kotlin")
class KotlinActivity : BaseActivity() {

    val vid by lazy { intent.getStringExtra(TITLE_TAG) as String }
    val url by lazy { intent.getStringExtra("url") as String }

    lateinit var player: PlayerView

    override fun initData() {


        val rootView = LayoutInflater.from(this@KotlinActivity).inflate(R.layout.simple_player_view_player, null)
        addContentView(rootView, ViewGroup.LayoutParams(-1, -1))

        player = MyPlayerView(this, rootView)
                .setTitle("")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .setPlayerRotation(90)
                .forbidTouch(false)
                .showThumbnail { ivThumbnail ->
                    //                    Glide.with(this@KotlinActivity)
//                            .load(it?.thumbs?.url)
//                            .into(ivThumbnail)
                }
                .setPlaySource(url)
                .startPlay()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)  //设置全屏
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutId(): Int = R.layout.activity_kotlin

    override fun isTintStatusBar() = false

    companion object {
        val TITLE_TAG: String = "TITLE_TAG"
    }

    override fun onResume() {
        super.onResume()
        player.onResume()
    }

    override fun onPause() {
        super.onPause()
        player.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        player.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        player.onConfigurationChanged(newConfig)
    }


}
