package com.pgkk.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pgkk.R;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.data.model.Video;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.VideoPresenter;
import com.pgkk.ui.base.BaseFragment;
import com.pgkk.ui.widget.PagingOnScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class VideoFragment extends BaseFragment implements NetView<Video> {
    @Inject
    VideoPresenter videoPresenter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.line_recycler)
    RecyclerView recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    @Inject
    List<Video.ItemListBean> mVideoListBean;

    @Inject
    VideoAdapter videoAdapter;

    private String date = null;


    public VideoFragment() {

    }

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
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

        recycler.setAdapter(videoAdapter);
        recycler.addOnScrollListener(new PagingOnScrollListener(layout, 0) {
            @Override
            public void onLoadMore() {
                videoPresenter.loadVideoList(date);
            }
        });

        videoAdapter.setOnItemClickListener((position, view, vh) -> VideoDetailActivity.launch(getActivity(),mVideoListBean.get(position).getData()));
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
        if (refresh.isRefreshing()) {
            refresh.setRefreshing(false);
            mVideoListBean.clear();
            videoAdapter.clear();
            videoAdapter.addAll(data.getItemList());
            recycler.setAdapter(videoAdapter);
        }else{
            videoAdapter.addAll(data.getItemList());
        }
        mVideoListBean.addAll(data.getItemList());

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
