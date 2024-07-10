package com.pgkk.ui.base;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pgkk.common.utils.AppLog;
import com.pgkk.common.utils.image.ImageLoader;

/**
 * Created by tanxueze on 2017/12/6.
 * <p>
 * Android-Iconics 使用
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * @param mToolbar
     * @param tool_title
     * @param collToolBar
     * @param color       collToolBar 字体颜色
     */
    protected void _setBack_Toolbar_CollToolBar(Toolbar mToolbar, String tool_title, CollapsingToolbarLayout collToolBar, int color) {
        mToolbar.setTitle(tool_title);
        collToolBar.setExpandedTitleColor(Color.WHITE);     //设置展开时Title的颜色
        collToolBar.setCollapsedTitleTextColor(Color.WHITE);    //设置收缩时Title的颜色
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * @param mToolbar
     * @param tool_title
     */
    protected void _setBack_Toolbar(Toolbar mToolbar, String tool_title) {
        mToolbar.setTitle(tool_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }


    /**
     * 根据 下载的图片 设置 layout 和 tab的背景颜色
     *
     * @param url
     * @param collapsingToolbarLayout
     * @param floatingActionButton
     */
    protected void _collToolbar_FloatButton_initColor(String url, CollapsingToolbarLayout collapsingToolbarLayout, FloatingActionButton floatingActionButton) {
        final int[] color = {0xffffcc00};
        ImageLoader.displayImage(this, url, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Palette.from(resource).generate(palette -> {
                    try {
                        color[0] = palette.getDarkMutedSwatch().getRgb();
                    } catch (Exception e) {
                        AppLog.d(e.getMessage());
                    }
                    if (collapsingToolbarLayout != null)
                        collapsingToolbarLayout.setContentScrimColor(color[0]);
                    if (floatingActionButton != null)
                        floatingActionButton.setBackgroundTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{color[0]}));

                });

            }
        });
    }
}
