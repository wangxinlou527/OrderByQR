package com.jydp.obqr.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.Gson;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.entity.LoginEntity;
import com.jydp.obqr.entity.PrintDetailEntity;
import com.jydp.obqr.entity.ShoppingEntity;
import com.jydp.obqr.entity.UserDetailEntity;
import com.jydp.obqr.source.BillDataSource;
import com.jydp.obqr.source.UserDataResource;
import com.jydp.obqr.net.ApiService;
import com.jydp.obqr.net.Contains;
import com.jydp.obqr.netnavigator.SimpleNetNavigator;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.SingleLiveEvent;


public class LoginViewModel extends AndroidViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    //给你写个例子
    //创建一个Snackbar或者Toast的LiveEvent
    public SingleLiveEvent<String> snackbarEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> dialogEvent = new SingleLiveEvent<>();

    public String Login_cn_1 = "前厅管理端";
    public String Login_cn_2 = "用户名";
    public String Login_cn_3 = "密码";
    public String Login_cn_4 = "登录";
    public String Login_cn_5 = "设置语言";

    public String Login_jp_1 = "飲食店管理端末";
    public String Login_jp_2 = "ユーザー名";
    public String Login_jp_3 = "パスワード";
    public String Login_jp_4 = "ログイン";
    public String Login_jp_5 = "言語设定";


    //是否显示密码
    public ObservableField<Boolean> isShowPassword = new ObservableField<>(false);


    public LoginViewModel(@NonNull Application application) {
        super(application);

    }

    //登录
    public void login(String phone, String password, String language, final Callback callback) {

        //登录成功
        UserDataResource.getInstance(getApplication()).login(phone, password,language,
                new SimpleNetNavigator<LoginEntity>(snackbarEvent, dialogEvent, callback) {

                    @Override
                    public String getTitle() {
                        return "Loading...";
                    }

                    @Override
                    public void success(LoginEntity data) {

                        if (data.getAccess_token() != null) {
                            Log.e("TAG", "data.getMsg()--222---"+new Gson().toJson(data));
                            Log.e("TAG", "正在登录地址-----"+ ApiService.baseUrl+ Contains.LOGIN_URL);
                            callback.success();

                        } else {
                            snackbarEvent.setValue("token为null");
                            Log.e("TAG", "data.getMsg()-----"+data);
                        }

                    }

                    @Override
                    public void fail(String error) {

                       Log.e("TAG", "fail: error-------" + error);
                        snackbarEvent.setValue(error);
                    }


                });

    }


    public void getUseDetail( final Callback callback) {


        UserDataResource.getInstance(getApplication()).getUseDetail(
                new SimpleNetNavigator<UserDetailEntity>(snackbarEvent, dialogEvent, callback) {

                    @Override
                    public String getTitle() {

                        return "Loading...";
                    }

                    @Override
                    public void success(UserDetailEntity data) {
                        Log.e("TAG", "获取当前数据详情------------"+new Gson().toJson(data));

                    }

                    @Override
                    public void fail(String error) {

 //                       Log.e("TAG", "fail: error-------" + error);
                        snackbarEvent.setValue(error);
                    }


                });

    }




    public void getShopping( final Callback callback) {

        //登录成功
        UserDataResource.getInstance(getApplication()).getShopping(
                new SimpleNetNavigator<ShoppingEntity>(snackbarEvent, dialogEvent, callback) {

                    @Override
                    public String getTitle() {

                        return ".";
                    }

                    @Override
                    public void success(ShoppingEntity data) {
//                        Log.e("TAG", "获取当前数据详情------------"+new Gson().toJson(data));

                    }

                    @Override
                    public void fail(String error) {

//                        Log.e("TAG", "fail: error-------" + error);
                        snackbarEvent.setValue(error);
                    }


                });

    }

//    public void getDetailsPrint( final Callback callback) {
//
//        //登录成功
//        UserDataResource.getInstance(getApplication()).getDetailsPrint(
//                new SimpleNetNavigator<PrintDetailEntity>(snackbarEvent, dialogEvent, callback) {
//
//                    @Override
//                    public String getTitle() {
//
//                        return "正在登录...";
//                    }
//
//                    @Override
//                    public void success(PrintDetailEntity data) {
////                        Log.e("TAG", "获取当前数据详情------------"+new Gson().toJson(data));
//
//                    }
//
//                    @Override
//                    public void fail(String error) {
//
//                        Log.e("TAG", "fail: 获取当前数据详情-------" + error);
//                        snackbarEvent.setValue(error);
//                    }
//
//
//                });
//
//    }





}
