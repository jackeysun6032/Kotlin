package com.sxc.kotlin.utils.recyclerViewUtil

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by jackey on 2017/9/2.
 */
abstract class BaseRecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>(context: Context): RecyclerView.Adapter<VH>(),ItemTouchHelperAdapter, View.OnClickListener {

    var context: Context? = null
    var item: ArrayList<T> =  arrayListOf()
    protected var itemView: View? = null

    init {
        this.context = context
    }
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(item, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        item.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        itemView = LayoutInflater.from(context).inflate(getViewId(),parent,false)
        itemView?.setOnClickListener(this)
        return createViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.itemView?.tag = position
    }

    abstract fun getViewId(): Int

    abstract fun createViewHolder(itemView:View?):VH

}