package com.jydp.obqr.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.NowSeatEntity;
import com.jydp.obqr.entity.OrderMessageEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class OrderMessageAdapter extends BaseQuickAdapter<OrderMessageEntity.DataBean, BaseViewHolder> {

    private int mPosition;


    public OrderMessageAdapter(int layoutResId, @Nullable List<OrderMessageEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,OrderMessageEntity.DataBean item) {

        if(helper.getAdapterPosition() == mPosition) {
            helper.setTextColor(R.id.item_id,Color.parseColor("#B94139"))
                    .setTextColor(R.id.item_name,Color.parseColor("#B94139"))
                    .setTextColor(R.id.item_count,Color.parseColor("#B94139"))
                    .setTextColor(R.id.item_money,Color.parseColor("#B94139"));
        }else{
            helper.setTextColor(R.id.item_id,Color.parseColor("#333333"))
                    .setTextColor(R.id.item_name,Color.parseColor("#333333"))
                    .setTextColor(R.id.item_count,Color.parseColor("#333333"))
                    .setTextColor(R.id.item_money,Color.parseColor("#333333"));
        }


        helper.setText(R.id.item_id,String.valueOf(helper.getAdapterPosition() + 1) )
                .setText(R.id.item_name,item.getName())
                .setText(R.id.item_count,item.getCount())
                .setText(R.id.item_money,item.getPrice_format());

        if(item.getAttribute() != null)
            helper.setText(R.id.item_attribute,item.getAttribute() + "");
//        helper.addOnClickListener(R.id.main_grid_buy)   //给图标添加 点击事件
//                .addOnClickListener(R.id.main_grid_icon).
//                setText(R.id.main_grid_price,item.getPrice()+"").
//                setText(R.id.main_grid_old_price,"¥"+item.getPrice_ori()+"").
//                setText(R.id.main_grid_title,item.getDish().getName()+"")  ;
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setAntiAlias(true);
//        Glide.with(MaoYesApplication.getContext()).load(item.getDish().getImage_url()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.main_grid_icon));
    }

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
