package com.pgkk.mvp;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by tanxueze on 2017/12/7.
 */

public class RxMvpPresenter<V extends MvpView> extends BaseMvpPresenter<V> {

    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();

        mCompositeSubscription.clear();
        mCompositeSubscription = null;

    }
}
