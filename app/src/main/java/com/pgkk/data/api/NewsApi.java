package com.pgkk.data.api;


import com.pgkk.data.model.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface NewsApi {

    @GET("index")
    Observable<News> loadNews(@Query("type") String type, @Query("key") String key);

}
