package com.pgkk.presenter;

import com.pgkk.data.api.MusicApi;
import com.pgkk.data.api.VideoApi;
import com.pgkk.data.model.Music;
import com.pgkk.data.model.Video;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class MusicPresenter extends RxMvpPresenter<NetView<List<Music>>> {

    private final MusicApi mainApi;

    @Inject
    public MusicPresenter(MusicApi mainApi) {
        this.mainApi = mainApi;
    }


    public void loadMusicList(int page) {
        mCompositeSubscription.add(mainApi.loadMusic(page + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> getmMvpView().showLoading())
                .subscribe(new ResponseObserver<List<Music>>() {
                    @Override
                    public void onSuccess(List<Music> request) {
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
