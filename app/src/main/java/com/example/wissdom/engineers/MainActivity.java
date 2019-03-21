package com.example.wissdom.engineers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wissdom.common.base.BaseFragment;
import com.example.wissdom.common.base.BaseFragmentActivity;
import com.example.wissdom.home.HomeFragment;
import com.example.wissdom.mine.DataFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

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
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    FragmentTransaction transaction;
    HomeFragment homeFragment;
    DataFragment dataFragment;
    DeployFragment deployFragment;
    WorkFragment workFragment;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.homeImage)
    ImageView homeImage;
    @BindView(R.id.homeTv)
    TextView homeTv;
    @BindView(R.id.homeRl)
    RelativeLayout homeRl;
    @BindView(R.id.dataImage)
    ImageView dataImage;
    @BindView(R.id.dataTv)
    TextView dataTv;
    @BindView(R.id.dataRl)
    RelativeLayout dataRl;
    @BindView(R.id.deployImage)
    ImageView deployImage;
    @BindView(R.id.deployTv)
    TextView deployTv;
    @BindView(R.id.deployRl)
    RelativeLayout deployRl;
    @BindView(R.id.workImage)
    ImageView workImage;
    @BindView(R.id.workTv)
    TextView workTv;
    @BindView(R.id.workRl)
    RelativeLayout workRl;
    @BindView(R.id.bottomContainer)
    LinearLayout bottomContainer;

    private BaseFragment[] fragments = {homeFragment, dataFragment, deployFragment, workFragment};

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

        if (savedInstanceState==null){
            homeFragment = new HomeFragment();
            dataFragment = new DataFragment();
            deployFragment = new DeployFragment();
            workFragment = new WorkFragment();
            fragments[0] = homeFragment;
            fragments[1] = dataFragment;
            fragments[2] = deployFragment;
            fragments[3] = workFragment;
            initListenser();
            selectTab();
        }else {
            fragments[0] = (HomeFragment) mFragmentManager.findFragmentByTag(HomeFragment.class.getName());
            fragments[1] = (DataFragment) mFragmentManager.findFragmentByTag(DataFragment.class.getName());
            fragments[2] = (DeployFragment) mFragmentManager.findFragmentByTag(DeployFragment.class.getName());
            fragments[3] = (WorkFragment) mFragmentManager.findFragmentByTag(WorkFragment.class.getName());
        }

    }

    private void initListenser() {
        homeRl.setOnClickListener(this);
        dataRl.setOnClickListener(this);
        deployRl.setOnClickListener(this);
        workRl.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void selectTab() {
        homeTv.setTextColor(getResources().getColor(R.color.blue_1296db));
        homeImage.setBackgroundResource(R.mipmap.home_light);
        if (homeFragment.isAdded()) {
            showFrag(homeFragment);
        } else {
            addFrag(R.id.frame_layout, homeFragment);
        }
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.length; i++) {
            transaction.hide(fragments[i]);
        }
    }

    @Override
    public void onClick(View v) {
        BaseFragment desFragment;
        //切换目标fragment
        resetTabState();
        switch (v.getId()) {
            case R.id.homeRl:
                desFragment = fragments[0];
                homeTv.setTextColor(getResources().getColor(R.color.blue_1296db));
                homeImage.setBackgroundResource(R.mipmap.home_light);
                break;
            case R.id.dataRl:
                desFragment = fragments[1];
                dataTv.setTextColor(getResources().getColor(R.color.blue_1296db));
                dataImage.setBackgroundResource(R.mipmap.data_light);
                break;
            case R.id.deployRl:
                desFragment = fragments[2];
                deployTv.setTextColor(getResources().getColor(R.color.blue_1296db));
                deployImage.setBackgroundResource(R.mipmap.set_light);
                break;
            case R.id.workRl:
                desFragment = fragments[3];
                workTv.setTextColor(getResources().getColor(R.color.blue_1296db));
                workImage.setBackgroundResource(R.mipmap.et_more_light);
                break;
            default:
                desFragment = fragments [0];
                homeTv.setTextColor(getResources().getColor(R.color.blue_1296db));
                homeImage.setBackgroundResource(R.mipmap.home_light);
                break;
        }
        changeFragShow(desFragment);
    }
    private void changeFragShow(BaseFragment desFragment) {
        if (null == desFragment) {
            Logger.e("--desFragment----" + desFragment);
            return;
        }
        if (desFragment.isAdded()) {
            showFrag(desFragment);
        } else {
            addFrag(R.id.frame_layout, desFragment);
        }
        for (BaseFragment baseFragment : fragments) {
            if (baseFragment == null) {
                continue;
            }
            if (baseFragment.isAdded()) {
                if (desFragment != baseFragment) {
                    hideFrag(baseFragment);
                }
            }
        }
    }
    private void resetTabState() {
        homeTv.setTextColor(getResources().getColor(R.color.black));
        dataTv.setTextColor(getResources().getColor(R.color.black));
        deployTv.setTextColor(getResources().getColor(R.color.black));
        workTv.setTextColor(getResources().getColor(R.color.black));
        homeImage.setBackgroundResource(R.mipmap.home);
        dataImage.setBackgroundResource(R.mipmap.data);
        deployImage.setBackgroundResource(R.mipmap.set);
        workImage.setBackgroundResource(R.mipmap.et_more);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
