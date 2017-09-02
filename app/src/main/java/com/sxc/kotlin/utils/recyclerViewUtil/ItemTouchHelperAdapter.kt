package com.sxc.kotlin.utils.recyclerViewUtil

/**
 * Created by jackey on 2017/9/2.
 */
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}