package com.example.wissdom.common.uitls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


import com.example.wissdom.common.base.BaseActivity;

import java.util.HashSet;


/**
 * Created by zwq on 2018/4/15.
 * Describe：
 * Email zwq_ay@163.com
 */

@SuppressWarnings("unused")
public class SharePUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String USER_SESSION_ID = "user_session_id";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_PASSWORD_MW = "userPasswordMW";
    public static final String USER_NICKNAME = "userNickName";
    public static final String USER_HEAD_FILE = "user_headfile";
    public static final String TOKEN = "token";
    public static String FILLNAME = "config";
    public static String MY_DATA = "TrineaAndroidCommon2";


    private static SharePUtil saveUtil;

    /**
     * 静态单例内部类
     **/
    public static SharePUtil getInstance() {
        return Builder.sharePUtil;
    }

    private static class Builder {
        private static SharePUtil sharePUtil = new SharePUtil();
    }


    @SuppressLint("CommitPrefEdits")
    private SharePUtil() {
        sp = BaseActivity.getActivity().getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public boolean clear() {
        editor.clear();
        return editor.commit();
    }

    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static boolean saveUser(Context context, String username) {
        SharedPreferences settings = context.getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit().clear();
        HashSet<String> set;
        set = (HashSet<String>) settings.getStringSet(FILLNAME, null);
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(username);
        return editor.putStringSet(FILLNAME, set).commit();
    }

    public static HashSet<String> getUsers(Context context) {
        SharedPreferences settings = context.getSharedPreferences(MY_DATA, Context.MODE_PRIVATE);
        return (HashSet<String>) settings.getStringSet(FILLNAME, null);
    }

}
