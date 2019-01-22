package com.pgkk.data.net;

import javax.inject.Inject;

/**
 * Created by tanxueze on 2017/12/25.
 */

public class MeiziProjetRetrofit extends BaseProjetRetrofit {

    public static final String Base_Url = "http://gank.io/api/data/福利/";

    @Inject
    public MeiziProjetRetrofit() {
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
