package com.pgkk.data.rx;



import com.pgkk.common.utils.AppLog;

import rx.Subscriber;

/**
 * Created by tanxueze on 2017/12/7.
 */

public abstract class ResponseObserver<T> extends Subscriber<T>{
    @Override
    public void onCompleted() {
        AppLog.d("onCompleted");
    }

    @Override
    public void onNext(T t) {
        AppLog.d("onNext");
        onSuccess(t);
    }


    public abstract void onSuccess(T t);
}
