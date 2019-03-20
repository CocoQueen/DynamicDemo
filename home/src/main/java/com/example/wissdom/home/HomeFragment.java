package com.example.wissdom.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wissdom.common.base.BaseFragment;
import com.github.mzule.activityrouter.annotation.Router;
import com.github.mzule.activityrouter.router.Routers;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author：Coco date：2019/3/15
 * version：1.0
 * description:HomeFragment
 * 使用ActivityRouter和EventBus实现组件的通信和跳转
 * 注：ActivityRouter的详细使用github  eventbus用法类同
 */
public class HomeFragment extends BaseFragment {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R2.id.text)
    TextView tex;

    @Override
    public String initActionBar() {
        return null;
    }

    @Override
    protected int setRootView() {
        return R.layout.home_fragment_home;
    }

    @Override
    public void initViews() {

        tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivitySelf, "点击", Toast.LENGTH_SHORT).show();
                //通过包名具体跳转
//                startActivityForPackageName(mActivitySelf,"com.example.wissdom.modulea.MainActivityA");
                //通过activityRouter跳转
                Routers.open(mActivitySelf, "modulea://test");
                //清单文件中定义的scheme://跳转的目标类的router注解?将要传递的参数名=参数值&参数名=参数值&...
                //getIntent().getStringExtra("name")在目标类中通过此方法进行值的获取，此例为string类型参数
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("数据" + i);
        }
        HomeAdapter adapter = new HomeAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivitySelf, "lalal" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void init() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
