package com.example.wissdom.mine;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author：Coco date：2019/3/19
 * version：1.0
 * description:HomeAdapter
 */
public class MineAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineAdapter(@Nullable List<String> data) {
        super(R.layout.mine_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.textview, item);
    }
}
