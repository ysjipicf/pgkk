package com.pgkk.data.net;

import javax.inject.Inject;

/**
 * Created by tanxueze on 2017/12/25.
 */

public class ReadProjetRetrofit extends BaseProjetRetrofit {

    public static final String Base_Url = "http://v3.wufazhuce.com:8000/api/";

    @Inject
    public ReadProjetRetrofit() {
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
