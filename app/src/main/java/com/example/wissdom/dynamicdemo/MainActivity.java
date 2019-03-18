package com.example.wissdom.dynamicdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wissdom.common.base.BaseActivity;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:
 * first_commit:组件化第三方SDK版本控制
 * <p>
 * 使用Intent进行跳转通信（一般使用ActivityRouter与EventBus）
 * <p>
 * activity+fragment 搭建tab类型ui界面
 * <p>
 * 整体框架使用activity+fragment
 * 采用组件化开发此界面准备工作
 * 基础组件：数据库、图片加载库、base基类等等
 * 首页组件、我的组件、其他组件、业务组件
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    Fragment[] fragments = new Fragment[3];
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    HomeFragment homeFragment;
    MineFragment mineFragment;
    OtherFragment otherFragment;
    private Button btnHome;
    private Button btnMine;
    private Button btnOther;

    @Override
    public String initActionBar() {
        return null;
    }

    @Override
    public int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btnHome = findViewById(R.id.btnHome);
        btnMine = findViewById(R.id.btnMine);
        btnOther = findViewById(R.id.btnOther);

        initListenser();

        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        otherFragment = new OtherFragment();

        fragmentManager = getSupportFragmentManager();
        fragments = new Fragment[]{homeFragment, mineFragment, otherFragment};

        selectTab(0);
    }

    private void initListenser() {
        btnHome.setOnClickListener(this);
        btnMine.setOnClickListener(this);
        btnOther.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void selectTab(int i) {
        transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        //展示需要展示的tab
        if (fragments[i].isAdded()) {
            transaction.show(fragments[i]);
        } else {
            transaction.add(R.id.frame_layout, fragments[i]);
            transaction.show(fragments[i]);
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHome:
                selectTab(0);
                break;
            case R.id.btnMine:
                selectTab(1);
                break;
            case R.id.btnOther:
                selectTab(2);
                break;
            default:
                break;
        }
    }
}
