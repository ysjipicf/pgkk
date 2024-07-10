package com.pgkk.mvp.common;


import androidx.annotation.UiThread;

import com.pgkk.mvp.MvpView;


/**
 * Created by tanxueze on 2017/12/6.
 *
 * 网络列表加载基类
 */

public interface NetView<M>  extends MvpView {

    @UiThread
    public void showLoading();

    @UiThread
    public void dismissLoading();

    @UiThread
    public void showContent(M data);

    @UiThread
    public void showError(Throwable throwable);

    @UiThread
    public void showEmpty(String empty);
}
