package com.pgkk.ui.music;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.konifar.fab_transformation.FabTransformation;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Music;
import com.pgkk.ui.base.BaseActivity;
import com.pgkk.ui.music.play.MusicPlayActivity;
import com.pgkk.ui.widget.AppBarStateChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2018/1/12.
 */

public class MusicDetialActivity extends BaseActivity {
    private static String DATA = "data";
    @BindView(R.id.img_detail)
    ImageView mImgDetail;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collToolBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    Music dataBean;
    MusicItemAdapter musicItemAdapter;

    public static void launch(Context context, Music dataBean) {
        Intent intent = new Intent(context, MusicDetialActivity.class);
        intent.putExtra(DATA, dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
        ButterKnife.bind(this);
        dataBean = (Music) getIntent().getSerializableExtra(DATA);
        _setBack_Toolbar_CollToolBar(mToolbar, "", collToolBar, Color.WHITE);
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (state == State.EXPANDED) {//展开状态
                    collToolBar.setTitle("");
                } else if (state == State.COLLAPSED) {//折叠状态
                    collToolBar.setTitle(dataBean.getMname());
                } else {//中间状态
                }
            }
        });
        _collToolbar_FloatButton_initColor(dataBean.getOphoto(), collToolBar, mFab);
        initView();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ImageLoader.load(this, dataBean.getOphoto(), mImgDetail);
        musicItemAdapter = new MusicItemAdapter(dataBean.getTracks());
        recycler.setAdapter(musicItemAdapter);
//        recycler.setLoadingMoreEnabled(false);
//        recycler.setPullRefreshEnabled(false);
        initHeadView();
        mFab.setOnClickListener(v -> {
            if (mFab.getVisibility() == View.VISIBLE) {
                FabTransformation.with(mFab).setListener(new FabTransformation.OnTransformListener() {
                    @Override
                    public void onStartTransform() {
                    }

                    @Override
                    public void onEndTransform() {
                        MusicPlayActivity.launch(MusicDetialActivity.this,dataBean);
                    }
                }).transformTo(mImgDetail);
            }
        });
    }


    private void initHeadView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_music_detial_top, null);
        TextView title = (TextView) inflate.findViewById(R.id.title);
        ExpandableTextView expandableTextView = (ExpandableTextView) inflate.findViewById(R.id.expand_text_view);
        expandableTextView.setText(dataBean.getMdesc());
        title.setText(dataBean.getMname());
//        musicItemAdapter.addHeaderView(inflate);
    }

    @Override
    public void onResume() {
        super.onResume();
        FabTransformation.with(mFab).setListener(new FabTransformation.OnTransformListener() {
            @Override
            public void onStartTransform() {
            }

            @Override
            public void onEndTransform() {
                if (mImgDetail.getVisibility() == View.INVISIBLE) {
                    mImgDetail.setVisibility(View.VISIBLE);
                }
            }
        }).transformFrom(mImgDetail);
    }
}
