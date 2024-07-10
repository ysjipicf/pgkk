package com.pgkk.mvp;


import androidx.annotation.UiThread;

/**
 * Created by tanxueze on 2017/12/6.
 * 关联MvpView
 */

public interface MvpPresenter<V extends MvpView> {


    /**
     * view attach presentr
     *
     * @param view
     */
    @UiThread
    void attachView(V view);

    /**
     * <code>Activity.detachVeiw<code/>
     * <code>Fragment.onDestroyView()</code>
     */
    @UiThread
    void detachView();
}
