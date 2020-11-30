package com.jydp.obqr.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class labelsDialog extends Dialog implements View.OnTouchListener {

    private TextView yes; //确定按钮
    private TextView no; //取消按钮
    private TextView titleTv;
    private View iosView;
    //    private TextView title; //消息标题文本
    private TextView message; //消息提示文本
    private String titleStr; //从外界设置的title文本
    private String yesStr, noStr; //确定文本和取消文本的显示内容
    private Window window = null;
    private onYesOnClickListener yesOnClickListener; //确定按钮被点击了的监听器
    private onNoClickListener noOnClickListener; //取消按钮被点击了的监听器

    private int type;
    private RecyclerView recyclerView;
    private LabelsAdapter labelsAdapter;
    private DifferentEntity.DataBean.MenusBean menusBean;

    public DifferentEntity.DataBean.MenusBean getMenusBean() {
        return menusBean;
    }

    public void setMenusBean(DifferentEntity.DataBean.MenusBean menusBean) {
        this.menusBean = menusBean;
    }

    public labelsDialog(@NonNull Context context, int type, String titleStr, DifferentEntity.DataBean.MenusBean menusBean) {
        super(context, R.style.CustomDialog_2);
        this.titleStr = titleStr;
        this.type = type;
        this.menusBean = menusBean;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_labels);
        recyclerView = findViewById(R.id.dailog_rv);

        for (DifferentEntity.DataBean.MenusBean.LabelsBean labelsBean : menusBean.getLabels()) {

            for (int i = 0; labelsBean.getAttributes().size() > i; i++) {
                if (labelsBean.getType() == 1) {
                    if (i == 0) {
                        labelsBean.getAttributes().get(i).getOrder_attribute_translate().setChecked(true);
                    } else {
                        labelsBean.getAttributes().get(i).getOrder_attribute_translate().setChecked(false);
                    }
                }else if(labelsBean.getType() == 2) {
                    labelsBean.getAttributes().get(i).getOrder_attribute_translate().setChecked(false);
                }
            }

        }


        labelsAdapter = new LabelsAdapter(getContext(), menusBean.getLabels());
        GroupedGridLayoutManager gridLayoutManager = new GroupedGridLayoutManager(getContext(), 5, labelsAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(labelsAdapter);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
        //设置窗口显示
        windowDeploy();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yesOnClickListener != null) {
                    yesOnClickListener.onYesClick();
                }
//                hideInput(getContext());
            }
        });

        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noOnClickListener != null) {
                    noOnClickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        iosView = findViewById(R.id.ios_view);
        titleTv = findViewById(R.id.title_tv);
        titleTv.setText(titleStr);
        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);

        if (type == 1) {
            //点击dialog以外的空白处是否隐藏
            setCanceledOnTouchOutside(true);
        } else {
            setCanceledOnTouchOutside(false);
            no.setVisibility(View.GONE);
            iosView.setVisibility(View.GONE);
        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.4f);
                break;

            case MotionEvent.ACTION_UP:
                v.setAlpha(1f);
                break;
        }

        return false;
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }


    private void windowDeploy() {
        window = getWindow();
        window.setGravity(Gravity.CENTER); //设置窗口显示位置
//        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnClickListener
     */
    public void setYesOnClickListener(String str, onYesOnClickListener onYesOnClickListener) {
        Log.d("-------", "yesy");
        if (str != null) {
            yesStr = str;
        }
        this.yesOnClickListener = onYesOnClickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoClickListener
     */
    public void setNoOnClickListener(String str, onNoClickListener onNoClickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnClickListener = onNoClickListener;
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnClickListener {

        void onYesClick();
    }

    public interface onNoClickListener {
        void onNoClick();
    }


    class LabelsAdapter extends GroupedRecyclerViewAdapter {
        private final List<DifferentEntity.DataBean.MenusBean.LabelsBean> labelsBeanList;
        private Context mContext;
        private int mPosition;
        private int mGroupPosition, mChildPosition;


        public LabelsAdapter(Context context, List<DifferentEntity.DataBean.MenusBean.LabelsBean> differentList) {
            super(context);
            this.labelsBeanList = differentList;
            mContext = context;
        }

        public int getmGroupPosition() {
            return mGroupPosition;
        }

        public void setmGroupPosition(int mGroupPosition) {
            this.mGroupPosition = mGroupPosition;
        }

        public int getmChildPosition() {
            return mChildPosition;
        }

        public void setmChildPosition(int mChildPosition) {
            this.mChildPosition = mChildPosition;
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

            if (labelsBeanList.get(groupPosition).getType() == 1) {
                holder.setText(R.id.item_header_tv, "*" + labelsBeanList.get(groupPosition).getOrder_label_translate().getName());
            } else {
                holder.setText(R.id.item_header_tv, labelsBeanList.get(groupPosition).getOrder_label_translate().getName());
            }

        }

        @Override
        public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

        }

        @Override
        public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

            if (labelsBeanList.get(groupPosition).getAttributes() != null) {
                if (labelsBeanList.get(groupPosition).getAttributes().size() > 0) {
                    //Log.d("------------",labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
//                    Log.d("------------", "AdapterPosition:" + holder.getAdapterPosition());
//                    Log.d("------------", "LayoutPosition:" + holder.getLayoutPosition());
//                    Log.d("------------", "OldPosition:" + holder.getOldPosition());
//                    Log.d("------------", "Position:" + holder.getPosition());
//                    Log.d("------------", "childPosition:" + childPosition);
//                    Log.d("------------", "groupPosition:" + groupPosition);
                    RadioButton checkBox = holder.get(R.id.item_child_tv);
                    CheckBox checkBox2 = holder.get(R.id.item_child_tv_2);

                    if (labelsBeanList.get(groupPosition).getType() == 1) {
                        holder.setText(R.id.item_child_tv, labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
                        checkBox2.setVisibility(View.GONE);
                        checkBox.setVisibility(View.VISIBLE);


                        if (labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().isChecked()) {
                            checkBox.setChecked(true);
                        } else {
                            checkBox.setChecked(false);
                        }


                        checkBox.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Log.d("------------", labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getAttribute_id() + "");
                                for (DifferentEntity.DataBean.MenusBean.LabelsBean.AttributesBeanX attributesBeanX : labelsBeanList.get(groupPosition).getAttributes()) {
                                    attributesBeanX.getOrder_attribute_translate().setChecked(false);
                                }
                                menusBean.getLabels().get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().setChecked(true);
                                labelsAdapter.notifyDataChanged();
                            }
                        });

                    } else {
                        holder.setText(R.id.item_child_tv_2, labelsBeanList.get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().getName());
                        checkBox.setVisibility(View.GONE);
                        checkBox2.setVisibility(View.VISIBLE);
                        checkBox2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menusBean.getLabels().get(groupPosition).getAttributes().get(childPosition).getOrder_attribute_translate().setChecked(checkBox2.isChecked());
                            }
                        });

                    }
                }

            }

        }
    }
}
