package com.pgkk.data.api;

import android.app.Application;

import com.pgkk.data.model.Meizi;
import com.pgkk.data.net.MeiziProjetRetrofit;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by tanxueze on 2017/12/8.
 */

public class MeiziService implements MeiziApi {

    @Inject
    Application mContext;

    @Inject
    MeiziProjetRetrofit mRetrofit;

    @Inject
    public MeiziService() {

    }

    @Override
    public Observable<Meizi> loadMeizi(String page) {
        return mRetrofit.get(mContext).create(MeiziApi.class).loadMeizi(page);
    }


}
