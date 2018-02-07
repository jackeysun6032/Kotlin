package com.sxc.kotlin.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Environment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.amap.api.col.sln3.v
import com.bumptech.glide.Glide
import com.sxc.kotlin.R
import com.sxc.kotlin.base.BaseFragment
import com.sxc.kotlin.home.banner.BannerHandler
import com.sxc.kotlin.home.repository.BannerRepository
import com.sxc.kotlin.network.BMobApi
import com.sxc.kotlin.network.RetrofitApp
import com.sxc.kotlin.splash.BannerAdapter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Retrofit
import java.io.*
import kotlin.math.log


/**
 * Created by sunxunchao on 2017/8/24.
 */
class HomeFragment : BaseFragment(), View.OnClickListener, ViewPager.OnPageChangeListener, View.OnTouchListener {

    private val TAG: String = HomeFragment::class.java.simpleName
    var bannerAdapter: BannerAdapter? = null
    var imgViews: ArrayList<View> = arrayListOf()

    val titles = arrayListOf("诡术妖姬", "美女", "戏命师", "疾风剑豪", "暗夜猎手")

    var currentPosition = Int.MAX_VALUE / 2

    var mHandler: BannerHandler? = null

    var length: Int = 0

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        val bannerRepository = ViewModelProviders.of(this).get(BannerRepository::class.java)

        bannerRepository.getImgs().observe(this@HomeFragment, Observer<ArrayList<Int>> {
            length = it!!.size

            it.map {
                val imgView = ImageView(context)
                imgView.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this@HomeFragment).load(it).into(imgView)
                imgViews.add(imgView)
            }
            bannerAdapter = BannerAdapter(context!!, imgViews)
            viewPager.adapter = bannerAdapter

            initPoints(0)
        })

        viewPager.setOnClickListener(this)
        viewPager.addOnPageChangeListener(this)

        viewPager.currentItem = currentPosition
        viewPager.setOnTouchListener(this)

        initMenu()

        RetrofitApp.getBmob().search(BMobApi.TABLES_USER, "ISsN999A")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ v ->
                    Log.i("HomeFragment", v)
                }, { e -> Log.e("HomeFragment", e.message) })


    }

    override fun initData() {

    }

    /**
     * 广告栏下的菜单
     */
    private fun initMenu() {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.replace(R.id.container_home_menu, MenuFragment())
        ft?.commit()
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            ACTION_DOWN -> mHandler?.sendEmptyMessage(BannerHandler.STOP)
            ACTION_UP -> mHandler?.sendEmptyMessageDelayed(BannerHandler.START, BannerHandler.DELAY)
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        mHandler = BannerHandler(viewPager)
        mHandler?.sendEmptyMessageDelayed(BannerHandler.START, BannerHandler.DELAY)
    }

    private fun initPoints(selectPoint: Int) {
        point_circle.removeAllViews()
        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until length) {
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
        title.text = titles[position % length]
        currentPosition = position % length

        var point: ImageView
        var pointRl: RelativeLayout
        for (i in 0 until length) {
            pointRl = point_circle.getChildAt(i) as RelativeLayout
            point = pointRl.getChildAt(0) as ImageView
            point.setBackgroundResource(R.drawable.gray_circle_bg)
        }
        pointRl = point_circle.getChildAt(position % length) as RelativeLayout
        point = pointRl.getChildAt(0) as ImageView
        point.setBackgroundResource(R.drawable.white_circle_bg)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }


    override fun onPause() {
        super.onPause()
        mHandler?.hasMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.START)
        mHandler = null
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        mHandler?.hasMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.START)
        mHandler = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        mHandler?.hasMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.STOP)
        mHandler?.removeMessages(BannerHandler.START)
        mHandler = null
    }

}