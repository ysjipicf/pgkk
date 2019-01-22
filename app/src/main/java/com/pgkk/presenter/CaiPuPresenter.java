package com.pgkk.presenter;


import com.pgkk.data.api.CaipuApi;
import com.pgkk.data.model.CaiPu;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/20.
 */

public class CaiPuPresenter extends RxMvpPresenter<NetView<CaiPu>> {

    private final CaipuApi caipuApi;

    @Inject
    public CaiPuPresenter(CaipuApi caipuApi) {
        this.caipuApi = caipuApi;
    }

    public void loadData(String filename) {
        mCompositeSubscription.add(caipuApi.lingdata(filename)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> {

                })
                .subscribe(new ResponseObserver<CaiPu>() {
                    @Override
                    public void onSuccess(CaiPu request) {
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



//    public  void loadTitle(){
//
//    }
}
