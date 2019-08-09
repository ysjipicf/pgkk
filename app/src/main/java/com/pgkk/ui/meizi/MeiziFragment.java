package com.pgkk.ui.meizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgkk.R;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.data.model.Meizi;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.MeiziPresenter;
import com.pgkk.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/27.
 */

public class MeiziFragment extends BaseFragment implements NetView<Meizi> {
    @Inject
    MeiziPresenter meiziPresenter;

    @Inject
    MeiziAdapter meiziAdapter;

    @BindView(R.id.line_swipe_refresh)
    SwipeRefreshLayout line_swipe_refresh;

    @BindView(R.id.line_recycler)
    RecyclerView line_recycler;

    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    StaggeredGridLayoutManager linearLayoutManager;

    int lastVisibleItem;

    ItemTouchHelper itemTouchHelper;

    Meizi data;

    int page = 1;

    public MeiziFragment() {

    }

    public static MeiziFragment newInstance() {
        MeiziFragment fragment = new MeiziFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, contentView);
        getActivity().setTitle(R.string.menu_slideshow_title);
        initView();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
        meiziPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meiziPresenter.loadMeizi(page);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        meiziPresenter.detachView();
    }


    void initView() {
        //左右上下移动
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager || recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Meizi.ResultsBean moveItem = data.getResults().get(from);
                data.getResults().remove(from);
                data.getResults().add(to, moveItem);
                meiziAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }
        });

        itemTouchHelper.attachToRecyclerView(line_recycler);

        line_swipe_refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        line_swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        line_recycler.setLayoutManager(linearLayoutManager);
        //防止item交换位置，加载完成的来回晃动
        linearLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        line_recycler.setHasFixedSize(true);

        line_recycler.setAdapter(meiziAdapter);

        initListener();
    }

    private void initListener() {
        line_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                meiziPresenter.loadMeizi(page);
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
                    meiziPresenter.loadMeizi(++page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见视图在适配器的位置。
                int[] positions = linearLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem = Math.max(positions[0], positions[1]);
            }
        });





        meiziAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                itemTouchHelper.startDrag(line_recycler.getChildViewHolder(view));
                return false;
            }
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
    public void showContent(Meizi data) {
        this.data = data;
        if(page ==1){
            meiziAdapter.setNewData(new ArrayList(new HashSet(data.getResults())));
        }else {
            meiziAdapter.addData(new ArrayList(new HashSet(data.getResults())));
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
