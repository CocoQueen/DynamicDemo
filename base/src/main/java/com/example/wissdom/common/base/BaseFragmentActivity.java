package com.example.wissdom.common.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Create: 2018/11/19 15:52
 *
 * @author: Coco
 * Description: 带控制fragment 的BaseActivity
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public abstract class BaseFragmentActivity extends BaseActivity {

    public FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        super.onCreate(savedInstanceState);
    }

    /**
     * @param containerID 往哪儿放
     * @param frag        放谁
     */
    public void addFrag(int containerID, BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(containerID, frag, frag.mTag);
        fragmentTransaction.commit();
    }

    /**
     * 移除fragment
     **/
    public void removeFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(frag);
        fragmentTransaction.commit();
    }

    /**
     * 替换fragment
     **/
    public void replaceFrag(int containerID, BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, frag, frag.mTag);
        fragmentTransaction.commit();
    }

    /**
     * 隐藏framgent
     **/
    public void hideFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.hide(frag);
        fragmentTransaction.commit();
    }

    /**
     * 显示fragment
     **/
    public void showFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.show(frag);
        fragmentTransaction.commit();
    }

    /**
     * 推出当前
     */
    public void killSelf() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mFragmentManager.popBackStack();
    }
}
