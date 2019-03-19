package com.example.wissdom.home;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.wissdom.common.base.BaseFragmentActivity;

/**
 *
 * @author：Coco
 * date：2019/3/19
 * version：1.0
 * description:HomeActivity.java
 *
 */
public class HomeActivity extends BaseFragmentActivity {


    @Override
    public String initActionBar() {
        return null;
    }

    @Override
    public int getRootView() {
        return R.layout.home_activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,new HomeFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
