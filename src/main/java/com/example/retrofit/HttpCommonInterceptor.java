package com.example.retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haif on 2019/4/13.
 */

public class HttpCommonInterceptor implements Interceptor {

    private Map<String, String> headerParamsMap = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();

        // 获取请求来改造
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());

        // 添加公共参数
        if (headerParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : headerParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }

        // 新的请求
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {

        private final HttpCommonInterceptor httpCommonInterceptor;

        public Builder() {
            httpCommonInterceptor = new HttpCommonInterceptor();
        }

        public HttpCommonInterceptor build() {
            return httpCommonInterceptor;
        }

        public Builder addHeaderParams(String key, String value) {
            httpCommonInterceptor.headerParamsMap.put(key, value);
            return this;
        }

    }

}
