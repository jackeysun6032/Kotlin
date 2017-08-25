package com.sxc.kotlin.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.sxc.kotlin.R
import com.sxc.kotlin.home.banner.BannerHandler
import com.sxc.kotlin.splash.BannerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by sunxunchao on 2017/8/24.
 */
class HomeFragment : Fragment(), View.OnClickListener, ViewPager.OnPageChangeListener, View.OnTouchListener {

    val TAG: String = HomeFragment::class.java.simpleName
    var bannerAdapter: BannerAdapter? = null
    var imgViews: ArrayList<View> = arrayListOf()
    val imgs: ArrayList<Int> = arrayListOf(R.mipmap.first, R.mipmap.second, R.mipmap.third)
    val titles = arrayListOf("第一个", "第二个", "第三个")

    var currentPosition = Int.MAX_VALUE / 2

    var mHandler: BannerHandler? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for (img in imgs) {
            var imgView = ImageView(context)
            imgView.setBackgroundResource(img)
            imgViews.add(imgView)
        }

        bannerAdapter = BannerAdapter(context, imgViews)
        viewPager.adapter = bannerAdapter
        viewPager.setOnClickListener(this)
        viewPager.setOnPageChangeListener(this)

        initPoints(currentPosition % imgs.size)
        initView()
    }

    fun initView() {
        viewPager.currentItem = currentPosition
        mHandler = BannerHandler(viewPager)

        viewPager.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            ACTION_DOWN -> mHandler?.sendEmptyMessage(BannerHandler.STOP)
            ACTION_UP -> mHandler?.sendEmptyMessageDelayed(BannerHandler.START, BannerHandler.DELAY)
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        mHandler?.sendEmptyMessageDelayed(BannerHandler.START, BannerHandler.DELAY)
    }

    private fun initPoints(selectPoint: Int) {
        point_circle.removeAllViews()
        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until imgs.size) {
            point = ImageView(context)
            pointRl = RelativeLayout(context)
            pointRl.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            pointRl.setPadding(5, 0, 5, 0)
            pointRl.addView(point)
            point_circle.addView(pointRl)
            if (i == selectPoint) {
                point.setBackgroundResource(R.drawable.white_circle_bg)
            } else {
                point.setBackgroundResource(R.drawable.gray_circle_bg)
            }
        }
    }


    override fun onClick(v: View?) {
        Log.d(TAG, "click")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        title.text = titles[position % imgs.size]
        currentPosition = position % imgs.size

        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until imgs.size) {
            pointRl = point_circle.getChildAt(i) as RelativeLayout
            point = pointRl.getChildAt(0) as ImageView
            point.setBackgroundResource(R.drawable.gray_circle_bg)
        }
        pointRl = point_circle.getChildAt(position % imgs.size) as RelativeLayout
        point = pointRl.getChildAt(0) as ImageView
        point.setBackgroundResource(R.drawable.white_circle_bg)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }


    override fun onPause() {
        super.onPause()
        mHandler?.hasMessages(BannerHandler.STOP)
    }

    override fun onStop() {
        super.onStop()
        mHandler?.hasMessages(BannerHandler.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler?.hasMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.STOP)
        mHandler = null
    }

}