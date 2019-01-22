package com.pgkk.data.api;

import android.app.Application;


import com.pgkk.data.model.News;
import com.pgkk.data.net.BaseProjetRetrofit;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by tanxueze on 2017/12/8.
 */

public class NewsService implements NewsApi {

    @Inject
    Application mContext;

    @Inject
    BaseProjetRetrofit mRetrofit;

    @Inject
    public NewsService() {

    }

    @Override
    public Observable<News> loadNews(String type, String key) {
        return mRetrofit.get(mContext).create(NewsApi.class).loadNews(type,key);
    }


}
