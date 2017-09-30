package com.sword.library.multitype.base

import android.support.v7.widget.RecyclerView


/**
 * ClassName: ViewModel
 * Author: blades
 * Des: ViewModel
 * CreateTime: 2017/1/3 11:25
 */
abstract class ViewModel {

    /**
     * item view type ID

     * @return
     */
    abstract fun viewTypeId(): Int

    /**
     * item position ID

     * @return
     */
    fun id(): Long {
        return RecyclerView.NO_ID
    }
}
