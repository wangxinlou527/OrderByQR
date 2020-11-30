package com.jydp.obqr.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.OrderItemEntity;
import com.jydp.obqr.entity.OrderMessageEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class CheckOutDetailAdapter extends BaseQuickAdapter<OrderItemEntity.OrderSnapshotsBean, BaseViewHolder> {



    public CheckOutDetailAdapter(int layoutResId, @Nullable List<OrderItemEntity.OrderSnapshotsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,OrderItemEntity.OrderSnapshotsBean item) {


        helper.setText(R.id.item_id,String.valueOf(helper.getAdapterPosition() + 1) )
                .setText(R.id.item_name,item.getMenu_name())
                .setText(R.id.item_count,item.getCount())
                .setText(R.id.item_money,item.getMenu_price_format())
                .setText(R.id.item_attribute,item.getAttribute_name());
//        helper.addOnClickListener(R.id.main_grid_buy)   //给图标添加 点击事件
//                .addOnClickListener(R.id.main_grid_icon).
//                setText(R.id.main_grid_price,item.getPrice()+"").
//                setText(R.id.main_grid_old_price,"¥"+item.getPrice_ori()+"").
//                setText(R.id.main_grid_title,item.getDish().getName()+"")  ;
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setAntiAlias(true);
//        Glide.with(MaoYesApplication.getContext()).load(item.getDish().getImage_url()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.main_grid_icon));
    }

}
