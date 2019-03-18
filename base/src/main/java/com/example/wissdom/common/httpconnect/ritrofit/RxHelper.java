package com.example.wissdom.common.httpconnect.ritrofit;

import com.google.gson.Gson;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Create: 2018/12/21 15:45
 *
 * @author: Coco
 * Description: 异常处理
 * Version: 1.0
 **/
public class RxHelper {


    private RxHelper() {
    }

    private static class Builder {
        private static RxHelper t = new RxHelper();
    }

    public static RxHelper build() {
        return Builder.t;
    }

    public String checkThrowble(Throwable t) {
        String msg = "";
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            switch (httpException.code()) {
                case 404:
                    msg = "连接地址异常，请稍后重试";
                    break;
                default:
                    msg = "操作异常，请稍后重试";
                    break;
            }
            return msg;
        }
        return msg;
    }

    public int getThrowbleCode(Throwable t) {
        int code = 202;
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            return httpException.code();
        }
        return code;
    }
}
