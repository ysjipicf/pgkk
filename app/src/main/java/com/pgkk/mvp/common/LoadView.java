package com.pgkk.mvp.common;


import androidx.annotation.UiThread;

import com.pgkk.mvp.MvpView;


/**
 * Created by tanxueze on 2017/12/6.
 *
 * 加载基类
 */

public interface LoadView  extends MvpView {


    @UiThread
    public void showLoading();

    @UiThread
    public void dismissLoading();

    @UiThread
    public void showError(Throwable throwable);

}
