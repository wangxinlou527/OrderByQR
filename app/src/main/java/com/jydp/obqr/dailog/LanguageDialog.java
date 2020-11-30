package com.jydp.obqr.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.LanguageAdapter;
import com.jydp.obqr.entity.LuanguageItem;
import com.jydp.obqr.util.ISharedPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:02
 */
public class LanguageDialog extends Dialog implements View.OnTouchListener {

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
    private onNoClickListener noOnClickListener; //取消按钮被点击了的监
    private int type;

    private RecyclerView recyclerView;
    private LanguageAdapter languageAdapter;
    private String languageStr;

    public LanguageDialog(@NonNull Context context, int type, String titleStr) {
        super(context, R.style.CustomDialog_2);
        this.titleStr =titleStr;
        this.type = type;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_language);

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
                    yesOnClickListener.onYesClick(languageStr);
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
        recyclerView = findViewById(R.id.dailog_rv);
        yes.setOnTouchListener(this);
        no.setOnTouchListener(this);

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
        languageStr = "中文简体";

        List<LuanguageItem>list = new ArrayList<>();

        LuanguageItem luanguageItem1 = new LuanguageItem();
        luanguageItem1.setName("中文简体");
        luanguageItem1.setDrawable( getContext().getResources().getDrawable(R.mipmap.zh_cn));
        LuanguageItem luanguageItem2 = new LuanguageItem();
        luanguageItem2.setName("日本語");
        luanguageItem2.setDrawable( getContext().getResources().getDrawable(R.mipmap.ja_jp));
        list.add(luanguageItem1);
        list.add(luanguageItem2);


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        languageAdapter = new LanguageAdapter(R.layout.item_language,list);
        switch (ISharedPreference.getInstance(App.app).getLocale()){
            case "zh-CN":
                languageAdapter.setmPosition(0);
                languageStr = list.get(0).getName();
                break;
            case "ja-JP":
                languageAdapter.setmPosition(1);
                languageStr = list.get(1).getName();
                break;
            case "en":

                break;

        }
        languageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                languageStr = list.get(position).getName();
                languageAdapter.setmPosition(position);
                languageAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(languageAdapter);

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


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnClickListener {
        void onYesClick(String s);
    }

    public interface onNoClickListener {
        void onNoClick();
    }

}
