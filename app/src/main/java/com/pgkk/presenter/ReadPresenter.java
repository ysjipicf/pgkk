package com.pgkk.presenter;

import com.pgkk.data.api.ReadApi;
import com.pgkk.data.model.Read;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class ReadPresenter extends RxMvpPresenter<NetView<Read>> {

    private final ReadApi mainApi;

    @Inject
    public ReadPresenter(ReadApi mainApi) {
        this.mainApi = mainApi;
    }


    public void loadReadList(int page) {
        mCompositeSubscription.add(mainApi.getReadList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> getmMvpView().showLoading())
                .subscribe(new ResponseObserver<Read>() {
                    @Override
                    public void onSuccess(Read request) {
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
