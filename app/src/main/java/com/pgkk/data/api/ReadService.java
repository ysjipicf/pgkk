package com.pgkk.data.api;

import android.app.Application;

import com.pgkk.data.model.Read;
import com.pgkk.data.model.ReadDetail;
import com.pgkk.data.model.Video;
import com.pgkk.data.net.ReadProjetRetrofit;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by tanxueze on 2017/12/8.
 */

public class ReadService implements ReadApi {

    @Inject
    Application mContext;

    @Inject
    ReadProjetRetrofit mRetrofit;

    @Inject
    public ReadService() {

    }

    @Override
    public Observable<Read> getReadList(int page) {
        return mRetrofit.get(mContext).create(ReadApi.class).getReadList(page);
    }

    @Override
    public Observable<ReadDetail> getReadDetail(int id, int sourceId) {
        return mRetrofit.get(mContext).create(ReadApi.class).getReadDetail(id,sourceId);
    }
}
