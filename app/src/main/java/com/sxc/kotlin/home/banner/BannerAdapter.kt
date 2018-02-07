package com.sxc.kotlin.splash

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent


/**
 * Created by sunxunchao on 2017/8/23.
 */
class BannerAdapter(context: Context, views: ArrayList<View>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    var views = arrayListOf<View>()

    init {
        this.views = views
    }


    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = views[position % views.size]
        var parent: ViewParent? = view.parent
        if (parent != null) {
            (parent as ViewGroup).removeView(view)
        }
        container?.addView(view)
        return views[position % views.size]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}