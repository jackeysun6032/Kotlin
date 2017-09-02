package com.sxc.kotlin.study

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.sxc.kotlin.R
import com.sxc.kotlin.utils.recyclerViewUtil.BaseRecyclerViewAdapter
import com.sxc.kotlin.utils.recyclerViewUtil.OnRecyclerViewItemClickListener
import java.util.*

/**
 * Created by sunxunchao on 2017/8/22.
 */
class StudyAdapter(context: Context) : BaseRecyclerViewAdapter<String, StudyAdapter.ViewHolder>(context), View.OnClickListener {


    var datas: ArrayList<String>? = null

    private lateinit var onItemClickListener: OnRecyclerViewItemClickListener

    fun setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener) {
        this.onItemClickListener = onRecyclerViewItemClickListener
    }

    fun setItems(datas: ArrayList<String>) {
        this.datas = datas
        this.item = datas
        this.notifyDataSetChanged()
    }

    override fun getViewId(): Int = R.layout.simple_list_item

    override fun createViewHolder(itemView: View?): ViewHolder = ViewHolder(itemView!!)

    override fun onClick(view: View?) {
        val position = view?.tag
        if (position is Int)
            onItemClickListener.onItemClick(view, position)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is ViewHolder) holder.tv?.text = datas?.get(position)
    }

    override fun getItemCount(): Int = datas?.size ?: 0

    class ViewHolder: RecyclerView.ViewHolder {

        constructor(itemView: View):super(itemView)
        var tv: TextView? = null

        init {
            tv = itemView.findViewById<TextView>(R.id.text)
        }
    }

}


