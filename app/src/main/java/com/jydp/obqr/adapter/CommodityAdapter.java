package com.jydp.obqr.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.dailog.ShowIOSDialog;
import com.jydp.obqr.dailog.ShowLabelsDialog;
import com.jydp.obqr.entity.Ado;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.NowSeatEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class CommodityAdapter extends GroupedRecyclerViewAdapter implements View.OnTouchListener {
    private final List<DifferentEntity.DataBean> differentList;
    private Context mContext;
    private int mGroupPosition;
    private int mChildPosition;


    public CommodityAdapter(Context context, List<DifferentEntity.DataBean> differentList) {
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
        return R.layout.item_commodity_child;
    }

    @Override
    public void onBindHeaderViewHolder(com.donkingliang.groupedadapter.holder.BaseViewHolder holder, int groupPosition) {
        holder.setText(R.id.item_header_tv, differentList.get(groupPosition).getOrder_category_translate().getName());
    }

    @Override
    public void onBindFooterViewHolder(com.donkingliang.groupedadapter.holder.BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

        holder.setText(R.id.item_name, differentList.get(groupPosition).getMenus().get(childPosition).getOrder_menu_translate().getName())
                .setText(R.id.item_money, "¥ " + differentList.get(groupPosition).getMenus().get(childPosition).getPrice_format());

        try {
            Glide.with(App.getContext()).load(differentList.get(groupPosition).getMenus().get(childPosition).getImage_url().get(0)).apply(GlideUtil.getRoundRe(mContext, 2)).into((ImageView) holder.get(R.id.item_img));
        } catch (Exception e) {
        }
        holder.get(R.id.item_btn).setOnTouchListener(this::onTouch);
        if (differentList.get(groupPosition).getMenus() != null) {
            if (differentList.get(groupPosition).getMenus().size() > 0) {

                Log.d("------------1", differentList.get(groupPosition).getMenus().get(childPosition).getStock());
                if (differentList.get(groupPosition).getMenus().get(childPosition).getIs_stocked() == 0) {
                    Log.d("------------2", differentList.get(groupPosition).getMenus().get(childPosition).getStock());
                    holder.get(R.id.item_stock_img).setVisibility(View.GONE);
                    holder.get(R.id.item_btn).setVisibility(View.VISIBLE);
                    holder.get(R.id.item_conunt_tv).setVisibility(View.GONE);

                    holder.get(R.id.item_btn).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("---------", "--------1----");
                            DifferentEntity.DataBean.MenusBean menusBean = differentList.get(groupPosition).getMenus().get(childPosition);

                            if(menusBean.getLabels() !=null) {
                                if (menusBean.getLabels().size() > 0) {
                                    ShowLabelsDialog showLabelsDialog = new ShowLabelsDialog();
                                    showLabelsDialog.show(mContext, 1, menusBean.getOrder_menu_translate().getName(), menusBean, new ShowLabelsDialog.OnBottomClickListener() {
                                        @Override
                                        public void positive() {
                                            Log.d("---------", "--------2----");
                                            DifferentEntity.DataBean.MenusBean menusBean = showLabelsDialog.getCustomDialog().getMenusBean();
                                            haveAtbData(menusBean);
                                        }

                                        @Override
                                        public void negative() {
                                        }
                                    });
                                } else {
                                    notAtbData(menusBean);
                                }
                            }

                        }
                    });

                } else {
                    //固定沽清
                    int count = Integer.parseInt(differentList.get(groupPosition).getMenus().get(childPosition).getStock());
                    if (count > 0) {
                        holder.setText(R.id.item_conunt, differentList.get(groupPosition).getMenus().get(childPosition).getStock());
                        holder.get(R.id.item_stock_img).setVisibility(View.GONE);
                        holder.get(R.id.item_btn).setVisibility(View.VISIBLE);
                        holder.get(R.id.item_conunt_tv).setVisibility(View.VISIBLE);
                        holder.get(R.id.item_btn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("---------", "--------1----");
                                DifferentEntity.DataBean.MenusBean menusBean = differentList.get(groupPosition).getMenus().get(childPosition);
                                if(menusBean.getLabels() !=null) {
                                    if (menusBean.getLabels().size() > 0) {
                                        ShowLabelsDialog showLabelsDialog = new ShowLabelsDialog();
                                        showLabelsDialog.show(mContext, 1, menusBean.getOrder_menu_translate().getName(), menusBean, new ShowLabelsDialog.OnBottomClickListener() {
                                            @Override
                                            public void positive() {
                                                Log.d("---------", "--------2----");
                                                DifferentEntity.DataBean.MenusBean menusBean = showLabelsDialog.getCustomDialog().getMenusBean();
                                                haveAtbData(menusBean);
                                                Ado ado = new Ado();
                                                ado.setmChildPosition(childPosition);
                                                ado.setmGroupPosition(groupPosition);
                                                EventBusHelper.post(new EventMessage(EventCode.EVENT_ADD_ORDER, ado));
                                            }

                                            @Override
                                            public void negative() {
                                            }
                                        });
                                    } else {
                                        notAtbData(menusBean);
                                        Ado ado = new Ado();
                                        ado.setmChildPosition(childPosition);
                                        ado.setmGroupPosition(groupPosition);
                                        EventBusHelper.post(new EventMessage(EventCode.EVENT_ADD_ORDER, ado));
                                    }
                                }

                            }
                        });

                    } else {
                        holder.get(R.id.item_btn).setVisibility(View.GONE);
                        holder.get(R.id.item_stock_img).setVisibility(View.VISIBLE);
                        holder.get(R.id.item_conunt_tv).setVisibility(View.VISIBLE);
                        holder.setText(R.id.item_conunt, "0");
                    }


                }
            }

        }
