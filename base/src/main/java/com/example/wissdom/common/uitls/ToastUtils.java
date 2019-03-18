package com.example.wissdom.common.uitls;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wissdom.common.R;
import com.example.wissdom.common.base.BaseActivity;


/**
 * Create: 2018/11/19 16:26
 *
 * @author: Coco
 * Description: 土司显示
 * Version: 2.0
 **/

@SuppressWarnings("unused")
public class ToastUtils {

    /**
     * 显示消息
     **/
    public static void show(String msg) {
        if (BaseActivity.getActivity() != null && !StringUtils.isEmptyString(msg)) {
            Toast t = Toast.makeText(BaseActivity.getActivity(), msg, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    /**
     * 显示消息
     **/
    public static void show(Context context, String msg) {
        if (context != null && !StringUtils.isEmptyString(msg)) {
            Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    /**
     * 中心显示消息
     **/
    public static void showInCenter(String msg) {
        if (BaseActivity.getActivity() != null && !StringUtils.isEmptyString(msg)) {
            Toast t = Toast.makeText(BaseActivity.getActivity(), msg, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    /**
     * 中心显示消息
     **/
    public static void showInCenter(Context context, String msg) {
        if (context != null && !StringUtils.isEmptyString(msg)) {
            Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    public void showToastShort(Context context, String mString, int id) {
        //TODO 自定义土司
        View layout = View.inflate(context, R.layout.toast_item, null);
        TextView text = layout.findViewById(R.id.tusi);
        text.setText(mString);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 60);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
