package com.app.turingrobot.multitype.base

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sword.library.multitype.base.ViewModel

/**
 * ClassName: ViewHolderPresenter
 * Author: blades
 * Des: ViewHolderPresenter
 * CreateTime: 2017/1/3 11:43
 */
abstract class ViewHolderPresenter<in T,in V>{
    /**
     * 创建ViewHolder

     * @param parent
     * *
     * @param inflater
     * *
     * @return
     */
   abstract fun createViewHodler(parent: ViewGroup, inflater: LayoutInflater): BaseViewHolder

    abstract fun  onBind(holder: T, entity: V)

    /**
     * 模型类型

     * @return
     */
    abstract fun type(): Int

    companion object {

        fun cast(presenter: Any)= presenter as ViewHolderPresenter<Any,Any>

    }
}
