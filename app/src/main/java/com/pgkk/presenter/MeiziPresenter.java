package com.pgkk.presenter;

import com.pgkk.data.api.MeiziApi;
import com.pgkk.data.model.Meizi;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class MeiziPresenter extends RxMvpPresenter<NetView<Meizi>> {

    private final MeiziApi mainApi;


    @Inject
    public MeiziPresenter(MeiziApi mainApi) {
        this.mainApi = mainApi;
    }


    public void loadMeizi(int page) {
        mCompositeSubscription.add(mainApi.loadMeizi(page + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> getmMvpView().showLoading())
                .subscribe(new ResponseObserver<Meizi>() {
                    @Override
                    public void onSuccess(Meizi request) {
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
