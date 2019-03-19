package com.example.wissdom.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wissdom.common.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author：Coco date：2019/3/15
 * version：1.0
 * description:HomeFragment
 */
public class HomeFragment extends BaseFragment {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        for (int i = 1; i <=100; i++) {
            list.add("数据"+i);
        }
        recyclerView.setAdapter(new HomeAdapter(list));

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
