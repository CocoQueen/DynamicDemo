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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.example.wissdom.common.base.BaseActivity;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * Create: 2018/6/28 12:23
 *
 * @author: Coco
 * Description:  带进度对话框的网络model（二次封装）
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public abstract class AbstractDialogCallback<T> extends JsonCallback<T> {
    private Dialog dialog;

    private void initDialog(Context activity) {
        dialog = LoadingDialog.createLoadingDialog(activity);
    }

    public AbstractDialogCallback() {
        super();
        initDialog(BaseActivity.getActivity());
    }

    public AbstractDialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (dialog != null && !dialog.isShowing()) {
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        onFinish();
    }

}
