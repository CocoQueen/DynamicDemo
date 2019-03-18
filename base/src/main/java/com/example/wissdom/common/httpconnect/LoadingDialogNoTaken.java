package com.example.wissdom.common.httpconnect;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wissdom.common.R;
import com.example.wissdom.common.uitls.SharePUtil;
import com.github.ybq.android.spinkit.SpinKitView;

/**
 * Create: 2018/9/6 14:25
 *
 * @author: Coco
 * Description:
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class LoadingDialogNoTaken {
    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @return
     */
    public static Dialog createLoadingDialog(Context context) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.loading_dialog_view_notaken, null);
        // 获取整个布局
        LinearLayout layout = (LinearLayout) view
                .findViewById(R.id.dialog_view);
        // 页面中的Img
        SpinKitView img = (SpinKitView) view.findViewById(R.id.spin_kit);
        // 创建自定义样式的Dialog
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        // 设置返回键无效
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return loadingDialog;
    }

    /**
     * 弹出 提示用户下线通知
     *
     * @param context
     * @return
     */
    public static PopupWindow createPopupWindow(Context context) {
        View popView = LayoutInflater.from(context).inflate(
                R.layout.popup_window_log_out, null);
        popView.setFocusable(true);
        popView.setFocusableInTouchMode(true);
        TextView sure = popView.findViewById(R.id.sure);
        PopupWindow popupWindow = new PopupWindow(popView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popView.setOnKeyListener((v, keyCode, event) -> keyCode == KeyEvent.KEYCODE_BACK);
        try {
            popupWindow.showAtLocation(popView, Gravity.BOTTOM | Gravity.LEFT, 0,
                    0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sure.setOnClickListener(view -> {
            popupWindow.dismiss();
            SharePUtil.getInstance().clear();
        });
        return popupWindow;
    }
}
