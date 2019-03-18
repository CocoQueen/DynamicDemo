package com.example.wissdom.common.httpconnect.ritrofit;

/**
 * Create: 2018/12/21 16:17
 *
 * @author: Coco
 * Description: 响应结果
 * Version: 1.0
 **/
public interface Response<T> {

    /**
     * 成功响应
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 错误响应
     *
     * @param code
     * @param msg
     */
    void onError(int code, String msg);
}
