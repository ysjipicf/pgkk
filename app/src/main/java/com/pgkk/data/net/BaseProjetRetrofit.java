package com.pgkk.data.net;

import android.content.Context;

import com.pgkk.common.utils.DeviceInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tanxueze on 2017/12/25.
 */

public class BaseProjetRetrofit extends BaseRetrofit {

    public static final String Base_Url = "http://v.juhe.cn/toutiao/";

    @Inject
    public BaseProjetRetrofit() {
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

    @Override
    public OkHttpClient getHttpClient(Context context) {
        return new AuthHttpClient(context).get();
    }

    //授权
    private class AuthHttpClient extends BaseOkHttpClient {
        public Context context;

        public AuthHttpClient(Context context) {
            this.context = context;
        }

        @Override
        public OkHttpClient.Builder custo(OkHttpClient.Builder builder) {

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("X-Requested-With", "andriod");

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            builder.addInterceptor(new CacheInterceptor(context))
                    .cache(new Cache(context.getExternalFilesDir("temp"), 50 * 1024 * 1024));

            builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

            return builder;
        }
    }


    /**
     * 网络缓存
     */
    public class CacheInterceptor implements Interceptor {
        public Context context;

        public CacheInterceptor(Context context) {
            this.context = context;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!DeviceInfo.isNetworkConnected(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (DeviceInfo.isNetworkConnected(context)) {
                int maxAge = 60 * 30;
                // 有网络时, 缓存, 最大保存时长为30分钟
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    }
}
