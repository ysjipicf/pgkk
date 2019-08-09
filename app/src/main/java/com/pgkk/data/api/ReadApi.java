package com.pgkk.data.api;


import com.pgkk.data.model.Read;
import com.pgkk.data.model.ReadDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tanxueze on 2017/12/7.
 */

public interface ReadApi {

    @GET("channel/reading/more/{id}?platform=ios&uuid=850717CA-9EF7-429E-92B3-6B88B479A476&user_id=&version=v4.2.1")
    Observable<Read> getReadList(@Path("id") int id);


    @GET("essay/{id}?platform=ios&uuid=850717CA-9EF7-429E-92B3-6B88B479A476&user_id=&version=v4.2.1&source=channel_reading")
    Observable<ReadDetail> getReadDetail(@Path("id") int id, @Query("source_id") int sourceId);

}
