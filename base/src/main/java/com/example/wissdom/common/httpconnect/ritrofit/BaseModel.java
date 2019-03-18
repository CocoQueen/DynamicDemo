package com.example.wissdom.common.httpconnect.ritrofit;


import android.annotation.SuppressLint;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * Create: 2018/12/21 16:46
 *
 * @author: Coco
 * Description: 基类BaseModel
 * Version: 1.0
 **/
public abstract class BaseModel<T> {
    private LifecycleProvider<ActivityEvent> provider;

    public BaseModel(LifecycleProvider<ActivityEvent> provider) {
        this.provider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return provider;
    }
    @SuppressLint("CheckResult")
    public void connetModel(Observable<T> observable,ProgressSubscriber<T> progressSubscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                // onDestroy取消订阅
                .compose(getProvider().<T>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(progressSubscriber);
    }
}
