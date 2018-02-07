package com.sxc.kotlin.video

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sxc.kotlin.R
import com.sxc.kotlin.bean.VideoBean
import com.sxc.kotlin.utils.recyclerViewUtil.BaseRecyclerViewAdapter

/**
 * Created by sunxunchao on 2017/8/22.
 */
class CategoryAdapter(context: Context) : BaseRecyclerViewAdapter<VideoBean, CategoryAdapter.ViewHolder>(context) {

    override fun getViewId(): Int = R.layout.simple_list_item

    override fun createViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        if (holder is ViewHolder) {
            val date = datas[position].duration
            holder.date?.text = date
            Glide.with(context!!).load(datas[position].img).into(holder.imageView as ImageView)
        }
    }

    override fun getItemCount(): Int = datas.size

    class ViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View) : super(itemView)

        var date: TextView? = null

        var imageView: AppCompatImageView? = null

        init {
            date = itemView.findViewById(R.id.date)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }

}