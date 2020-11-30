package com.jydp.obqr.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.NotbillrightEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class CheckOutAdapter extends BaseQuickAdapter<NotbillrightEntity.DataBean, BaseViewHolder> {

    private int mPosition;
    private int page;
    private int limit;

    public CheckOutAdapter(int layoutResId, @Nullable List<NotbillrightEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,NotbillrightEntity.DataBean item) {
        helper.setText(R.id.item_id,String.valueOf(helper.getAdapterPosition() + 1 + (page - 1) * limit))
                .setText(R.id.item_time,item.getOpened_at())
                .setText(R.id.item_seat,item.getSeat_number())
                .setText(R.id.item_people,item.getPeople())
                .setText(R.id.item_money,"¥" + item.getTotal_format());

        if(helper.getAdapterPosition() == mPosition) {
            helper.setBackgroundColor(R.id.item_ll, Color.parseColor("#50FD8084"));
        }else{
            if((helper.getAdapterPosition() + 1)%2 == 0) {
                helper.setBackgroundColor(R.id.item_ll, Color.parseColor("#10725133"));
            }else {
                helper.setBackgroundColor(R.id.item_ll, Color.parseColor("#FFFFFF"));
            }
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

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
