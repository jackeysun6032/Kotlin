package com.app.turingrobot.multitype
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.turingrobot.multitype.base.BaseViewHolder
import com.app.turingrobot.multitype.base.MultiTypePresenterImpl


/**
 * ClassName: MultiTypeAdapter
 * Author: blades
 * Des: MultiTypeAdapter
 * CreateTime: 2017/1/3 10:30
 * UpdateTime: 2017/1/3 10:30
 * GitHub: https://github.com/AlphaKnife
 */

class MultiTypeAdapter(private val presenter: MultiTypePresenterImpl) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return presenter.createViewHolder(inflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        presenter.bindViewHolder(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return presenter.getItemId(position)
    }

    override fun onViewRecycled(holder: BaseViewHolder?) {
        super.onViewRecycled(holder)
        presenter.unBind(holder!!)
    }

    override fun getItemCount(): Int {
        return presenter.viewModels!!.size
    }

}
