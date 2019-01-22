package com.pgkk.data.net;

import javax.inject.Inject;

/**
 * Created by tanxueze on 2017/12/25.
 */

public class MusicProjetRetrofit extends BaseProjetRetrofit {

    public static final String Base_Url = "http://www.wawa.fm:9090/wawa/api.php/magazine/";

    @Inject
    public MusicProjetRetrofit() {
    }

    @Override
    public ApiEndpoint getApiEndpoint() {
        return new ApiEndpoint() {
            @Override
            public String getEndpoint() {
                return Base_Url;
            }
        };
    }
}
