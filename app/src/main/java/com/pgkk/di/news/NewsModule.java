package com.pgkk.di.news;


import android.app.Activity;

import com.google.gson.Gson;
import com.pgkk.data.api.CaipuApi;
import com.pgkk.data.api.CaipuService;
import com.pgkk.data.api.MeiziApi;
import com.pgkk.data.api.MeiziService;
import com.pgkk.data.api.MusicApi;
import com.pgkk.data.api.MusicService;
import com.pgkk.data.api.NewsApi;
import com.pgkk.data.api.NewsService;
import com.pgkk.data.api.ReadApi;
import com.pgkk.data.api.ReadService;
import com.pgkk.data.api.VideoApi;
import com.pgkk.data.api.VideoService;
import com.pgkk.data.model.Read;
import com.pgkk.data.model.Video;
import com.pgkk.ui.caipu.SuspensionAdapter;
import com.pgkk.ui.meizi.MeiziAdapter;
import com.pgkk.ui.music.MusicAdapter;
import com.pgkk.ui.news.NewsAdapter;
import com.pgkk.ui.read.ReadAdapter;
import com.pgkk.ui.video.VideoAdapter;
import com.pgkk.ui.video.VideoListAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tanxueze on 2017/12/11.
 */
@Module
public class NewsModule {

    //news
    @Provides
    NewsApi provideNewsApi(NewsService dataSource) {
        return dataSource;
    }


    @Provides
    NewsAdapter provideNewsAdapter() {
        return new NewsAdapter(null);
    }

    @Provides
    List<NewsAdapter.MultipleItem> provideList() {
        return new LinkedList<>();
    }


    //caipu
    @Provides
    CaipuApi provideCaipuApi(CaipuService caipuService) {
        return caipuService;
    }

    @Provides
    SuspensionAdapter provideFeedAdapter() {
        return new SuspensionAdapter(null);
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }


    //meizi
    @Provides
    MeiziApi provideMeiziApi(MeiziService meiziService) {
        return meiziService;
    }

    @Provides
    MeiziAdapter provideMeiziAdapter() {
        return new MeiziAdapter(null);
    }


    //video
    @Provides
    VideoApi provideVideoApi(VideoService videoService) {
        return videoService;
    }

    @Provides
    VideoAdapter provideVideoAdapter(Activity activity, List<Video.ItemListBean> listBeans) {
        return new VideoAdapter(activity, listBeans);
    }

    @Provides
    List<Video.ItemListBean> providemVideoListBean() {
        return new LinkedList<>();
    }

    @Provides
    VideoListAdapter provideVideoListAdapter() {
        return new VideoListAdapter(null);
    }


    //music
    @Provides
    MusicApi provideMusicApi(MusicService videoService) {
        return videoService;
    }

    @Provides
    MusicAdapter provideMusicAdapter() {
        return new MusicAdapter(null);
    }


    //read
    @Provides
    ReadApi provideReadApi(ReadService readService) {
        return readService;
    }

    @Provides
    ReadAdapter provideReadAdapter() {
        return new ReadAdapter(null);
    }
}
