package com.app.turingrobot.multitype.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * ClassName: BaseViewHolder
 * Author: blades
 * Des: BaseViewHolder
 * CreateTime: 2017/1/3 10:31
 * UpdateTime: 2017/1/3 10:31
 * GitHub: https://github.com/AlphaKnife
 */

abstract class BaseViewHolder(@LayoutRes layoutId: Int, inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(layoutId, parent, false)) {

    fun unBind() {

    }
}
