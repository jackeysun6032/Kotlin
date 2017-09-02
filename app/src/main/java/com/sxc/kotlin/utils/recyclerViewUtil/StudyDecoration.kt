package com.sxc.kotlin.utils.recyclerViewUtil

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View


/**
 * Created by jackey on 2017/9/1.
 */
class StudyDecoration(context: Context, orientation: Int = LinearLayoutManager.VERTICAL) : ItemDecoration() {

    companion object {
        val HORIZONTAL = LinearLayoutManager.HORIZONTAL
        val VERTICAL = LinearLayoutManager.VERTICAL
        val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    private var context: Context? = null
    private var orientation: Int
    private var mDivider: Drawable? = null

    init {
        this.context = context
        this.orientation = orientation
        val ta: TypedArray = context.obtainStyledAttributes(ATTRS)
        this.mDivider = ta.getDrawable(0)
        ta.recycle()
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        when (orientation) {
            VERTICAL -> drawVerticalLine(c, parent, state)
            HORIZONTAL -> drawHorizontalLine(c, parent, state)
        }
    }

    private fun drawHorizontalLine(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        val left = parent?.paddingLeft
        val right = parent?.width!! - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            //获得child的布局信息
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider?.intrinsicHeight!!
            mDivider?.setBounds(left!!, top, right, bottom)
            mDivider?.draw(c)
        }
    }


    private fun drawVerticalLine(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        val top: Int? = parent?.paddingTop
        val bottom = parent?.height!! - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            //获得child的布局信息
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider?.intrinsicWidth!!
            mDivider?.setBounds(left, top ?: 0, right, bottom)
            mDivider?.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        when (orientation) {
            VERTICAL -> outRect?.set(0, 0, mDivider?.intrinsicWidth ?: 0, 0)
            HORIZONTAL -> outRect?.set(0, 0, 0, mDivider?.intrinsicHeight ?: 0)
        }
    }
}