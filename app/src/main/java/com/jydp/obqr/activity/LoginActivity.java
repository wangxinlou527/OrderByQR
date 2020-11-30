package com.jydp.obqr.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.databinding.ActivityLoginBinding;
import com.jydp.obqr.databinding.LanguagelDialogBinding;
import com.jydp.obqr.listener.LoginListener;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.LoginViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.Locale;


//Unable to resolve host "isv.shanghankj.com": No address associated with hostname
//登录页面
public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener , TextView.OnEditorActionListener {

    private LoginViewModel model;
    private ProgressDialog dialog;
    private String payStr = "zh-CN";
    private CustomDialog customDialog;

    @Override
    protected Activity getChildActivity() {
        return this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(this).get(LoginViewModel.class);
        dataBinding.setViewModel(model);
        initView();


        customDialog = new CustomDialog(this, 1);
        //登录
        //在Activity中注册两个LiveData,
        model.snackbarEvent.observe(this, s -> Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show());
        dataBinding.logLagTv.setOnClickListener(this);//语言设置按钮

        model.dialogEvent.observe(this, s -> {
            if (s == null) {
                if (customDialog != null) {
                    customDialog.dismiss();
                }
            } else {
                //我的dialog需要一个标题,所以我需要一个接口方法来获取标题
               // dialog = ProgressDialog.show(LoginActivity.this, s, "请稍等……", false, false);
                customDialog.show();
            }
        });

        switch (ISharedPreference.getInstance(getApplication()).getLocale()) {
            case "zh-CN":
                showCN();

                break;

            case "en":

                break;
            case "ja-JP":
                showJP();

                break;
        }

        //事件监听
        dataBinding.setListener(new LoginListener() {
            @Override
            public void login() {
                //登录
                String phone = dataBinding.loginAccount.getText().toString().trim();
                String password = dataBinding.loginPassword.getText().toString().trim();

//                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(phone)) {
//                    model.snackbarEvent.setValue("用户名或密码不能为空");
//                    return;
//                }
                //语言: 1.en;2.ja-JP;3.zh-CN;4.zh-HK;5.ko-KR

                if (dataBinding.logLagTv.getText().toString().equals(model.Login_cn_5)) {
                    payStr = "zh-CN";
                } else if (dataBinding.logLagTv.getText().toString().equals("English")) {
                    payStr = "en";
                } else if (dataBinding.logLagTv.getText().toString().equals(model.Login_jp_5)) {
                    payStr = "ja-JP";
                }

                Log.e("TAG", "((TextView) v).getText().toString()----" + payStr);

                //登录成功



                model.login(dataBinding.loginAccount.getText().toString(), dataBinding.loginPassword.getText().toString(), payStr, () -> {
                    ISharedPreference.getInstance(App.app).saveLocale(payStr);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();//登录成功关闭这个activity
                    //获取用户信息
                    model.getUseDetail(new Callback() {
                        @Override
                        public void success() {

                        }
                    });

                });
            }

        });

    }

    private void initView(){

        dataBinding.loginPassword.setOnEditorActionListener(this);
        dataBinding.loginAccount.setOnEditorActionListener(this);
    }


    //显示bottomDialog
    private void intBottomSheetDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        LanguagelDialogBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.languagel_dialog, null, false);
        dialog.setContentView(binding.getRoot());

        BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) binding.getRoot().getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        binding.loginLangChinese.setOnClickListener(v -> {

            showCN();
            dialog.dismiss();
            ISharedPreference.getInstance(getApplication()).saveLocale("zh-CN");
        });

        binding.loginLangJapan.setOnClickListener(v -> {
            showJP();
            dialog.dismiss();
            ISharedPreference.getInstance(getApplication()).saveLocale("ja-JP");
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    private int getWindowHeight() {
        Resources res = LoginActivity.this.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_lag_tv:
                intBottomSheetDialog();//语言选择
                break;
        }
    }

    public void showCN() {
        dataBinding.loginTitle.setText(model.Login_cn_1);
        dataBinding.loginAccount.setHint(model.Login_cn_2);
        dataBinding.loginPassword.setHint(model.Login_cn_3);
        dataBinding.loginBtn.setText(model.Login_cn_4);
        dataBinding.logLagTv.setText(model.Login_cn_5);
    }

    public void showJP() {
        dataBinding.loginTitle.setText(model.Login_jp_1);
        dataBinding.loginAccount.setHint(model.Login_jp_2);
        dataBinding.loginPassword.setHint(model.Login_jp_3);
        dataBinding.loginBtn.setText(model.Login_jp_4);
        dataBinding.logLagTv.setText(model.Login_jp_5);
    }

    @Override
    protected void onDestroy() {
        if(customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();
        Log.d("---------"," LoginActivity onDestroy");
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
}
