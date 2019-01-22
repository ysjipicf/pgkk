package com.pgkk.ui.caipu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgkk.PgkkApplication;
import com.pgkk.R;
import com.pgkk.data.model.CaiPu;
import com.pgkk.di.HasComponent;
import com.pgkk.di.module.ActivityModule;
import com.pgkk.di.news.DaggerNewsComponent;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.di.news.NewsModule;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.CaiPuPresenter;
import com.pgkk.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/18.
 * <p>
 * 悬浮条 recyclerview
 */

public class SuspensionActivity extends BaseActivity implements NetView<CaiPu>, HasComponent<NewsComponent> {
    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout line_swipe_refresh;

    @BindView(R.id.feed_list)
    RecyclerView mFeedList;

    @Inject
    CaiPuPresenter mPresenter;

    GridLayoutManager gridLayoutManager;

    @Inject
    SuspensionAdapter feedAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String title;

    public static void launch(Context context, String title) {
        Intent intent = new Intent(context, SuspensionActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension);
        ButterKnife.bind(this);
        getComponent().inject(this);
        mPresenter.attachView(this);
        title = getIntent().getStringExtra("title");
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    void initView() {
        _setBack_Toolbar(toolbar,title);
        line_swipe_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        line_swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        setListener();
        mPresenter.loadData(title + ".json");
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mFeedList.setLayoutManager(gridLayoutManager);
        mFeedList.setHasFixedSize(true);
        mFeedList.setAdapter(feedAdapter);
    }

    void setListener() {
        line_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(title + ".json");
            }
        });

        feedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CaiPu.ResultBean.DataBean dataBean = (CaiPu.ResultBean.DataBean) adapter.getItem(position);
                CaiPuDetailActivity.launch(SuspensionActivity.this,dataBean);
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
    public void showContent(CaiPu data) {
        feedAdapter.setNewData(data.getResult().getData());
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showEmpty(String empty) {

    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(PgkkApplication.get(this).getComponent())
                .activityModule(new ActivityModule(this))
                .newsModule(new NewsModule())
                .build();
    }
}
