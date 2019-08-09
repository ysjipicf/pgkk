package com.pgkk.data.api;

import android.app.Application;

import com.google.gson.Gson;
import com.pgkk.common.utils.AssestUtils;
import com.pgkk.data.model.CaiPu;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tanxueze on 2017/12/8.
 */

public class CaipuService implements CaipuApi {

    @Inject
    Application mContext;

    @Inject
    public CaipuService() {
    }

    @Inject
    Gson gson;

    @Override
    public Observable<CaiPu> lingdata(final String fileName) {
        //创建被观察者
        return Observable.create(new Observable.OnSubscribe<CaiPu>() {
            @Override
            public void call(final Subscriber<? super CaiPu> subscriber) {
                try {
                    //判断观察者和被观察者是否存在订阅关系
                    subscriber.onNext(gson.fromJson(AssestUtils.readAssetsFile(fileName, mContext), CaiPu.class));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
