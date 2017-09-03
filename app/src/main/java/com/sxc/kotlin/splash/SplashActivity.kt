package com.sxc.kotlin.splash

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseActivity
import com.sxc.kotlin.login.LoginActivity
import com.sxc.kotlin.utils.SPUtil
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    var splashAdapter: SplashAdapter? = null
    val imgs = arrayListOf(R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img6)
    var views: ArrayList<View> = arrayListOf()
    private val FIRST_USE = "FIRST_USE"
    var isFirst: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)  //设置全屏
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {

        isFirst = SPUtil.get(FIRST_USE, true) as Boolean

        return if (isFirst as Boolean) {
            R.layout.activity_splash
        } else {
            R.layout.activity_welcome
        }
    }

    override fun initView() {

        if (isFirst as Boolean) {
            initSplashView()
        } else {
            initWelcomeView()
        }

    }

    private fun initSplashView() {
        for (i in imgs) {
            var imageView = ImageView(this)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(this).load(i).into(imageView)
            views.add(imageView)
        }

        splashAdapter = SplashAdapter(this, views)
        viewPager.adapter = splashAdapter
        viewPager.setOnPageChangeListener(this)

        jump_click.setOnClickListener {
            SPUtil.put(FIRST_USE, false)
            jumpToLogin()
        }

        initPoints(0)
    }

    private fun initWelcomeView() {
        Thread(Runnable {
            Thread.sleep(2000)
            jumpToLogin()
        }).start()
    }

    private fun jumpToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
//        overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
    }

    override fun initData() {
    }

    private fun initPoints(selectPoint: Int) {
        point_ll.removeAllViews()
        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until imgs.size) {
            point = ImageView(this)
            pointRl = RelativeLayout(this)
            pointRl.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            pointRl.setPadding(5, 0, 5, 0)
            pointRl.addView(point)
            point_ll.addView(pointRl)
            if (i == selectPoint) {
                point.setBackgroundResource(R.drawable.white_circle_bg)
            } else {
                point.setBackgroundResource(R.drawable.gray_circle_bg)
            }
        }
    }


    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until imgs.size) {
            pointRl = point_ll.getChildAt(i) as RelativeLayout
            point = pointRl.getChildAt(0) as ImageView
            point.setBackgroundResource(R.drawable.gray_circle_bg)
        }
        pointRl = point_ll.getChildAt(position) as RelativeLayout
        point = pointRl.getChildAt(0) as ImageView
        point.setBackgroundResource(R.drawable.white_circle_bg)
    }
}
