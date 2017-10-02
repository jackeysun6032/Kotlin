package com.sxc.kotlin.utils.recyclerViewUtil

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jackey on 2017/9/2.
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(context: Context) : RecyclerView.Adapter<VH>(), ItemTouchHelperAdapter, View.OnClickListener {

    var context: Context? = null
    var datas: ArrayList<T> = arrayListOf()

    init {
        this.context = context
    }

    private lateinit var onItemClickListener: OnRecyclerViewItemClickListener<ArrayList<T>>

    fun setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<ArrayList<T>>) {
        this.onItemClickListener = onRecyclerViewItemClickListener
    }

    override fun onClick(view: View?) {
        val position = view?.tag
        if (position is Int)
            onItemClickListener.onItemClick(view, position, datas)
    }

    fun setItems(datas: ArrayList<T>) {
        this.datas = datas
        this.notifyDataSetChanged()
    }

    fun addItems(datas: ArrayList<T>) {

        datas?.let {
            this.datas.addAll(datas)
            this.notifyDataSetChanged()
        }

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(datas, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyItemRangeChanged(0, datas.size) //数据变化导致position错误的问题
        return true
    }

    override fun onItemDismiss(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, datas.size)//数据变化导致position错误的问题
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val itemView = LayoutInflater.from(context).inflate(getViewId(), parent, false)
        itemView.setOnClickListener(this)
        return createViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.itemView?.tag = position
    }

    abstract fun getViewId(): Int

    abstract fun createViewHolder(itemView: View): VH

}