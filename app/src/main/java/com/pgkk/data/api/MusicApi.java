package com.pgkk.data.api;


import com.pgkk.data.model.Music;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface MusicApi {

    @GET("mgmagazinelist/r/10/page/{page}/sign=2230926e0bb334c908c9f7fabdaf42014e1afb31c17cd4d53b52fcd3bc34d501&api_key=08b1e567157582019f7fe639c841c42a&timestrap=1488600156")
    Observable<List<Music>> loadMusic(@Path("page") String page);

}
