package com.pgkk.data.api;


import com.pgkk.data.model.Meizi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface MeiziApi {

    @GET("15/{page}")
    Observable<Meizi> loadMeizi(@Path("page") String page);

}
