package com.pgkk.ui.music.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.pgkk.R;
import com.pgkk.common.utils.AppLog;
import com.pgkk.data.model.Music;
import com.pgkk.ui.base.BaseActivity;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by tanxueze on 2018/1/12.
 */

public class MusicPlayActivity extends BaseActivity {

    public static final String DATA = "DATA";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.play_pause)
    ImageView mPlayPause;
    @BindView(R.id.model)
    ImageView model;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.headline)
    TextView title;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.controller)
    RelativeLayout controller;
    @BindView(R.id.current_time)
    TextView mStart;
    @BindView(R.id.progress)
    SeekBar mSeekbar;
    @BindView(R.id.total_time)
    TextView mEnd;
    @BindView(R.id.progress_controller)
    LinearLayout progressController;
    @BindView(R.id.viewpager)
    ViewPager mVpcontent;
    @BindView(R.id.progressBar1)
    ProgressBar mLoading;

    private Music tracksBean;
    private List<Music.TracksBean> nowPlayList;
    private CoverFlowAdapter adapter;
    private IjkMediaPlayer player;

    private int playerIndex = 0;
    public Handler mHandler;
    public Runnable mRunnable;
    private boolean isContainue = true;

    private int mode = 0;


    public static void launch(Context context, Music dataBean) {
        Intent intent = new Intent(context, MusicPlayActivity.class);
        intent.putExtra(DATA, dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_playl);
        ButterKnife.bind(this);
        tracksBean = (Music) getIntent().getSerializableExtra(DATA);
        initGallery();
        initListener();
        initMeida();
    }
    private void initMeida() {
        player = new IjkMediaPlayer();
        player.reset();
        player.setOnPreparedListener(iMediaPlayer -> {
            iMediaPlayer.start();
            mStart.setText("00:00");
            AppLog.e(iMediaPlayer.getDuration() + "  获取时间 ");
            mEnd.setText(DateUtils.formatElapsedTime(iMediaPlayer.getDuration() / 1000));
        });
        player.setOnErrorListener((iMediaPlayer, i, i1) -> {
            iMediaPlayer.pause();
            return false;
        });
        player.setOnCompletionListener(iMediaPlayer -> {
            changeMusic();
        });
        player.setOnSeekCompleteListener(IMediaPlayer::start);
        playMusic();
    }

    private void changeMusic() {
        int index = playerIndex;
        switch (mode) {
            case 1:
                index += 1;
                break;
            case 2:
                playMusic();
                break;
            case 0:
                Random random = new Random();
                int anInt = random.nextInt(10);
                if (anInt == index) {
                    anInt = random.nextInt(10);
                }
                index = anInt;
                break;
        }
        mVpcontent.setCurrentItem(index, true);
    }

    private void playMusic() {
        try {
            player.reset();
            AppLog.e(nowPlayList.get(playerIndex).getFilename());
            player.setDataSource(nowPlayList.get(playerIndex).getFilename());
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        back.setOnClickListener(v -> finish());
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                double time = player.getDuration() * (seekBar.getProgress() * 0.01);
                player.seekTo((long) time);
            }
        });
        mPlayPause.setOnClickListener(v -> {
            if (player.isPlaying()) {
                mPlayPause.setImageResource(R.drawable.music_play_msc_icon);
                player.pause();
            } else {
                player.start();
                mPlayPause.setImageResource(R.drawable.music_pause_msc_icon);
            }
        });

        model.setOnClickListener(v -> {
            switch (mode) {
                case 0:
                    mode = 1;
                    model.setImageResource(R.drawable.music_circle_icon);
                    break;
                case 1:
                    mode = 2;
                    model.setImageResource(R.drawable.music_single_play_icon);
                    break;
                case 2:
                    mode = 0;
                    model.setImageResource(R.drawable.music_random_icon);
                    break;
            }
        });

        next.setOnClickListener(v -> {
            changeMusic();
        });
        updateProgress();
    }

    private void updateProgress() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (isContainue) {
                    mHandler.postDelayed(this, 1000);
                }
                runOnUiThread(() -> {
                    if (player != null) {
                        int progress = (int) ((player.getCurrentPosition() * 1f / player.getDuration() * 1f) * 100);
                        mSeekbar.setProgress(progress);
                        mStart.setText(DateUtils.formatElapsedTime(player.getCurrentPosition() / 1000));
                    }
                });
            }
        };
        mHandler = new Handler();
        if (isContainue) {
            mHandler.postDelayed(mRunnable, 1000);
        }
    }


    private void initGallery() {
        final List<Music.TracksBean> nowPlayList = tracksBean.getTracks();

        if (nowPlayList == null || nowPlayList.size() == 0) {
            return;
        }
        this.nowPlayList = nowPlayList;


        adapter = new CoverFlowAdapter(nowPlayList, this);
        mVpcontent.setAdapter(adapter);
        mVpcontent.setOffscreenPageLimit(3);
        mVpcontent.setPageTransformer(true, new ScalePageTransformer());
        title.setText(nowPlayList.get(0).getSongname());
        name.setText(nowPlayList.get(0).getSonger());
        mVpcontent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                playerIndex = position;
                title.setText(nowPlayList.get(position).getSongname());
                name.setText(nowPlayList.get(position).getSonger());
                playMusic();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                }
            }
        });

    }

    public class ScalePageTransformer implements ViewPager.PageTransformer {

        public static final float MAX_SCALE = 1.0f;
        public static final float MIN_SCALE = 0.8f;

        @Override
        public void transformPage(View page, float position) {

            if (position < -1) {
                position = -1;
            } else if (position > 1) {
                position = 1;
            }

            float tempScale = position < 0 ? 1 + position : 1 - position;

            float slope = (MAX_SCALE - MIN_SCALE) / 1;
            //一个公式
            float scaleValue = MIN_SCALE + tempScale * slope;

            //设置缩放比例
            page.setScaleX(scaleValue);
            page.setScaleY(scaleValue);
            //设置透明度
            page.setAlpha(scaleValue);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isContainue = false;
        if (player.isPlaying()) {
            player.pause();
            player.stop();
            player.release();
            player = null;
        }
    }
}
