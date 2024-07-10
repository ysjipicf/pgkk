package com.pgkk.ui.video;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.konifar.fab_transformation.FabTransformation;
import com.pgkk.R;
import com.pgkk.common.utils.AppLog;
import com.pgkk.common.utils.DateUtils;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Video;
import com.pgkk.ui.base.BaseActivity;
import com.pgkk.ui.widget.AppBarStateChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2018/1/11.
 */

public class VideoDetailActivity extends BaseActivity {

    private static String DATA = "data";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collToolBar;
    @BindView(R.id.img_detail)
    ImageView mImgDetail;
    @BindView(R.id.bg_image)
    ImageView bgImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.app_bar)
    AppBarLayout app_bar;

    Video.ItemListBean.DataBean dataBean;

    int color = 0xffffcc00;

    public static void launch(Context context, Video.ItemListBean.DataBean dataBean) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(DATA, dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        dataBean = (Video.ItemListBean.DataBean) getIntent().getSerializableExtra(DATA);
        ButterKnife.bind(this);
        AppLog.e(dataBean.toString());
        _setBack_Toolbar_CollToolBar(mToolbar, "", collToolBar, Color.WHITE);
        app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (state == State.EXPANDED) {//展开状态
                    collToolBar.setTitle("");
                } else if (state == State.COLLAPSED) {//折叠状态
                    collToolBar.setTitle(dataBean.getTitle());
                } else {//中间状态
                }
            }
        });
        initView();
        _collToolbar_FloatButton_initColor(dataBean.getCover().getBlurred(),collToolBar,mFab);
    }

    private void initView() {
        mFab.setBackgroundTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{0xffffcc00}));
        ImageLoader.displayImage(this, dataBean.getCover().getDetail(), mImgDetail);
        ImageLoader.displayImage(this, dataBean.getCover().getBlurred(), bgImage);
        title.setText(dataBean.getTitle());
        type.setText("#" + dataBean.getCategory() + " " + " / " + " " + DateUtils.formatTime2(dataBean.getDuration()));
        description.setText(dataBean.getDescription());
        setListener();
    }

    private void setListener() {
        /**
         * 播放展开
         */
        mFab.setOnClickListener(v -> {
            if (mFab.getVisibility() == View.VISIBLE) {
                FabTransformation.with(mFab).setListener(new FabTransformation.OnTransformListener() {
                    @Override
                    public void onStartTransform() {
                    }

                    @Override
                    public void onEndTransform() {
                        PlayActivity.launch(VideoDetailActivity.this, mImgDetail, dataBean);
                    }
                }).transformTo(mImgDetail);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        /**
         * 播放收缩
         */
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
