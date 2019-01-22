package com.pgkk.mvp;

/**
 * Created by tanxueze on 2017/12/6.
 */

public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;

    @Override
    public void attachView(V view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getmMvpView(){
        return mMvpView;
    }

    public void checkViewAttached(){
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException{
        public MvpViewNotAttachedException(){
            super("Please call Presenter");
        }
    }
}

