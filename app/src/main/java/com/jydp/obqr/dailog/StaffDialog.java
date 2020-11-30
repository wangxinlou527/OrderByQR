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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.StaffAdapter;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.util.ToastUtil;

import java.util.List;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class StaffDialog extends Dialog implements View.OnTouchListener {

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
    private StaffAdapter staffAdapter;
    private List <StaffEntity.DataBean> staffList;
    private StaffEntity.DataBean data;


    public StaffDialog(@NonNull Context context, int type, String titleStr,List <StaffEntity.DataBean> staffList) {
        super(context, R.style.CustomDialog_2);
        this.titleStr = titleStr;
        this.type = type;
        this.staffList = staffList;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_labels);
        recyclerView = findViewById(R.id.dailog_rv);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        staffAdapter = new StaffAdapter(R.layout.item_staff,staffList);
        staffAdapter.setmPosition(-1);
        staffAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                staffAdapter.setmPosition(position);
                data = staffList.get(position);
                staffAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(staffAdapter);
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
                if(data != null) {
                    if (yesOnClickListener != null) {
                        yesOnClickListener.onYesClick(data);
                    }
                }else {
                    ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_1));
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

        void onYesClick(StaffEntity.DataBean data);
    }

    public interface onNoClickListener {
        void onNoClick();
    }

}
