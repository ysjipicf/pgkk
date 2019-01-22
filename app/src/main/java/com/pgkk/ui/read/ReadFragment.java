package com.pgkk.ui.read;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgkk.R;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.data.model.Music;
import com.pgkk.data.model.Read;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.MusicPresenter;
import com.pgkk.presenter.ReadPresenter;
import com.pgkk.ui.base.BaseFragment;
import com.pgkk.ui.music.MusicAdapter;
import com.pgkk.ui.music.MusicDetialActivity;
import com.pgkk.ui.widget.PagingOnScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class ReadFragment extends BaseFragment implements NetView<Read> {
    @Inject
    ReadPresenter readPresenter;

    @Inject
    ReadAdapter readAdapter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout line_swipe_refresh;

    @BindView(R.id.line_recycler)
    RecyclerView line_recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    LinearLayoutManager linearLayoutManager;

    int page = 0;

    public ReadFragment() {

    }

    public static ReadFragment newInstance() {
        ReadFragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.menu_read_title);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
        readPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readPresenter.loadReadList(page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        readPresenter.detachView();
    }


    void initView() {
        line_swipe_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        line_swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        line_recycler.setLayoutManager(linearLayoutManager);
        line_recycler.setHasFixedSize(true);
        line_recycler.setAdapter(readAdapter);
        initListener();
    }

    private void initListener() {
        line_swipe_refresh.setOnRefreshListener(() -> {
            page = 0;
            readPresenter.loadReadList(page);
        });
        line_recycler.addOnScrollListener(new PagingOnScrollListener(linearLayoutManager, 0) {
            @Override
            public void onLoadMore() {
                readPresenter.loadReadList(page);
            }
        });

        readAdapter.setOnItemClickListener((adapter, view, position) -> {
            ReadDetailActivity.launch(getActivity(), (Read.DataBean) adapter.getItem(position));
        });
    }

    @Override
    public void showLoading() {
        if (page == 1) line_swipe_refresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        line_swipe_refresh.setRefreshing(false);
    }

    @Override
    public void showContent(Read data) {
        if (page == 1) {
            readAdapter.setNewData(data.getData());
        } else {
            readAdapter.addData(data.getData());
        }
        page = Integer.parseInt(data.getData().get(data.getData().size()-1).getId());
    }
    @Override
    public void showError(Throwable throwable) {
    }

    @Override
    public void showEmpty(String empty) {
        SnackbarUtil.ShortSnackbar(constraintLayout, empty, SnackbarUtil.blue).show();
    }
}
