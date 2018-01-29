package com.ican.skeleton.net;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by twy on 2018/1/29.
 */

public class QueryParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        String method = originalRequest.method();
        Headers headers = originalRequest.headers();

        String loginToken = "token---------";
        HttpUrl.Builder newBuilder = originalRequest.url().newBuilder();
        if (!TextUtils.isEmpty(loginToken)) {
            newBuilder.addQueryParameter("rt", loginToken);
        }
        HttpUrl modifiedUrl = newBuilder.build();

       /* HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter("rt",StringUtil.getLoginToken())
                .build();*/
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
