package com.example.wissdom.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.wissdom.base.R;
import com.trello.rxlifecycle2.LifecycleProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Create: 2018/6/28 10:03
 *
 * @author: Coco
 * Description: 基类 fragment
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public abstract class BaseFragment extends Fragment {

    public String mTag;
    public BaseActivity mActivitySelf;
    public BaseFragment mFragSelf;
    public View mRootView;
    public FragmentManager mFragmentManager;
    public LayoutInflater mLayoutInflater;
    private BGABadgeImageView mToolbarMineIcnRight;
    private BGABadgeImageView mToolbarMineIcnLeft;
    private TextView mToolbarRight;
    private TextView mToolbarLeft;
    private Unbinder bind;
    public LifecycleProvider provider;
    public BaseFragment() {
        mTag = this.getClass().getName();
    }

    /**
     * 设置标题
     *
     * @return
     */
    public abstract String initActionBar();

    /**
     * 设置页面根布局
     *
     * @return 页面根布局索引---R.layout.xx
     */
    protected abstract int setRootView();

    /**
     * 初始化页面控件
     */
    public abstract void initViews();

    /**
     * 初始化页面数据
     */
    public abstract void initData();

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 是否使用title
     *
     * @return
     */
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragSelf = this;
        mActivitySelf = (BaseActivity) this.getActivity();
        provider=mActivitySelf.provider;
        mFragmentManager = this.getChildFragmentManager();
        mLayoutInflater = inflater;
        mRootView = mLayoutInflater.inflate(setRootView(), container, false);
        bind = ButterKnife.bind(this, mRootView);
        if (useTitleBar()){
            Toolbar mToolbar = mRootView.findViewById(R.id.toolbar);
            TextView mToolbarTitle = mRootView.findViewById(R.id.base_title);
            mToolbarRight = mRootView.findViewById(R.id.base_right);
            mToolbarLeft = mRootView.findViewById(R.id.base_change);
            mToolbarMineIcnRight = mRootView.findViewById(R.id.base_mine_right);
            mToolbarMineIcnLeft = mRootView.findViewById(R.id.base_mine_left);
            if (null != mToolbarTitle) {
                mToolbarTitle.setText(initActionBar());
            }
            //判断是否有Toolbar,并默认显示返回按钮
            if (null != getToolbar() && isShowBacking()) {
                showBack();
            }
        }
        initViews();
        initData();
        init();
        return mRootView;
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) mRootView.findViewById(R.id.toolbar);
    }

    /**
     * 获取头部标题的TextView Right
     *
     * @return mToolbarRight
     */
    public TextView getmToolbarRight() {
        return mToolbarRight;
    }

    /**
     * 获取头部标题的TextView Left
     *
     * @return mToolbarLeft
     */
    public TextView getmToolbarLeft() {
        return mToolbarLeft;
    }

    /**
     * title 右icon
     *
     * @return mToolbarMineIcn_right
     */
    public BGABadgeImageView getToolbarMineIcnRight() {
        return mToolbarMineIcnRight;
    }

    /**
     * title 左icon
     *
     * @return mToolbarMineIcn_left
     */
    public BGABadgeImageView getToolbarMineIcnLeft() {
        return mToolbarMineIcnLeft;
    }

    /**
     * 快捷弹出一个短吐司
     *
     * @param text 吐司内容
     */
    public void showToastShort(String text) {
        mActivitySelf.showToast(text);
    }

    /**
     * 快捷弹出一个长吐司
     *
     * @param text 吐司内容
     */
    public void showToastLong(String text) {
        mActivitySelf.showToast(text);
    }

    /**
     * 快捷跳转到另一个Activity，可传递数据
     *
     * @param activityClass 目标
     * @param bundle        数据
     * @param taget         标识
     */
    public void goToActivity(Class activityClass, String taget, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, activityClass);
        if (bundle != null) {
            intent.putExtra(taget, bundle);
        }
        startActivity(intent);
    }

    /**
     * 快捷跳转到另一个Activity
     *
     * @param activityClass 目标
     */
    public void goToActivity(Class activityClass) {
        goToActivity(activityClass, "", null);
    }

    /**
     * 快捷跳转到另一个Service，可传递数据
     *
     * @param serviceClass 目标
     * @param bundle       数据
     */
    public void goToService(Class serviceClass, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, serviceClass);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        mActivitySelf.startService(intent);
    }

    /**
     * 快捷跳转到另一个Service
     *
     * @param serviceClass 目标
     */
    public void goToService(Class serviceClass) {
        goToService(serviceClass, null);
    }

    /**
     * 快捷跳转到另一个BrodCastReceiver，可传递数据
     *
     * @param receiverClass 目标
     * @param bundle        数据
     */
    public void goToReceiver(Class receiverClass, Bundle bundle) {
        Intent intent = new Intent(mActivitySelf, receiverClass);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        mActivitySelf.sendBroadcast(intent);
    }

    /**
     * 快捷跳转到另一个BrodCastReceiver
     *
     * @param receiverClass 目标
     */
    public void goToReceiver(Class receiverClass) {
        goToReceiver(receiverClass, null);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        getToolbar().setNavigationIcon(R.mipmap.back);
        getToolbar().setNavigationOnClickListener(v ->
                mActivitySelf.onBackPressed());
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
     * 移除 fragment
     *
     * @param frag
     */
    public void removeFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(frag);
        fragmentTransaction.commit();
    }

    /**
     * 替换fragment
     *
     * @param containerID
     * @param frag
     */
    public void replaceFrag(int containerID, BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, frag, frag.mTag);
        fragmentTransaction.commit();
    }

    /**
     * 影藏 fragment
     *
     * @param frag
     */
    public void hideFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.hide(frag);
        fragmentTransaction.commit();
    }

    /**
     * fragment
     *
     * @param frag
     */
    public void showFrag(BaseFragment frag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.show(frag);
        fragmentTransaction.commit();
    }
    /**
     * 通过包名跳转
     * @param context 上下文
     * @param packageName 包名
     */
    public void startActivityForPackageName(Context context, String packageName){
        try {
            Class clazz =Class.forName(packageName);
            Intent intent =new Intent(context,clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 退出当前
     */
    public void killSelf() {
        removeFrag(this);
    }
}
