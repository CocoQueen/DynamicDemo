package com.example.wissdom.common.uitls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Create: 2018/6/1 14:15
 *
 * @author: Coco
 * Description:  网络工具类
 * {@link #getInstance()}  单例模式返回当前类对象
 * {@link #getNetworkType(Context)} 入参上下文对象，获取当前网络状态
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class NetWorkStateUtil {

    private static final int NETTYPE_WIFI = 0x01;
    private static final int NETTYPE_CMWAP = 0x02;
    private static final int NETTYPE_CMNET = 0x03;

    /** 静态单例内部类**/
    private NetWorkStateUtil() {
    }

    public static NetWorkStateUtil getInstance() {
        return NetWork2.t;
    }

    private static class NetWork2 {
        private static NetWorkStateUtil t = new NetWorkStateUtil();
    }

    /**
     * 获取当前网络类型
     *
     * @param context 上下文
     * @return 0：没有网络
     * 1：WIFI网络
     * 2：WAP网络
     * 3：NET网络
     */
    public int getNetworkType(Context context) {
        //TODO  获取网络类型
        int netType = 0;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return netType;
            }
            // 获取网络类型
            int nType = networkInfo.getType();
            if (nType == ConnectivityManager.TYPE_MOBILE) {
                String extraInfo = networkInfo.getExtraInfo();
                if (!"".equals(extraInfo)) {
                    if (extraInfo.toLowerCase().equals("cmnet")) {
                        netType = NETTYPE_CMNET;
                    } else {
                        netType = NETTYPE_CMWAP;
                    }
                }
            } else if (nType == ConnectivityManager.TYPE_WIFI) {
                netType = NETTYPE_WIFI;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return netType;
    }
}
