package com.pgkk.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgkk.R;
import com.pgkk.common.utils.AppLog;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.NewsPresenter;
import com.pgkk.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class NewsBatFragment extends BaseFragment implements NetView<List<NewsAdapter.MultipleItem>> {

    private static final String TYPE = "TYPE";

    @Inject
    NewsPresenter newsPresenter;

    @Inject
    NewsAdapter newsAdapter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout line_swipe_refresh;

    @BindView(R.id.line_recycler)
    RecyclerView line_recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    LinearLayoutManager linearLayoutManager;
    int lastVisibleItem;

    @Inject
    List<NewsAdapter.MultipleItem> lists;

    public NewsBatFragment() {

    }

    public static NewsBatFragment newInstance(String type) {
        NewsBatFragment fragment = new NewsBatFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, contentView);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
        newsPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppLog.e(getArguments().getString(TYPE));
        newsPresenter.loadNews(getArguments().getString(TYPE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsPresenter.detachView();
    }


    void initView() {
        line_swipe_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        line_swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsAdapter.MultipleItem dataBean = (NewsAdapter.MultipleItem) adapter.getItem(position);
                NewsDetailActivity.launch(getActivity(),dataBean.getData());
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        line_recycler.setLayoutManager(linearLayoutManager);
        line_recycler.setHasFixedSize(true);

        line_recycler.setAdapter(newsAdapter);

        line_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        line_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsPresenter.loadNews(getArguments().getString(TYPE));
            }
        });

        line_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 >= linearLayoutManager.getItemCount()) {
                    //下一页数据
                    // newsPresenter.loadNews(getArguments().getString(TYPE));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见视图在适配器的位置。
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void showLoading() {
        line_swipe_refresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        line_swipe_refresh.setRefreshing(false);
    }

    @Override
    public void showContent(List<NewsAdapter.MultipleItem> data) {
        if (data != null && data.size() > 0)
            lists.addAll(data);
            newsAdapter.setNewData(new ArrayList(new HashSet(lists)));
    }

    @Override
    public void showError(Throwable throwable) {
    }

    @Override
    public void showEmpty(String empty) {
        SnackbarUtil.ShortSnackbar(constraintLayout,empty,SnackbarUtil.blue).show();
    }
}
