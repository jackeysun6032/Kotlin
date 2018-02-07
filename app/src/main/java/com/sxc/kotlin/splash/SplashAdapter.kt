package com.sxc.kotlin.splash

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by sunxunchao on 2017/8/23.
 */
class SplashAdapter(context: Context, views: ArrayList<View>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    var views = arrayListOf<View>()

    init {
        this.views = views
    }


    override fun getCount(): Int {
        return views.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
        container.removeView(views[position])
    }
}