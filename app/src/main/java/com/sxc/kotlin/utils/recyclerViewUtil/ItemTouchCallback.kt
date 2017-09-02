package com.sxc.kotlin.utils.recyclerViewUtil

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import java.util.*

/**
 * Created by jackey on 2017/9/2.
 * todo() 有一个隐藏bug
 */
class ItemTouchCallback<T>(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, datas: ArrayList<T>) : ItemTouchHelper.Callback() {

    private val TAG = ItemTouchCallback::class.java.simpleName
    private var datas: ArrayList<T>? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = adapter

    var canSwipe = true
    var canDrag = true

    init {
        this.datas = datas
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        Log.d(TAG, "getMovementFlags")
        var dragFlags: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        var swipeFlags: Int = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        Log.d(TAG, "onMove")
        val fromPosition = viewHolder?.adapterPosition //拖拽的item的位置
        val toPosition = target?.adapterPosition//拖拽到的item位置
        if (fromPosition!! < toPosition!!) {
            for (i in fromPosition until toPosition) {
                Collections.swap(datas, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(datas, i, i - 1)
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition)
        return true //执行拖拽
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        Log.d(TAG, "onSwiped")
        val position = viewHolder?.adapterPosition
        datas?.removeAt(position!!)
        mAdapter.notifyItemRemoved(position!!)
    }


    override fun isLongPressDragEnabled(): Boolean {
        Log.d(TAG, "isLongPressDragEnabled")
        return canDrag
    }


    override fun isItemViewSwipeEnabled(): Boolean {
        Log.d(TAG, "isItemViewSwipeEnabled")
        return canSwipe
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        super.clearView(recyclerView, viewHolder)
        viewHolder?.itemView?.setBackgroundColor(0)
    }
}