package com.sxc.kotlin.study

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sxc.kotlin.R
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener

/**
 * Created by sunxunchao on 2017/8/22.
 */
class StudyAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    var context: Context? = null
    var datas: List<String>? = null
    var holder: RecyclerView.ViewHolder? = null

    init {
        this.context = context
    }

    private lateinit var onItemClickListener: OnRecyclerViewItemClickListener

    fun setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener) {
        this.onItemClickListener = onRecyclerViewItemClickListener
    }

    fun setItems(datas: List<String>) {
        this.datas = datas
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false)
        itemView?.setOnClickListener(this)
        return ViewHolder(itemView)
    }

    override fun onClick(view: View?) {
        val position = view?.tag
        if (position is Int)
            onItemClickListener.onItemClick(view, position)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) holder?.tv?.text = datas?.get(position)
        holder?.itemView?.tag = position
        this.holder = holder
    }

    override fun getItemCount(): Int = datas?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView.findViewById<TextView>(R.id.text)
        }
    }

}


