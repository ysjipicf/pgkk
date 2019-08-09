package com.pgkk.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgkk.R;
import com.pgkk.common.utils.AppLog;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.data.model.Video;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.VideoPresenter;
import com.pgkk.ui.base.BaseFragment;
import com.pgkk.ui.widget.PagingOnScrollListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class VideoListPlayFragment extends BaseFragment implements NetView<Video> {
    @Inject
    VideoPresenter videoPresenter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.line_recycler)
    RecyclerView recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    VideoListAdapter videoAdapter;

    private String date = null;


    public VideoListPlayFragment() {

    }

    public static VideoListPlayFragment newInstance() {
        VideoListPlayFragment fragment = new VideoListPlayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.menu_video_title);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
        videoPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoPresenter.loadVideoList("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoPresenter.detachView();
    }


    void initView() {
        refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        refresh.setProgressViewOffset(false, 100, 200);

        refresh.setOnRefreshListener(() -> videoPresenter.loadVideoList(""));

        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);
        recycler.setHasFixedSize(true);
        videoAdapter = new VideoListAdapter(null);
        recycler.setAdapter(videoAdapter);
        recycler.addOnScrollListener(new PagingOnScrollListener(layout, 0) {
            @Override
            public void onLoadMore() {
                videoPresenter.loadVideoList(date);
            }
        });
    }


    @Override
    public void showLoading() {
        if (date ==null)
            refresh.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        refresh.setRefreshing(false);
    }

    @Override
    public void showContent(Video data) {
        AppLog.e(data.toString());
        if (date == null) {
            videoAdapter.setNewData(data.getItemList());
        } else {
            videoAdapter.addData(data.getItemList());
        }
        int end = data.getNextPageUrl().lastIndexOf("&num");
        int start = data.getNextPageUrl().lastIndexOf("date=");
        date = data.getNextPageUrl().substring(start + 5, end);
    }



    @Override
    public void showError(Throwable throwable) {
    }

    @Override
    public void showEmpty(String empty) {
        SnackbarUtil.ShortSnackbar(constraintLayout, empty, SnackbarUtil.blue).show();
    }
}
