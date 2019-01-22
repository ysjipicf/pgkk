package com.pgkk.data.net;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tanxueze on 2017/12/25.
 */

public abstract class BaseRetrofit {

    public Retrofit get(Context context) {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(getApiEndpoint().getEndpoint())
                .client(getHttpClient(context))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());


        return builder.build();
    }

    public abstract ApiEndpoint getApiEndpoint();
    public abstract OkHttpClient getHttpClient(Context context);

}
