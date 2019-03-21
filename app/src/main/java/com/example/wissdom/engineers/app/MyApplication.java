package com.example.wissdom.engineers.app;

import android.app.Application;
import android.util.Log;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:MyApplication
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //以下两行的打印可以理解为第三方SDK的初始化
        Log.e("application", "onCreate: a");
        Log.e("application", "onCreate: b");
    }
}
