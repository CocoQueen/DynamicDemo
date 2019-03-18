package com.example.wissdom.common.httpconnect.responsebean;

import java.io.Serializable;

/**
 * Create: 2018/6/28 12:38
 *
 * @author: Coco
 * Description:  网络请求回传 entity基类
 * Version: 1.0
 **/


@SuppressWarnings("unused")
public class BaseResponseEntity<T> implements Serializable {

    public int code;
    public String subCode;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LwxResponse{\n" +
                "\tsubCode=" + subCode + "\n" +
                "\tmsg='" + msg + "\'\n" +
                "\tdata=" + data + "\n" +
                '}';
    }

}
