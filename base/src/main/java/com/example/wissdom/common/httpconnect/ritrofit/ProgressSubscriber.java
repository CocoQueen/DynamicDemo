package com.example.wissdom.common.httpconnect.ritrofit;

import android.app.Activity;
import android.app.Dialog;

import com.example.wissdom.common.base.BaseActivity;
import com.example.wissdom.common.httpconnect.LoadingDialog;
import com.example.wissdom.common.uitls.NetWorkStateUtil;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create: 2018/12/21 16:21
 *
 * @author: Coco
 * Description: modelc层结果回调
 * Version: 1.0
 **/
public class ProgressSubscriber<T> implements Observer<T> {
    private Dialog loadingDialog;
    private Response<T> response;
    private boolean useLoading;

    public ProgressSubscriber(Response<T> response, boolean useLoading) {
        this.response = response;
        this.useLoading = useLoading;
    }    public ProgressSubscriber(Response<T> response, boolean useLoading, Activity activity) {
        this.response = response;
        this.useLoading = useLoading;
    }

    public ProgressSubscriber(Response<T> response) {
        this.response = response;
        this.useLoading = false;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (useLoading) {
            loadingDialog = LoadingDialog.createLoadingDialog(BaseActivity.getActivity());
            loadingDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        finishDialog();
        response.onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        finishDialog();
        int networkType = NetWorkStateUtil.getInstance().getNetworkType(BaseActivity.getActivity());
        if (0 == networkType) {
            response.onError(202, "网络异常，请检查您的网络状态");
            return;
        }
        response.onError(RxHelper.build().getThrowbleCode(t), RxHelper.build().checkThrowble(t));
    }

    @Override
    public void onComplete() {

    }

    private void finishDialog() {
        if (null != loadingDialog) {
            loadingDialog.dismiss();
        }
    }
}
