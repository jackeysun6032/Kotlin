package com.sxc.kotlin.home

import android.content.Context
import android.content.res.Resources
import android.support.annotation.IntegerRes
import android.support.v7.widget.RecyclerView
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.sxc.kotlin.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by jackey on 2017/8/26.
 */
class MenuAdapter(context: Context, datas: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener/*, View.OnClickListener*/ {

    val TAG = MenuAdapter::class.java.simpleName
    var context: Context? = null
    var datas: List<String>? = null

    val sizeList = mapOf<Int, Array<Int>>().toMutableMap()

    private lateinit var onItemClickListener: OnRecyclerViewItemClickListener

    interface OnRecyclerViewItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    companion object {
        val TYPE_ONE = 0
        val TYPE_TWO = 1
        val TYPE_THIRD = 2
    }

    init {
        this.context = context
        this.datas = datas

    }

    fun setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener) {
        this.onItemClickListener = onRecyclerViewItemClickListener
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        if (viewHolder is FirstViewHolder) viewHolder.mTextView?.text = datas?.get(position)
        if (viewHolder is SecondViewHolder) viewHolder.mTextView2?.text = datas?.get(position)
        if (viewHolder is ThirdViewHolder) viewHolder.mTextView3?.text = datas?.get(position)

        if (sizeList[position] == null) {
            sizeList.put(position, arrayOf(-1, Resources.getSystem().displayMetrics.widthPixels / 3 + Random().nextInt(300)))
        }
        val info = sizeList[position]
        val layoutParams = FrameLayout.LayoutParams(info!![0], info[1])
        viewHolder?.itemView?.layoutParams = layoutParams
        viewHolder?.itemView?.requestLayout()

        viewHolder?.itemView?.tag = position
    }

    override fun getItemCount(): Int = datas?.size ?: 0

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var viewHolder: RecyclerView.ViewHolder? = null
        var itemView: View? = null
        when (viewType) {
            TYPE_ONE -> {
                itemView = LayoutInflater.from(context).inflate(R.layout.first_item_layout, null)
                viewHolder = FirstViewHolder(itemView)
            }
            TYPE_TWO -> {
                itemView = LayoutInflater.from(context).inflate(R.layout.second_item_layout, null)
                viewHolder = SecondViewHolder(itemView)
            }
            TYPE_THIRD -> {
                itemView = LayoutInflater.from(context).inflate(R.layout.third_item_layout, null)
                viewHolder = ThirdViewHolder(itemView)
            }
        }

        itemView?.setOnClickListener(this)
        return viewHolder
    }

    override fun onClick(view: View?) {
        val position = view?.tag
        if (position is Int)
            onItemClickListener.onItemClick(view, position)
    }

    override fun getItemViewType(position: Int): Int {
//
//        val random: Random = Random()
//        var type = random.nextInt(3)
        return position % 3
    }

    class FirstViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView? = null

        init {
            mTextView = itemView.findViewById<TextView>(R.id.textView)
        }
    }

    class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView2: TextView? = null

        init {
            mTextView2 = itemView.findViewById<TextView>(R.id.textView2)
        }
    }

    class ThirdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView3: TextView? = null

        init {
            mTextView3 = itemView.findViewById<TextView>(R.id.textView3)
        }
    }

}