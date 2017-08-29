package com.sxc.kotlin.splash

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.sxc.kotlin.MainActivity
import com.sxc.kotlin.R
import kotlinx.android.synthetic.main.activity_splash.*
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sxc.kotlin.login.LoginActivity


class SplashActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    var splashAdapter: SplashAdapter? = null
    val imgs = arrayListOf(R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img6)
    var views: ArrayList<View> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)  //设置全屏
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        for (i in imgs) {
            var imageView = ImageView(this)
            imageView.setBackgroundResource(i)
            views.add(imageView)
        }

        splashAdapter = SplashAdapter(this, views)
        viewPager.adapter = splashAdapter
        viewPager.setOnPageChangeListener(this)

        jump_click.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        initPoints(0)
    }

    fun initPoints(selectPoint: Int) {
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
