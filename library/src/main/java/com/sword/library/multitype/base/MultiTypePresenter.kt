package com.app.turingrobot.multitype.base
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * ClassName: MultiTypePresenter
 * Author: blades
 * Des: MultiTypePresenter
 * CreateTime: 2017/1/3 11:06
 * UpdateTime: 2017/1/3 11:06
 * GitHub: https://github.com/AlphaKnife
 */

interface MultiTypePresenter {

    fun createViewHolder(inflater: LayoutInflater, group: ViewGroup, type: Int): BaseViewHolder

    fun bindViewHolder(holder: BaseViewHolder, position: Int)

    fun unBind(holder: BaseViewHolder)

    fun getViewType(position: Int): Int

    fun getItemId(position: Int): Long


}
