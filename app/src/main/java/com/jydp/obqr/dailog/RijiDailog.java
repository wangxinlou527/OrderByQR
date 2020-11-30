package com.jydp.obqr.dailog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jydp.obqr.R;
import com.jydp.obqr.adapter.StaffAdapter;
import com.jydp.obqr.entity.MoneyPrintEntity;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.entity.TakeMoney;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.picker.StaffPickerDialogFragment;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.view.SlideButton;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;

import java.util.List;

import static android.graphics.Color.parseColor;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class RijiDailog extends Dialog  implements View.OnTouchListener, TextView.OnEditorActionListener {

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
    private SlideButton button;
    private TextView totleTv,zhunbeiTv,dayinTv,cashTv;
    private ThreadPool threadPool;
    private int id;
    private RelativeLayout rl;
    private BillViewModel model;
    private Activity activity;
    private int staffId = -1;
    private String bottonId = "0";

    public RijiDailog(@NonNull Activity activity, int type, String titleStr, BillViewModel model) {
        super(activity, R.style.CustomDialog_2);
        this.titleStr = titleStr;
        this.type = type;
        this.activity = activity;
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_riji);


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

                        if(staffId != 0) {

                            if(moneyEd.getText().toString() != null && ! moneyEd.getText().toString().equals("")) {
                                if(StrUtil.isInt(moneyEd.getText().toString())) {

                                    model.getTakeMoney(staffId,contentEd.getText().toString(), bottonId, Integer.valueOf(moneyEd.getText().toString().equals("") ? "0" :moneyEd.getText().toString()), new Callback() {
                                        @Override
                                        public void success() {
                                            btnReceiptPrint(9,true);
//                                            EventBusHelper.post(new EventMessage(EventCode.EVENT_MAIN_RIJI_ALL));
                                            yesOnClickListener.onYesClick();
                                        }
                                    });


                                }else{
                                    ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_3));
                                }

                            }else{
                                ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_3));
                            }
                        }else{
                            ToastUtil.showToastOne(getContext(),getContext().getString(R.string.riji_hint_1));
                        }
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

        totleTv = findViewById(R.id.dailog_totle_tv);
        zhunbeiTv = findViewById(R.id.dailog_zhunbei_tv);
        cashTv = findViewById(R.id.dailog_cash_tv);
        button = findViewById(R.id.button1);
        rl = findViewById(R.id.dailog_rl);
        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);
        moneyEd.setOnEditorActionListener(this);
        contentEd.setOnEditorActionListener(this);

        button.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked) {
                    bottonId = "1";
                }else {

                    bottonId = "0";
                }

            }
        });

        dayinTv = findViewById(R.id.dailog_dayin_tv);
        dayinTv.setOnTouchListener(this);
        dayinTv.setOnClickListener(v -> {
//            EventBusHelper.post(new EventMessage(EventCode.EVENT_MAIN_RIJI));
            btnReceiptPrint(8);
        });

        button.setBigCircleModel(parseColor("#cccccc"), parseColor("#B78B4F"), parseColor("#ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));


        model.getCashPrint(new Callback() {
            @Override
            public void success() {
                totleTv.setText(model.PrintCashDetail.get().getTotal_format());
                zhunbeiTv.setText(model.PrintCashDetail.get().getCheck_input_format());
                cashTv.setText(model.PrintCashDetail.get().getPayments().get(0).getTotal_format());
            }
        });


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

        rl.setOnClickListener(v -> {
            closeKeyboard();
            moneyEd.clearFocus();
            contentEd.clearFocus();
        });

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

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

        threadPool = ThreadPool.getInstantiation();
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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
                v.clearFocus();
                break;
        }
        return false;
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
    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isShowing() && shouldCloseOnTouch(getContext(),event)){
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
    public void hideKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop)
                || (x > (decorView.getWidth()+slop))
                || (y > (decorView.getHeight()+slop));
    }


    public void btnReceiptPrint(int i) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, false, i);
        }else{
            DeviceConnFactoryManager.InitQianting(model,threadPool,i);
        }
    }

    public void btnReceiptPrint(int i, boolean n) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, n, i);
        }else{
            DeviceConnFactoryManager.InitQianting(model,threadPool,i);
        }
    }
}
