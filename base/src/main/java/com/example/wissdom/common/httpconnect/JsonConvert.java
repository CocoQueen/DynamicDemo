/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wissdom.common.httpconnect;

import com.example.wissdom.common.httpconnect.responsebean.BaseResponseEntity;
import com.example.wissdom.common.httpconnect.responsebean.SimpleResponse;
import com.example.wissdom.common.uitls.LogUtils;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.cache.policy.BaseCachePolicy;
import com.lzy.okgo.convert.Converter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Create: 2018/6/28 12:35
 *
 * @author: Coco
 * Description: 解析类
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class JsonConvert<T> implements Converter<T> {

    private Type type;
    private Class<T> clazz;

    public JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }
        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    /**
     * 解析json数据
     *
     * @param response
     * @param rawType
     * @return
     * @throws Exception
     */
    private T parseClass(Response response, Class<?> rawType) throws Exception {
        if (rawType == null) {
            return null;
        }
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType == String.class) {
            //noinspection unchecked
            return (T) body.string();
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(body.string());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(body.string());
        } else {
            //解析成 T 对象最后返回该对象
            T t = Convert.fromJson(jsonReader, rawType);
            response.close();
            return t;
        }
    }

    private T parseType(Response response, Type type) {
        if (type == null) {
            return null;
        }
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        JsonReader jsonReader = new JsonReader(body.charStream());
        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        T t = Convert.fromJson(jsonReader, type);
        response.close();
        return t;
    }

    /**
     * 解析成功调用onSuccess 反之,调用onError方法 具体看@link源码 *  {@link BaseCachePolicy#requestNetworkAsync()}
     *
     * @param response
     * @param type
     * @return
     * @throws Exception
     */
    private T parseParameterizedType(Response response, ParameterizedType type) throws Exception {
        if (type == null) {
            return null;
        }
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        assert response.body() != null;
        String jsonReader = readerToStr(Objects.requireNonNull(response.body()).charStream());
        LogUtils.getLogInstanse().showLogInFo("---getJson=" + jsonReader);
        // 泛型的实际类型
        Type rawType = type.getRawType();
        // 泛型的参数
        Type typeArgument = type.getActualTypeArguments()[0];
        if (rawType != BaseResponseEntity.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            T t = Convert.fromJson(jsonReader, type);
            response.close();
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<SimpleResponse<Void>>(this)
                JSONObject json = new JSONObject(jsonReader);
                int code = json.getInt("code");
                if (0 == code) {
                    throw new IllegalStateException(json.getString("msg"));
                }
                SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                return (T) simpleResponse.toParseResponse();
            } else {
                //泛型格式如下:new JsonCallback<BaseResponseEntity<内层JavaBean>>(this)
                JSONObject json = new JSONObject(jsonReader);
                int codes = json.getInt("code");
                if (0 != codes) {
                    //与后台约定0是成功的标志
                    throw new IllegalStateException(json.getString("msg"));
                }
                BaseResponseEntity baseResponseEntity = null;
                try {
                    baseResponseEntity = Convert.fromJson(jsonReader, type);
                    return (T) baseResponseEntity;
                } catch (Exception e) {
                    throw new IllegalStateException(baseResponseEntity.msg);
                }
            }
        }
    }

    private String readerToStr(Reader reader) {
        BufferedReader r = new BufferedReader(reader);
        StringBuilder b = new StringBuilder();
        String line = "";
        try {
            while ((line = r.readLine()) != null) {
                b.append(line);
                b.append("\r\n");
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b.toString();
    }
}
