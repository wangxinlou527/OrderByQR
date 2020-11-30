package com.jydp.obqr.dailog;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class DayinDialog extends Dialog implements View.OnClickListener , View.OnTouchListener {

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
    private ImageView testIv,edIv;
    private TextView okTv;
    private EditText editText;
    private ThreadPool threadPool;
    private int id = 0;
    private BillViewModel model;
    private String oldIp;

    public DayinDialog(@NonNull Context context, int type, String titleStr, BillViewModel model) {
        super(context, R.style.CustomDialog_2);
        this.titleStr =titleStr;
        this.type = type;
        this.model = model;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_dayin);

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
                    hideKeyBoard();
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
                    hideKeyBoard();
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
        testIv = findViewById(R.id.dailog_test_iv);
        edIv = findViewById(R.id.dailog_ed_iv);
        editText = findViewById(R.id.dailog_ip_ed);
        okTv = findViewById(R.id.dailog_ok);
        okTv.setOnClickListener(this);
        testIv.setOnClickListener(this);
        edIv.setOnClickListener(this);
        editText.setOnClickListener(this);
        titleTv.setText(titleStr);

        okTv.setOnTouchListener(this);
        testIv.setOnTouchListener(this);
        edIv.setOnTouchListener(this);
        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);

        if(model.staffPrint.get() != null) {
            editText.setText(model.staffPrint.get().getIp());
        }
        oldIp = editText.getText().toString();

        if (type == 1){
            //点击dialog以外的空白处是否隐藏
            setCanceledOnTouchOutside(true);
        }else{
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

        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }

        threadPool = ThreadPool.getInstantiation();
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
        Log.d("-------","yesy");
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dailog_test_iv:
//                initPrinter(editText.getText().toString().trim());
                if( DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {

                    if(!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState()) {
                        ToastUtil.CenterShow(getContext().getString(R.string.setting_dayinji_3));
                        Log.e("--------", "打印机初始化IP设置异常:" + 0);
                        //                          ToastUtil.showToastOne(getBaseContext(),getString(R.string.setting_dayinji_1) + model.staffPrint.get().getIp() + getString(R.string.setting_dayinji_2) );
                    }else{
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool,null,true,1);
                    }
                }
                break;
            case R.id.dailog_ed_iv:
                editText.setEnabled(true);
                //切换聚焦
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                editText.setSelection(editText.getText().length());
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                edIv.setVisibility(View.INVISIBLE);
                okTv.setVisibility(View.VISIBLE);
                break;
            case R.id.dailog_ip_ed:

                break;
            case R.id.dailog_ok:
                edIv.setVisibility(View.VISIBLE);
                okTv.setVisibility(View.INVISIBLE);
//                closeKeyboard();
                editText.clearFocus();
                editText.setEnabled(false);
                model.getChangePrint(model.staffPrint.get().getId(), editText.getText().toString().trim(), new ffCallback() {
                    @Override
                    public void success() {
                        Log.d("----------","DAIYI-------" +DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState() );
                        if(DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState()) {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].closePort(0);
                        }
                        oldIp = editText.getText().toString().trim();
                        Log.d("----------","DAIYI-------" +oldIp );
                        new DeviceConnFactoryManager.Build()
                                //设置端口连接方式
                                .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                                //设置端口IP地址
                                .setIp(oldIp)//"192.168.199.213"
                                //设置端口ID（主要用于连接多设备）
                                .setId(0)
                                //设置连接的热点端口号
                                .setPort(9100)
                                .build();

                        threadPool.addTask(new Runnable() {
                            @Override
                            public void run() {
                                DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].openPort();
//                                if(!DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState()) {
//                                    ToastUtil.CenterShow(getContext().getString(R.string.setting_dayinji_3));
//                                    Log.e("--------", "打印机初始化IP设置异常:" + 0);
//                                    //                          ToastUtil.showToastOne(getBaseContext(),getString(R.string.setting_dayinji_1) + model.staffPrint.get().getIp() + getString(R.string.setting_dayinji_2) );
//                                }
                            }
                        });

                    }

                    @Override
                    public void file(String s) {
                        Log.d("-----------","oldIp");
                        editText.setText(oldIp);
                    }


                });
//                ISharedPreference.getInstance(App.app).savePrint_ip(editText.getText().toString().trim());
                break;
        }
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


    public void initPrinter(String ip) {

        //初始化端口信息
        new DeviceConnFactoryManager.Build()
                //设置端口连接方式
                .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                //设置端口IP地址
                .setIp(ip)
                //设置端口ID（主要用于连接多设备）
                .setId(id)
                //设置连接的热点端口号
                .setPort(9100)
                .build();


        threadPool = ThreadPool.getInstantiation();
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
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
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
}
