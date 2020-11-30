package com.jydp.obqr.adapter;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.ReserveListEntity;
import com.jydp.obqr.util.StrUtil;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class ResvrveAdapter extends BaseQuickAdapter<ReserveListEntity.DataBean, BaseViewHolder> {

    private int mPosition;

    public ResvrveAdapter(int layoutResId, @Nullable List<ReserveListEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,ReserveListEntity.DataBean item) {

        helper.setText(R.id.item_people_tv,item.getPeople())
                .setText(R.id.item_time_1_tv,StrUtil.dateFormat1(item.getBooked_at()))
                .setText(R.id.item_time_2_tv,StrUtil.dateFormat2(item.getBooked_at()))
                .setText(R.id.item_number_tv,item.getSeat().getNumber());



        if(helper.getAdapterPosition() == mPosition) {
            helper.getView(R.id.item_check_img).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_check_img).setVisibility(View.GONE);
        }

        helper.getView(R.id.item_cl).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setAlpha(0.4f);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        v.setAlpha(1f);

                    case MotionEvent.ACTION_UP:
                        v.setAlpha(1f);
                        break;
                }
                return false;
            }
        });
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
