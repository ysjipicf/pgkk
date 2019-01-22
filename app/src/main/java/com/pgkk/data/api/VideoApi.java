package com.pgkk.data.api;


import com.pgkk.data.model.Video;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface VideoApi {

    @GET("tabs/selected")
    Observable<Video> getVideoList(@Query("date") String date);

}
