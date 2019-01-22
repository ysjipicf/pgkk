package com.pgkk.ui.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tanxueze on 2018/1/10.
 * 分页计算
 */

public abstract class PagingOnScrollListener extends RecyclerView.OnScrollListener {

    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;

    //加载出来的Item的数量
    private int totalItemCount;

    //一个totalItemCount
    private int previousTotal = 0;

    //可见的item数量
    private int visibleItemCount;

    //可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;

    private int type;

    public PagingOnScrollListener(LinearLayoutManager linearLayoutManager, int type) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.type = type;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (type == 1) {
            if (!loading && totalItemCount - visibleItemCount == firstVisibleItem) {
                onLoadMore();
                loading = true;
            }
        } else if (type == 0) {
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
                onLoadMore();
                loading = true;
            }
        }
    }

    /**
     * 实现方法，加载
     */
    public abstract void onLoadMore();
}
