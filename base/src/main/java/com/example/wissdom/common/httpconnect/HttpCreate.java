package com.example.wissdom.common.httpconnect;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.wissdom.common.uitls.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create: 2018/12/3 18:06
 *
 * @author: Coco
 * Description: Okhttp初始化
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class HttpCreate {

    public static void initOkGo(Application context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
//        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
//        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志*/
        // builder.addInterceptor(new RequestLoggerInterceptor());//请求拦截器
        //  builder.addInterceptor(new ResponseLogInterceptor());//响应拦截器

        //超时时间设置，默认60秒 OkGo.DEFAULT_MILLISECONDS
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(context)));
        //必须调用初始化
        OkGo.getInstance().init(context)
                //建议设置OkHttpClient，不设置会使用默认的
                .setOkHttpClient(builder.build())
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheMode(CacheMode.NO_CACHE)
                //全局统一缓存时间，默认永不过期，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(0);
    }

    static class RequestLoggerInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            LogUtils.getLogInstanse().showLogInFo("request_url     =  : " + request.url());
            LogUtils.getLogInstanse().showLogInFo("request_method  =  : " + request.method());
            LogUtils.getLogInstanse().showLogInFo("request_headers =  : " + request.headers());
            LogUtils.getLogInstanse().showLogInFo("request_body    =  : " + request.body());
            return chain.proceed(request);
        }
    }

    static class ResponseLogInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            return chain.proceed(request);
        }
    }

}
