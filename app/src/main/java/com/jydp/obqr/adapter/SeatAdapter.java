package com.jydp.obqr.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import com.jydp.obqr.BR;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.databinding.ItemShouyinZhaodanBinding;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.fragment.Tab1Right;

import io.reactivex.annotations.NonNull;

/*
2020/10/10 11:11 星期六
作用 ：
*/
public class SeatAdapter extends IAdapter<SeatListEntity.DataBean, ItemShouyinZhaodanBinding> {

    Tab1Right tab1Right;

    public SeatAdapter(Tab1Right tab1Right) {
        this.tab1Right = tab1Right;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shouyin_zhaodan;
    }


    @Override
    protected int getDataBRId(int itemViewType) {
        return BR.item;
    }

    @Override
    protected void convertListener(@NonNull ItemShouyinZhaodanBinding Itembinding) {
        Itembinding.setItemNavigator((ItemNavigator)tab1Right);
    }

    @Override
    public void onBindViewHolder(IHolder<ItemShouyinZhaodanBinding> holder, @SuppressLint("RecyclerView") int position) {
        super.onBindViewHolder(holder, position);
    }
}
