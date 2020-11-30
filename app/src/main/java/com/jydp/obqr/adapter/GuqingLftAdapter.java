package com.jydp.obqr.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.GuQingEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class GuqingLftAdapter extends BaseQuickAdapter<GuQingEntity.DataBean, BaseViewHolder> {

    private Context context;

    public GuqingLftAdapter(int layoutResId, @Nullable List<GuQingEntity.DataBean> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper,GuQingEntity.DataBean item) {


        helper.setText(R.id.item_name,item.getOrder_menu_translate().getName())
                .setText(R.id.item_id,(helper.getAdapterPosition() + 1) + "");

        if(item.getStock() > 0) {
            helper.setText(R.id.item_count,item.getStock() + "");
        }else{
            helper.setText(R.id.item_count,context.getResources().getString(R.string.tab4_item));
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
