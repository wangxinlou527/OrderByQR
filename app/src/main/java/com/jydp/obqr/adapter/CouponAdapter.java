package com.jydp.obqr.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.CouponEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class CouponAdapter extends BaseQuickAdapter<CouponEntity.DataBean, BaseViewHolder> {

    private int mPosition;
    private Context mContext;

    public CouponAdapter(int layoutResId, @Nullable List<CouponEntity.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper,CouponEntity.DataBean item) {
        switch (item.getType()){
            case 0:
                helper.setText(R.id.item_tv_1,mContext.getResources().getString(R.string.tab1_coupon_tv_3));
                break;

            case 1:
                helper.setText(R.id.item_tv_1,mContext.getResources().getString(R.string.tab1_coupon_tv_2));
                break;

            case -1:
                helper.setText(R.id.item_tv_1,mContext.getResources().getString(R.string.tab1_coupon_tv_1));
                break;

        }
        helper.setText(R.id.item_tv_2,item.getName());

        if(helper.getAdapterPosition() == mPosition){
            helper.getView(R.id.item_check).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_check).setVisibility(View.GONE);
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
