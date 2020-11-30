package com.jydp.obqr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.dailog.ShowLabelsDialog;
import com.jydp.obqr.entity.Ado;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.NowSeatEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.util.GlideUtil;
import com.jydp.obqr.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class GuqingAdapter extends GroupedRecyclerViewAdapter {
    private final List<DifferentEntity.DataBean> differentList;
    private Context mContext;


    public GuqingAdapter(Context context, List<DifferentEntity.DataBean> differentList) {
        super(context);
        this.differentList = differentList;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return differentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return differentList.get(groupPosition).getMenus().size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return true;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_commodity_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.item_commodity_foot;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_guqing_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        holder.setText(R.id.item_header_tv,differentList.get(groupPosition).getOrder_category_translate().getName());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

        holder.setText(R.id.item_name,differentList.get(groupPosition).getMenus().get(childPosition).getOrder_menu_translate().getName());

        try {
            Glide.with(App.getContext()).load(differentList.get(groupPosition).getMenus().get(childPosition).getImage_url().get(0)).apply(GlideUtil.getRoundRe(mContext, 2)).into((ImageView)holder.get(R.id.item_img));
        }catch (Exception e){
        }


        if(differentList.get(groupPosition).getMenus() != null) {
            if (differentList.get(groupPosition).getMenus().size() > 0) {
                if(!differentList.get(groupPosition).getMenus().get(childPosition).getStock().equals("")) {
                    if(StrUtil.isInt(differentList.get(groupPosition).getMenus().get(childPosition).getStock())) {
                        if(Integer.valueOf(differentList.get(groupPosition).getMenus().get(childPosition).getStock()) > 0) {
                            holder.get(R.id.item_stock_img).setVisibility(View.GONE);
                            holder.get(R.id.item_stock_tv).setVisibility(View.VISIBLE);
                            holder.setText(R.id.item_stock_tv,differentList.get(groupPosition).getMenus().get(childPosition).getStock());
                        }else{
                            holder.get(R.id.item_stock_img).setVisibility(View.VISIBLE);
                            holder.get(R.id.item_stock_tv).setVisibility(View.GONE);
                        }
                    }

                }else {
                    holder.get(R.id.item_stock_img).setVisibility(View.GONE);
                    holder.get(R.id.item_stock_tv).setVisibility(View.GONE);
                }

            }
        }


//        holder.setText(R.id.item_count,"剩余   " + differentList.get(groupPosition).getMenus().get(childPosition).getStock());
    }

}