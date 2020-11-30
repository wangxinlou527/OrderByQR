package com.jydp.obqr.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.PayTypeEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class PayTypeAdapter extends BaseQuickAdapter<PayTypeEntity.DataBean, BaseViewHolder> {

    private int mPosition;
    private Context context;

    public PayTypeAdapter(Context context, int layoutResId, @Nullable List<PayTypeEntity.DataBean> data) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper,PayTypeEntity.DataBean item) {

        helper.setText(R.id.item_tv,item.getName());
        TextView textView = helper.getView(R.id.item_tv);
        textView.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.mipmap.payment), null, null, null);
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
