package com.jydp.obqr.adapter;


import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2017/7/24 0024.company keydom
 */

public class IHolder<VB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public VB binding;

    public IHolder(VB vb) {
        super(vb.getRoot());
        binding = vb;
    }
}
