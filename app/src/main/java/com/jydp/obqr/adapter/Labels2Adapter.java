package com.jydp.obqr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.jydp.obqr.R;
import com.jydp.obqr.dailog.ShowLabelsDialog;
import com.jydp.obqr.entity.DifferentEntity;

import java.util.List;

/**
 * @author: yeq
 * @date: 2019/10/30
 * 主页
 */
public class Labels2Adapter extends GroupedRecyclerViewAdapter {
    private final List<DifferentEntity.DataBean.MenusBean.LabelsBean> labelsBeanList;
    private Context mContext;


    public Labels2Adapter(Context context, List<DifferentEntity.DataBean.MenusBean.LabelsBean> differentList) {
        super(context);
        this.labelsBeanList = differentList;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return labelsBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return labelsBeanList.get(groupPosition).getAttributes().size();
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
        return R.layout.item_dailog_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.item_commodity_foot;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_dailog_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {

        if(labelsBeanList.get(groupPosition).getType() == 1) {
            holder.setText(R.id.item_header_tv,"*" + labelsBeanList.get(groupPosition).getOrder_label_translate().getName());
        }else{
            holder.setText(R.id.item_header_tv,labelsBeanList.get(groupPosition).getOrder_label_translate().getName());
        }

    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

        if(labelsBeanList.get(groupPosition).getAttributes() != null) {
            if(labelsBeanList.get(groupPosition).getAttributes().size() > 0) {
                //Log.d("------------",labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
                Log.d("------------", "AdapterPosition:" + holder.getAdapterPosition() );
                Log.d("------------", "LayoutPosition:" + holder.getLayoutPosition());
                Log.d("------------", "OldPosition:" + holder.getOldPosition() );
                Log.d("------------", "Position:" + holder.getPosition() );
                Log.d("------------", "childPosition:" + childPosition );
                Log.d("------------", "groupPosition:" + groupPosition );
                RadioButton checkBox = holder.get(R.id.item_child_tv);

                if(labelsBeanList.get(groupPosition).getType() == 1) {
                    holder.setText(R.id.item_child_tv,labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
                    holder.get(R.id.item_child_tv_2).setVisibility(View.GONE);
                    holder.get(R.id.item_child_tv).setVisibility(View.VISIBLE);
                    if(labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().isChecked()) {
                        checkBox.setChecked(true);
                    }else{
                        checkBox.setChecked(false);
                    }

                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(checkBox.isChecked()) {
                                Log.d("------------",labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getAttribute_id() + "");
                            }
                        }
                    });

                }else{
                    holder.setText(R.id.item_child_tv_2,labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
                    holder.get(R.id.item_child_tv).setVisibility(View.GONE);
                    holder.get(R.id.item_child_tv_2).setVisibility(View.VISIBLE);
                }
            }

        }

    }
}