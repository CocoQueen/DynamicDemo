package com.example.wissdom.mine;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.wissdom.common.base.BaseFragmentActivity;

/**
 * @author：Coco date：2019/3/19
 * version：1.0
 * description:MineActivity.java
 */
public class MineActivity extends BaseFragmentActivity {

    @Override
    public String initActionBar() {
        return null;
    }

    @Override
    public int getRootView() {
        return R.layout.mine_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new DataFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
