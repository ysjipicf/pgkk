package com.pgkk.data.api;

import android.app.Application;

import com.pgkk.data.model.Video;
import com.pgkk.data.net.VideoProjetRetrofit;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by tanxueze on 2017/12/8.
 */

public class VideoService implements VideoApi {

    @Inject
    Application mContext;

    @Inject
    VideoProjetRetrofit mRetrofit;

    @Inject
    public VideoService() {

    }

    @Override
    public Observable<Video> getVideoList(String date) {
        return mRetrofit.get(mContext).create(VideoApi.class).getVideoList(date);
    }
}
