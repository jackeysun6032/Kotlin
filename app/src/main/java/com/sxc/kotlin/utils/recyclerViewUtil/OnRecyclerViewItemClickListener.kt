package com.sxc.kotlin.utils.recyclerViewUtil

import android.view.View

/**
 * Created by jackey on 2017/9/2.
 */
interface OnRecyclerViewItemClickListener<T> {

    fun onItemClick(view: View?, position: Int, data: T)
}