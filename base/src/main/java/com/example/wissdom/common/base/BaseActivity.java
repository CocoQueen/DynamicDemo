package com.example.wissdom.common.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wissdom.common.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Create: 2018/11/19 15:31
 *
 * @author: Coco
 * Description:  baseActivity
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder mUnbinder;
    public static BaseActivity mActivity;
    private Toolbar mToolbar;
    private TextView mToolbarRight;
    private TextView mToolbarLeft;
    private BGABadgeImageView mToolbarMineIcnRight;
    private BGABadgeImageView mToolbarMineIcnLeft;
    public static LifecycleProvider provider;

    /**
     * 初始化title
     *
     * @return
     */
    public abstract String initActionBar();

    /**
     * 初始化根布局
     *
     * @return 0
     */
    public abstract int getRootView();

    /**
     * 初始化页面
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化基础数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 获取头部标题的TextView Right
     *
     * @return
     */
    public TextView getToolbarRight() {
        return mToolbarRight;
    }

    /**
     * 是否使用title
     *
     * @return
     */
    public boolean useTitleBar() {
        return true;
    }

    /**
     * 获取头部标题的TextView Left
     *
     * @return
     */
    public TextView getToolbarLeft() {
        return mToolbarLeft;
    }

    /**
     * 获取头部标题的BGABadgeImageView Right
     *
     * @return
     */
    public BGABadgeImageView getmToolbarMineIcnLeft() {
        return mToolbarMineIcnLeft;
    }

    /**
     * 获取头部标题mToolbarMineIcnRight
     *
     * @return
     */
    public BGABadgeImageView getToolbarMineIcnRight() {
        return mToolbarMineIcnRight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());
        provider = this;
        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        if (useTitleBar()) {
            mToolbar = findViewById(R.id.toolbar);
            initTitle();
        }
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != mToolbar && isShowBacking()) {
            showBack();
        }
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public void initTitle() {
        TextView mToolbarTitle = findViewById(R.id.base_title);
        mToolbarRight = findViewById(R.id.base_right);
        mToolbarLeft = findViewById(R.id.base_change);
        mToolbarMineIcnRight = findViewById(R.id.base_mine_right);
        mToolbarMineIcnLeft = findViewById(R.id.base_mine_left);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        if (null != mToolbarTitle) {
            mToolbarTitle.setText(initActionBar());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    /**
     * 快捷启动 activity 有回传
     *
     * @param activity
     * @param homeActivityClass
     * @param code
     */
    public void startActivityForResult(Activity activity, Class homeActivityClass, int code) {
        Intent intent = new Intent(activity.getApplicationContext(), homeActivityClass);
        activity.startActivityForResult(intent, code);
    }

    /**
     * 设置状态栏
     *
     * @param activity
     * @param color
     */
    public void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加statusView到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content))
                    .getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏的颜色值
     * @return 状态栏矩形条
     */
    private View createStatusView(Activity activity, int color) {
        // 获得状态栏的高度
        int resourceId = activity.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources()
                .getDimensionPixelSize(resourceId);
        // 绘制一个和状态栏一样高度的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        getToolbar().setNavigationIcon(R.mipmap.arrow);
        getToolbar().setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * 使状态栏透明
     * 适用于图片作为背景的界面，此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity
                    .findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 快捷跳转到另一个Activity
     *
     * @param activityClass
     * @param tag
     * @param bundle
     */
    public void goToActivity(Class activityClass, String tag, Bundle bundle) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null) {
            intent.putExtra(tag, bundle);
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
     * 弹出toast
     **/
    public void showToast(String msg) {
        this.runOnUiThread(() -> Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.getInstance().removeActivity(this);
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public void finishSelf() {
        this.finish();
    }
}
