package com.sxc.kotlin.study

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sxc.kotlin.R

/**
 * Created by sunxunchao on 2017/8/22.
 */
class StudyAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null
    var datas: List<String>? = null

    init {
        this.context = context
    }

    fun setItems(datas: List<String>) {
        this.datas = datas
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is ViewHolder) holder?.tv?.text = datas?.get(position)
    }

    override fun getItemCount(): Int = datas?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = null

        init {
            tv = itemView.findViewById<TextView>(R.id.text)
        }
    }

}


