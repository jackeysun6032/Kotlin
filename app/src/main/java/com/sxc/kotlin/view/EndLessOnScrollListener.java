package com.sxc.kotlin.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/** Created by wnw on 16-5-26. */
public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    boolean isSlidingToLast = false;


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            //大于0表示，正在向下滚动
            isSlidingToLast = true;
        } else {
            //小于等于0 表示停止或向上滚动
            isSlidingToLast = false;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                int lastVisiblePos = getMaxElem(lastVisiblePositions);
                int totalItemCount = manager.getItemCount();

                // 判断是否滚动到底部
                if (lastVisiblePos == (totalItemCount - 1) && isSlidingToLast) {
                    onLoadMore();
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {

            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisiblePos = manager.findLastVisibleItemPosition();
                int totalItemCount = manager.getItemCount();

                // 判断是否滚动到底部
                if (lastVisiblePos == (totalItemCount - 1) && isSlidingToLast) {
                    onLoadMore();
                }
            }
        }

    }


    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i] > maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }


    public abstract void onLoadMore();
}