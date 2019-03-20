package com.example.wissdom.mine;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author：Coco date：2019/3/15
 * version：1.0
 * description:HomeFragment
 */
public class MineFragment extends BaseFragment {
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
        return R.layout.mine_fragment;
    }

    @Override
    public void initViews() {

        tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivitySelf, "点击", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("数据" + i);
        }
        MineAdapter adapter = new MineAdapter(list);
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
