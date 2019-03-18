package com.example.wissdom.common.uitls;

import android.util.Log;

/**
 * Create: 2018/4/23 17:01
 *
 * @author: Coco
 * <p>
 * ****************************************************
 * Description:  log工具类
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class LogUtils {

    private boolean useLog = true;

    private LogUtils() {
    }

    private final static class Logs {
        private final static LogUtils logUtils = new LogUtils();
    }

    public static LogUtils getLogInstanse() {
        return Logs.logUtils;
    }

    public void showLogInFo(String content) {
        if (useLog) {
            String IF = "log_info";
            Log.i(IF, content);
        }
    }

    public void showLogInFo(String key, String content) {
        if (useLog) {
            Log.i(key, content);
        }
    }

    public void showLogErro(String content) {
        if (useLog) {
            String ER = "log_error";
            Log.e(ER, content);
        }
    }
}
