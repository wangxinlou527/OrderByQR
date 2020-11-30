package com.jydp.obqr.adapter;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class CommodityTabAdapter extends BaseQuickAdapter<DifferentEntity.DataBean, BaseViewHolder> {

    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public CommodityTabAdapter(int layoutResId, @Nullable List<DifferentEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,DifferentEntity.DataBean item) {

        helper.setText(R.id.tab_tv,item.getOrder_category_translate().getName());

        if(helper.getAdapterPosition() == mPosition){

            helper.setImageResource(R.id.tab_img_1,R.mipmap.tab_3)
                    .setImageResource(R.id.tab_img_2,R.mipmap.tab_4)
                    .setBackgroundColor(R.id.tab_tv, Color.parseColor("#715133"));

        }else{
            helper.setImageResource(R.id.tab_img_1,R.mipmap.tab_1)
                    .setImageResource(R.id.tab_img_2,R.mipmap.tab_2)
                    .setBackgroundColor(R.id.tab_tv, Color.parseColor("#E9C7A1"));

        }
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
