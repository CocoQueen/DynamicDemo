package com.example.wissdom.common.httpconnect.ritrofit;


import com.example.wissdom.common.uitls.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create: 2018/12/21 14:55
 *
 * @author: Coco
 * Description: ritrofit 构建
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class HttpHelper<T> {

    /**
     * 接口地址
     */
    private static final String BASE_API = "http://192.168.1.123:8350/";
//    private static final String BASE_API = "http://192.168.1.123:8350/snxy-business/";
    /**
     * 连接超时时长x秒
     **/
    private static final int CONNECT_TIME_OUT = 30;
    /**
     * 读数据超时时长x秒
     **/
    private static final int READ_TIME_OUT = 30;
    /**
     * 写数据接超时时长x秒
     **/
    private static final int WRITE_TIME_OUT = 30;


    public HttpHelper() {
    }

    private static class Builder {
        private static HttpHelper build = new HttpHelper();
    }

    public static HttpHelper build() {
        return Builder.build;
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor())
                .build();
    }

    public T retrofit(Class<T> tClass) {
        return new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create((Class<T>) tClass);
    }

    private static Interceptor getLogInterceptor() {
        okhttp3.logging.HttpLoggingInterceptor.Level level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
        okhttp3.logging.HttpLoggingInterceptor loggingInterceptor =
                new okhttp3.logging.HttpLoggingInterceptor(message ->
                        LogUtils.getLogInstanse()
                                .showLogInFo("back_message----------->" + message));
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

}