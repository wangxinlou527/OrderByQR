package com.jydp.obqr.dailog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.StaffAdapter;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.picker.DataPickerDialogFragment;
import com.jydp.obqr.picker.StaffPickerDialogFragment;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class Zhunbeilog extends Dialog implements View.OnTouchListener {

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
    private EditText moneyEd;
    private EditText contentEd;
    private TextView nameTv;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private BillViewModel model;
    private int staffId = -1;
    private Activity activity;

    public Zhunbeilog(@NonNull Activity activity, int type, String titleStr, BillViewModel model) {
        super(activity, R.style.CustomDialog_2);
        this.activity = activity;
        this.titleStr = titleStr;
        this.type = type;
        this.model = model;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_zhunbei);


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


                    if(!StrUtil.isInt(moneyEd.getText().toString()) || moneyEd.getText().toString().equals("")) {
                        ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_3));
                        return;
                    }

                    if(staffId == -1) {
                        ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_1));
                        return;
                    }


                    model.getPutMoney(Integer.parseInt(moneyEd.getText().toString().equals("") ? "0" : moneyEd.getText().toString()), contentEd.getText().toString(), staffId, new Callback() {
                        @Override
                        public void success() {
                            ToastUtil.show(getContext().getString(R.string.setting_zhunbei_hint));
                            yesOnClickListener.onYesClick();
                        }
                    });

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
        nameTv = findViewById(R.id.dailog_name_tv);
        moneyEd = findViewById(R.id.dailog_money_ed);
        contentEd = findViewById(R.id.dailog_content_ed);
        relativeLayout = findViewById(R.id.zhunbei_rl);
        linearLayout = findViewById(R.id.dailog_ll);


        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);

        titleTv.setText(titleStr);
        nameTv.setOnClickListener(v -> {
            model.getStaffList(new Callback() {
                @Override
                public void success() {

                   StaffPickerDialogFragment dataPickerDialogFragment = new StaffPickerDialogFragment(model.staffList);
                    dataPickerDialogFragment.setOnDateChooseListener(new StaffPickerDialogFragment.OnDateChooseListener() {
                        @Override
                        public void onDateChoose(StaffEntity.DataBean data) {
                            nameTv.setText(data.getNickname());
                            staffId = data.getId();
                        }
                    });
                    dataPickerDialogFragment.show(activity.getFragmentManager(), "StaffPickerDialogFragment");
                }
            });


        });

        linearLayout.setOnClickListener(v -> {
            contentEd.requestFocus();
            contentEd.setSelection(contentEd.getText().length());
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(contentEd, InputMethodManager.SHOW_IMPLICIT);
        });


//        relativeLayout.setOnClickListener(v -> {
//            moneyEd.clearFocus();
//            contentEd.clearFocus();
//            closeKeyboard();
//        });

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

    //只是关闭软键盘
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isShowing() && shouldCloseOnTouch(getContext(), event)) {
            hideKeyBoard();
        }
        return super.onTouchEvent(event);
    }

    public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && isOutOfBounds(context, event) && getWindow().peekDecorView() != null) {
            return true;
        }
        return false;
    }

    //edContent是输入框
    public void hideKeyBoard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop)
                || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }
}
