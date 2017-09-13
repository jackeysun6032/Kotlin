package com.sxc.kotlin.study

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sxc.kotlin.R
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.utils.recyclerViewUtil.BaseRecyclerViewAdapter

/**
 * Created by sunxunchao on 2017/8/22.
 */
class StudyAdapter(context: Context) : BaseRecyclerViewAdapter<VideoBean, StudyAdapter.ViewHolder>(context) {

    override fun getViewId(): Int = R.layout.simple_list_item

    override fun createViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is ViewHolder) {
            holder.tv?.text = datas?.get(position).url
            Glide.with(context).load(datas?.get(position).img).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = datas.size

    class ViewHolder: RecyclerView.ViewHolder {

        constructor(itemView: View):super(itemView)
        var tv: TextView? = null

        var imageView:AppCompatImageView?=null

        init {
            tv = itemView.findViewById(R.id.text)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

}


