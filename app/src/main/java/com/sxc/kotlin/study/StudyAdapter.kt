package com.sxc.kotlin.study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.sxc.kotlin.R

/**
 * Created by sunxunchao on 2017/8/22.
 */
class StudyAdapter(context: Context) : BaseAdapter() {

    var context: Context? = null
    var datas: ArrayList<String> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        var view = convertView
        if (convertView == null) {
            viewHolder = ViewHolder()
            view = View.inflate(context, R.layout.simple_list_item, null)
            viewHolder.tv = view?.findViewById(R.id.text)
            view.tag = viewHolder
        } else {
            viewHolder = view?.tag as ViewHolder
        }
        viewHolder.tv?.text = datas[position]
        return view!!
    }

    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas.size
    }

    class ViewHolder {
        var tv: TextView? = null
    }
}