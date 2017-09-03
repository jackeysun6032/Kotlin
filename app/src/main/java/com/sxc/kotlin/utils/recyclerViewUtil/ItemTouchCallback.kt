package com.sxc.kotlin.utils.recyclerViewUtil

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import com.sxc.kotlin.study.StudyAdapter

/**
 * Created by jackey on 2017/9/2.
 */
class ItemTouchCallback<T,VH : RecyclerView.ViewHolder>(adapter: BaseRecyclerViewAdapter<T,VH>?) : ItemTouchHelper.Callback() {

    private val TAG = ItemTouchCallback::class.java.simpleName
    private var mAdapter: RecyclerView.Adapter<VH>? = adapter

    var canSwipe = true
    var canDrag = true

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        Log.d(TAG, "getMovementFlags")
        var dragFlags: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        var swipeFlags: Int = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        Log.d(TAG, "onMove")
        if (viewHolder?.itemViewType != target?.itemViewType) {
            return false
        }
        if (mAdapter is StudyAdapter)
            (mAdapter as StudyAdapter).onItemMove(viewHolder?.adapterPosition!!, target?.adapterPosition!!)
        return true //执行拖拽
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        Log.d(TAG, "onSwiped")
        if (mAdapter is StudyAdapter)
            (mAdapter as StudyAdapter).onItemDismiss(viewHolder?.adapterPosition!!)
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