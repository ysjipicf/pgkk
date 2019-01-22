package com.pgkk.presenter;

import android.text.TextUtils;

import com.pgkk.data.api.NewsApi;
import com.pgkk.data.model.News;
import com.pgkk.data.rx.ResponseObserver;
import com.pgkk.mvp.RxMvpPresenter;
import com.pgkk.mvp.common.NetView;
import com.pgkk.ui.news.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by tanxueze on 2017/12/14.
 */

public class NewsPresenter extends RxMvpPresenter<NetView<List<NewsAdapter.MultipleItem>>> {

    private final NewsApi mainApi;

    public String key = "0355d38f5d4d510e91e636acbe1ec2e6";

    @Inject
    public NewsPresenter(NewsApi mainApi) {
        this.mainApi = mainApi;
    }


    public void loadNews(String type) {
        mCompositeSubscription.add(mainApi.loadNews(type, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> getmMvpView().showLoading())
                .doOnTerminate(() -> getmMvpView().showLoading())
                .subscribe(new ResponseObserver<News>() {
                    @Override
                    public void onSuccess(News request) {
                        if (request.getError_code() == 10012 || request.getResult().getData() == null || request.getResult().getData().size() == 0)
                            getmMvpView().showEmpty(request.getReason());

                        List<NewsAdapter.MultipleItem> lists = new ArrayList<>();
                        for (int i = 0; i < request.getResult().getData().size(); i++) {
                            News.ResultBean.DataBean dataBean = request.getResult().getData().get(i);
                            if ((!TextUtils.isEmpty(dataBean.getThumbnail_pic_s()) && !TextUtils.isEmpty(dataBean.getThumbnail_pic_s02()) && !TextUtils.isEmpty(dataBean.getThumbnail_pic_s03()))) {
                                lists.add(new NewsAdapter.MultipleItem(NewsAdapter.MultipleItem.ITEM_2, dataBean));
                            } else if (!TextUtils.isEmpty(dataBean.getThumbnail_pic_s())) {
                                lists.add(new NewsAdapter.MultipleItem(NewsAdapter.MultipleItem.TIEM_1, dataBean));
                            }
                        }

                        getmMvpView().showContent(lists);
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
