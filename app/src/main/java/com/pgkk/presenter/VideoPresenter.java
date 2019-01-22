package com.pgkk.presenter;

import com.pgkk.data.api.VideoApi;
import com.pgkk.data.model.Video;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class VideoPresenter extends RxMvpPresenter<NetView<Video>> {

    private final VideoApi mainApi;

    @Inject
    public VideoPresenter(VideoApi mainApi) {
        this.mainApi = mainApi;
    }


    public void loadVideoList(String data) {
        mCompositeSubscription.add(mainApi.getVideoList(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getmMvpView().showLoading();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        getmMvpView().showLoading();
                    }
                })
                .subscribe(new ResponseObserver<Video>() {
                    @Override
                    public void onSuccess(Video request) {
                        getmMvpView().showContent(request);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getmMvpView().showError(e);
                    }

                    @Override
                    public void onCompleted() {
                        getmMvpView().dismissLoading();
                    }
                }));
    }
}
