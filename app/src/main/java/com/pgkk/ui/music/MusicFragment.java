package com.pgkk.ui.music;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pgkk.R;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.data.model.Music;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.MusicPresenter;
import com.pgkk.ui.base.BaseFragment;
import com.pgkk.ui.widget.PagingOnScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class MusicFragment extends BaseFragment implements NetView<List<Music>> {
    @Inject
    MusicPresenter musicPresenter;

    @Inject
    MusicAdapter musicAdapter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout line_swipe_refresh;

    @BindView(R.id.line_recycler)
    RecyclerView line_recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    GridLayoutManager linearLayoutManager;

    int page = 1;

    public MusicFragment() {

    }

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.menu_music_title);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
        musicPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        musicPresenter.loadMusicList(page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPresenter.detachView();
    }


    void initView() {
        line_swipe_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        line_swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        linearLayoutManager = new GridLayoutManager(getActivity(), 2);

        line_recycler.setLayoutManager(linearLayoutManager);

        line_recycler.setHasFixedSize(true);

        line_recycler.setAdapter(musicAdapter);

        initListener();
    }

    private void initListener() {
        line_swipe_refresh.setOnRefreshListener(() -> {
            page = 1;
            musicPresenter.loadMusicList(page);
        });
        line_recycler.addOnScrollListener(new PagingOnScrollListener(linearLayoutManager, 0) {
            @Override
            public void onLoadMore() {
                musicPresenter.loadMusicList(++page);
            }
        });

        musicAdapter.setOnItemClickListener((adapter, view, position) -> {
            MusicDetialActivity.launch(getActivity(), (Music) adapter.getItem(position));
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
    public void showContent(List<Music> data) {
        if (page == 1) {
            musicAdapter.setNewData(data);
        } else {
            musicAdapter.addData(data);
        }
    }

    @Override
    public void showError(Throwable throwable) {
    }

    @Override
    public void showEmpty(String empty) {
        SnackbarUtil.ShortSnackbar(constraintLayout, empty, SnackbarUtil.blue).show();
    }
}
