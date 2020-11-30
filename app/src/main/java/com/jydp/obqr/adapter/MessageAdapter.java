package com.jydp.obqr.adapter;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.MessageEntuty;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class MessageAdapter extends BaseQuickAdapter<MessageEntuty.DataBean, BaseViewHolder> {

    private int mPosition;
    private Context context;

    public MessageAdapter(int layoutResId, @Nullable List<MessageEntuty.DataBean> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper,MessageEntuty.DataBean item) {


    if(item.getType() == 1){
        helper.setText(R.id.item_time,item.getTime() + "  " + context.getResources().getString(R.string.tab3_item_1));
    }else if(item.getType() == 2){
        helper.setText(R.id.item_time,item.getTime() + "  " + context.getResources().getString(R.string.tab3_item_2));
    }
    helper.setText(R.id.item_seat, item.getNumber());

    if(helper.getAdapterPosition() == mPosition) {
        helper.setBackgroundColor(R.id.message_ll, Color.parseColor("#50FD8084"));
    }else{
        if((helper.getAdapterPosition() + 1)%2 == 0) {
            helper.setBackgroundColor(R.id.message_ll, Color.parseColor("#10725133"));
        }else {
            helper.setBackgroundColor(R.id.message_ll, Color.parseColor("#FFFFFF"));
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
}
