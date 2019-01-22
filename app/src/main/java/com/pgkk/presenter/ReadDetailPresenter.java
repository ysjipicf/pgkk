package com.pgkk.presenter;

import com.pgkk.data.api.ReadApi;
import com.pgkk.data.model.Read;
import com.pgkk.data.model.ReadDetail;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class ReadDetailPresenter extends RxMvpPresenter<NetView<ReadDetail>> {

    private final ReadApi mainApi;

    @Inject
    public ReadDetailPresenter(ReadApi mainApi) {
        this.mainApi = mainApi;
    }

    public void loadReadDetail(int id, int sourceId) {
        mCompositeSubscription.add(mainApi.getReadDetail(id,sourceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> getmMvpView().showLoading())
                .subscribe(new ResponseObserver<ReadDetail>() {
                    @Override
                    public void onSuccess(ReadDetail request) {
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