//        holder.setText(R.id.item_count,"剩余   " + differentList.get(groupPosition).getMenus().get(childPosition).getStock());
    }

    public void haveAtbData(DifferentEntity.DataBean.MenusBean menusBean) {
        NowSeatEntity.DataBean dataBean = new NowSeatEntity.DataBean();
        List<String> attributeIds = new ArrayList<>();
        String attribute = "";
        int price = Integer.valueOf(menusBean.getPrice());

        for (DifferentEntity.DataBean.MenusBean.LabelsBean labelsBean : menusBean.getLabels()) {

            for (DifferentEntity.DataBean.MenusBean.LabelsBean.AttributesBeanX attributesBeanX : labelsBean.getAttributes()) {
                if (attributesBeanX.getOrder_attribute_translate().isChecked()) {
                    attributeIds.add(attributesBeanX.getOrder_attribute_translate().getAttribute_id() + "");
                    if(attribute.equals("")) {
                        attribute =  attributesBeanX.getOrder_attribute_translate().getName();
                    }else{
                        attribute = attribute + "/" + attributesBeanX.getOrder_attribute_translate().getName();
                    }
                    price += Integer.valueOf(attributesBeanX.getPrice());

                }
            }
        }

        dataBean.setMenu_id(Integer.valueOf(menusBean.getId()));
        dataBean.setName(menusBean.getOrder_menu_translate().getName());
        dataBean.setCount("1");
        dataBean.setPrice(price + "");
        dataBean.setAttribute(attribute);
        dataBean.setAttribute_ids(attributeIds);
        EventBusHelper.post(new EventMessage(EventCode.EVENT_DIACAI_LFT, dataBean));

    }

    public void notAtbData(DifferentEntity.DataBean.MenusBean menusBean) {
        NowSeatEntity.DataBean dataBean = new NowSeatEntity.DataBean();
        int price = Integer.valueOf(menusBean.getPrice());
        dataBean.setMenu_id(Integer.valueOf(menusBean.getId()));
        dataBean.setName(menusBean.getOrder_menu_translate().getName());
        dataBean.setCount("1");
        dataBean.setPrice(price + "");
        EventBusHelper.post(new EventMessage(EventCode.EVENT_DIACAI_2_LFT, dataBean));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.4f);
                break;

            case MotionEvent.ACTION_CANCEL:
                v.setAlpha(1f);
                break;

            case MotionEvent.ACTION_UP:
                v.setAlpha(1f);
                break;
        }

        return false;
    }

}