package com.jydp.obqr.dailog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;

import java.util.List;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class GuqingDialog extends Dialog implements View.OnTouchListener {

    private TextView yes; //确定按钮
    private TextView no; //取消按钮
    private TextView titleTv;
    private View iosView;
    //    private TextView title; //消息标题文本
    private TextView message; //消息提示文本
    private String titleStr; //从外界设置的title文本
    private String yesStr, noStr, delStr; //确定文本和取消文本的显示内容
    private Window window = null;
    private onYesOnClickListener yesOnClickListener; //确定按钮被点击了的监听器
    private onNoClickListener noOnClickListener; //取消按钮被点击了的监听器
    private onDelClickListener noDelClickListener; //取消按钮被点击了的监听器

    private Button button1, button2;
    private EditText editText;

    private int type;
    private int stockTpye;
    private int stock;
    private RadioGroup radiogroup;
    private LinearLayout linearLayout;
    private View viewLine;
    private View btnLine;
    private TextView detBtn;

    public GuqingDialog(@NonNull Context context, int type, String titleStr) {
        super(context, R.style.CustomDialog_2);
        this.titleStr = titleStr;
        this.type = type;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_guqing);


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
                    editText.clearFocus();
                    if(!StrUtil.isInt(editText.getText().toString()  ) && !editText.getText().toString().equals("")) {
                        ToastUtil.showToastOne(getContext(),getContext().getString(R.string.tab4_hint_1_txt));
                        return;
                    }
                    yesOnClickListener.onYesClick(stockTpye, editText.getText().toString());
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

        detBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noDelClickListener != null) {
                    noDelClickListener.onDelClick();
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
        editText = findViewById(R.id.dailog_ed);
        button1 = findViewById(R.id.btn_1);
        button2 = findViewById(R.id.btn_2);
        radiogroup = (RadioGroup) findViewById(R.id.dailog_rg);
        linearLayout = findViewById(R.id.dailog_count_ll);
        viewLine = findViewById(R.id.dailog_line);
        btnLine = findViewById(R.id.ios_view_2);
        detBtn = findViewById(R.id.del);
        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);

        if (type == 1) {
            //点击dialog以外的空白处是否隐藏
            setCanceledOnTouchOutside(true);
        } else if (type == 3) {
            btnLine.setVisibility(View.VISIBLE);
            detBtn.setVisibility(View.VISIBLE);
        } else {
            setCanceledOnTouchOutside(false);
            no.setVisibility(View.GONE);
            iosView.setVisibility(View.GONE);
        }
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_1:
                        button1.setTextColor(Color.parseColor("#FFFFFF"));
                        button2.setTextColor(Color.parseColor("#000000"));
                        linearLayout.setVisibility(View.GONE);
                        viewLine.setVisibility(View.GONE);
                        editText.clearFocus();
                        stockTpye = 0;
                        break;
                    case R.id.btn_2:
                        button1.setTextColor(Color.parseColor("#000000"));
                        button2.setTextColor(Color.parseColor("#FFFFFF"));
                        linearLayout.setVisibility(View.VISIBLE);
                        viewLine.setVisibility(View.VISIBLE);
                        stockTpye = 1;
                        break;


                }
            }
        });
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
        if (delStr != null) {
            detBtn.setText(delStr);
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

    public void setDelOnClickListener(String str, onDelClickListener onDelClickListener) {
        if (str != null) {
            delStr = str;
        }
        this.noDelClickListener = onDelClickListener;
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

        void onYesClick(int stockTpye, String s);
    }

    public interface onNoClickListener {
        void onNoClick();
    }

    public interface onDelClickListener {
        void onDelClick();
    }
}
