package com.pgkk.data.net;

import android.content.Context;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tanxueze on 2017/12/25.
 */

public class VideoProjetRetrofit extends BaseRetrofit {

    public static final String Base_Url = "https://baobab.kaiyanapp.com/api/v4/";

    @Inject
    public VideoProjetRetrofit() {
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
        return new VideoProjetRetrofit.AuthHttpClient(context).get();
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

            //https
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };
            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;

            builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
//                    .sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(DO_NOT_VERIFY);

            return builder;
        }
    }
}
