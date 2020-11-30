package com.jydp.obqr.adapter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.SeatListEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class SeatBaseAdapter extends BaseQuickAdapter<SeatListEntity.DataBean, BaseViewHolder> {

    private int mPotition;


    public SeatBaseAdapter(int layoutResId, @Nullable List<SeatListEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper,SeatListEntity.DataBean item) {

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

        switch (item.getStatus()){
            //没人入座
            case 0:
                helper.getView(R.id.item_status_img).setVisibility(View.VISIBLE);
                helper.getView(R.id.item_people_img).setVisibility(View.INVISIBLE);
                helper.getView(R.id.item_time_img).setVisibility(View.INVISIBLE);
                helper.setText(R.id.item_number_tv,item.getNumber())
                        .setImageResource(R.id.item_status_img,R.mipmap.empty_seat);
                break;
            //已打印二维码
            case 1:
                helper.getView(R.id.item_status_img).setVisibility(View.VISIBLE);
                helper.getView(R.id.item_people_img).setVisibility(View.INVISIBLE);
                helper.getView(R.id.item_time_img).setVisibility(View.INVISIBLE);
                helper.setText(R.id.item_number_tv,item.getNumber())
                .setImageResource(R.id.item_status_img,R.mipmap.qrcode_seat);
                break;
            //有人入座
            case 2:
                helper.getView(R.id.item_status_img).setVisibility(View.INVISIBLE);
                helper.getView(R.id.item_people_img).setVisibility(View.VISIBLE);
                helper.getView(R.id.item_time_img).setVisibility(View.VISIBLE);
                helper.setText(R.id.item_people_tv,item.getCount() + "人")
                .setText(R.id.item_time_tv,item.getTime() + "分")
                .setText(R.id.item_number_tv,item.getNumber());
                break;

        }

        if(mPotition == helper.getAdapterPosition()) {
            helper.getView(R.id.item_check_img).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_check_img).setVisibility(View.GONE);
        }
        
//        helper.setText(R.id.test_tv,item.getNumber() + "");
//        helper.addOnClickListener(R.id.main_grid_buy)   //给图标添加 点击事件
//                .addOnClickListener(R.id.main_grid_icon).
//                setText(R.id.main_grid_price,item.getPrice()+"").
//                setText(R.id.main_grid_old_price,"¥"+item.getPrice_ori()+"").
//                setText(R.id.main_grid_title,item.getDish().getName()+"")  ;
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//        ((TextView) helper.getView(R.id.main_grid_old_price)).getPaint().setAntiAlias(true);
//        Glide.with(MaoYesApplication.getContext()).load(item.getDish().getImage_url()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.main_grid_icon));
    }

    public int getmPotition() {
        return mPotition;
    }

    public void setmPotition(int mPotition) {
        this.mPotition = mPotition;
    }
}
