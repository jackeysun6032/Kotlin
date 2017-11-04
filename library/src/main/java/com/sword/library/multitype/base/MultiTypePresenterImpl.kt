package com.app.turingrobot.multitype.base

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sword.library.multitype.base.ViewModel
import java.lang.IllegalArgumentException

/**
 * ClassName: MultiTypePresenterImpl
 * Author: blades
 * Des: MultiTypePresenterImpl
 * CreateTime: 2017/1/3 11:15
 * UpdateTime: 2017/1/3 11:15
 * GitHub: https://github.com/AlphaKnife
 */

class MultiTypePresenterImpl private constructor(builder: MultiTypePresenterImpl.Builder) : MultiTypePresenter {

    val viewModels: List<ViewModel>?

    private val holders: SparseArray<ViewHolderPresenter<Any,Any>>

    init {
        viewModels = builder.viewModels
        holders = builder.holders
    }

    override fun createViewHolder(inflater: LayoutInflater, group: ViewGroup, type: Int)=holders.get(type).createViewHolder(group, inflater)

    override fun bindViewHolder(holder: BaseViewHolder, position: Int)= holders.get( viewModels!![position].viewTypeId()).onBind(holder,  viewModels[position])

    override fun unBind(holder: BaseViewHolder)= holder.unBind()

    override fun getViewType(position: Int) = if (viewModels == null) 0 else viewModels[position].viewTypeId()

    override fun getItemId(position: Int) =if(viewModels == null) 0 else viewModels[position].id()


    class Builder {

        var viewModels: List<ViewModel>? = null

        val holders: SparseArray<ViewHolderPresenter<Any,Any>> = SparseArray()

        fun withViewModels(value: List<ViewModel>): Builder {
            viewModels = value
            return this
        }

        fun addHolders(value: ViewHolderPresenter<Any,Any>): Builder {
            checkNotNull(holders) { "worker must not be null" }
            if (this.holders.get(value.type()) != null) {
                throw IllegalArgumentException("worker type has exits")
            }
            this.holders.put(value.type(), value)
            return this
        }

        fun build(): MultiTypePresenterImpl {
            return MultiTypePresenterImpl(this)
        }
    }

    companion object {

        fun newBuilder(): Builder {
            return Builder()
        }
    }
}
