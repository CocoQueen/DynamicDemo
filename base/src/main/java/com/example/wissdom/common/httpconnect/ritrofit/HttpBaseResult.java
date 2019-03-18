package com.example.wissdom.common.httpconnect.ritrofit;

/**
 * Create: 2018/12/21 15:43
 *
 * @author: Coco
 * Description: 基类 Response
 * Version: 1.0
 **/
public class HttpBaseResult<T> {
    private int code;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
