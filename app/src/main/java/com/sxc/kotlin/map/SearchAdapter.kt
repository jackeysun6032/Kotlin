package com.sxc.kotlin.map

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.amap.api.services.help.Tip
import com.sxc.kotlin.R
import com.sxc.kotlin.utils.recyclerViewUtil.BaseRecyclerViewAdapter

/**
 * Created by jackey on 2017/10/3.
 */
class SearchAdapter(context: Context) : BaseRecyclerViewAdapter<Tip, SearchAdapter.SearchViewHolder>(context) {

    override fun getViewId(): Int = R.layout.adapter_search_item

    override fun getItemCount(): Int = datas.size

    override fun createViewHolder(itemView: View): SearchViewHolder = SearchViewHolder(itemView)


    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        val data = datas[position]
        holder?.nameTv?.text = data.name
        holder?.addressTv?.text = data.district
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTv: TextView? = null
        var addressTv: TextView? = null

        init {
            nameTv = itemView.findViewById(R.id.name)
            addressTv = itemView.findViewById(R.id.address)
        }
    }
}