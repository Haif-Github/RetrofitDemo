package com.example.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haif on 2019/4/11.
 */

public class RetrofitServiceManager {

    private final Retrofit retrofit;

    private RetrofitServiceManager() {
        // 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS)    // 超时时间5s
                .readTimeout(5, TimeUnit.SECONDS)   // 读超时时间5s
                .writeTimeout(5, TimeUnit.SECONDS);     // 写操作

        // 添加公共参数拦截器（因为很多请求都有共同参数，这时可以使用公共参数拦截器）
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("userName", "haif")
                .addHeaderParams("userId", "123445")
                .build();
        builder.addInterceptor(commonInterceptor);

        // 创建Retrofit
        retrofit = new Retrofit.Builder()
                .client(builder.build())    // OkHttpClient配置
                .addConverterFactory(GsonConverterFactory.create())     // 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   // RxJava配置，接口返回的类型转换为被观察者，而不再是call
                .baseUrl("")    // 请求地址的公共部分
                .build();
    }

    private static RetrofitServiceManager retrofitServiceManager = new RetrofitServiceManager();

    public static RetrofitServiceManager getInstance() {
        return retrofitServiceManager;
    }

    // 获取对应的Service接口
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
