package com.pgkk.data.api;

import android.app.Application;

import com.pgkk.data.model.Music;
import com.pgkk.data.net.MusicProjetRetrofit;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by tanxueze on 2017/12/8.
 */

public class MusicService implements MusicApi {

    @Inject
    Application mContext;

    @Inject
    MusicProjetRetrofit mRetrofit;

    @Inject
    public MusicService() {

    }

    @Override
    public Observable<List<Music>> loadMusic(String page) {
        return mRetrofit.get(mContext).create(MusicApi.class).loadMusic(page);    }
}
