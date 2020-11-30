package com.jydp.obqr.source;

import android.app.Application;
import android.util.Log;


import com.jydp.obqr.entity.LoginEntity;
import com.jydp.obqr.entity.PrintDetailEntity;
import com.jydp.obqr.entity.ShoppingEntity;
import com.jydp.obqr.entity.UserDetailEntity;
import com.jydp.obqr.netnavigator.NetNavigator;
import com.jydp.obqr.netnavigator.NetNavigatorWrapper;
import com.jydp.obqr.netnavigator.SimpleNetNavigator;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.RetrofitUtil;
import com.jydp.obqr.util.RxUtil;

import java.lang.ref.WeakReference;


public class UserDataResource {
    private static final String TAG = UserDataResource.class.getSimpleName();
    private static UserDataResource instance = null;
    private WeakReference<Application> appWeak;

    /**
     * 采用弱引用的方式传入,其实没必要,因为application对象是肯定存在的
     *
     * @param appWeak application的弱引用对象
     */
    private UserDataResource(WeakReference<Application> appWeak) {
        this.appWeak = appWeak;
    }

    /**
     * 单例
     *
     * @param app
     * @return instance
     */
    public static UserDataResource getInstance(Application app) {
        if (instance == null) {
            synchronized (UserDataResource.class) {
                if (instance == null) {
                    instance = new UserDataResource(new WeakReference<>(app));
                }
            }
        }
        return instance;
    }

    //登录方法
    public void login(String username, String password, String language, final SimpleNetNavigator<LoginEntity> netNavigator) {
        NetNavigator<LoginEntity> netNavigatorWapper = new NetNavigatorWrapper<LoginEntity>(netNavigator) {
            //我在这里重写success方法,就可以获得数据

            @Override
            public void success(LoginEntity loginEntity) {
                super.success(loginEntity);

                //保存用户的token
                ISharedPreference.getInstance(appWeak.get()).saveToken(loginEntity.getAccess_token());
                ISharedPreference.getInstance(appWeak.get()).saveTime(System.currentTimeMillis());
                //ISharedPreference.getInstance(appWeak.get()).saveMerchant_id(String.valueOf(loginEntity.getMerchant_id()));
                Log.e("TAG", "保存用户的token------------"+loginEntity.getAccess_token() + "," + loginEntity.getMerchant_id() );
                Log.e("TAG", "保存用户的time------------"+ System.currentTimeMillis() + "" );
            }

            @Override
            public void fail(String error) {
                Log.e("TAG", "error-----"+error);

                super.fail(error);
            }

        };
        //然后传入代理对象就可以了
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.login(username, password,language), netNavigatorWapper);
    }
    //登录方法
    public void getUseDetail( final SimpleNetNavigator<UserDetailEntity> netNavigator) {
        NetNavigator<UserDetailEntity> netNavigatorWapper = new NetNavigatorWrapper<UserDetailEntity>(netNavigator) {
            //我在这里重写success方法,就可以获得数据

            @Override
            public void success(UserDetailEntity userDetailEntity) {
                super.success(userDetailEntity);

                //保存用户的token
                ISharedPreference.getInstance(appWeak.get()).saveMerchant_id(userDetailEntity.getMerchant_id());
                Log.e("TAG", "保存店铺的Merchant_id------------"+userDetailEntity.getMerchant_id());
            }

            @Override
            public void fail(String error) {
                Log.e("TAG", "保存店铺的token-----"+error);

                super.fail(error);
            }

        };
        //然后传入代理对象就可以了
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getUseDetail(), netNavigatorWapper);
    }
    //登录方法
    public void getShopping( final SimpleNetNavigator<ShoppingEntity> netNavigator) {
        NetNavigator<ShoppingEntity> netNavigatorWapper = new NetNavigatorWrapper<ShoppingEntity>(netNavigator) {
            //我在这里重写success方法,就可以获得数据

            @Override
            public void success(ShoppingEntity shoppingEntity) {
                super.success(shoppingEntity);

                //保存用户的token
                //保存用户的token
                ISharedPreference.getInstance(appWeak.get()).saveShop_name(shoppingEntity.getName());
                Log.e("TAG", "保存店铺的名字------------"+shoppingEntity.getName());
            }

            @Override
            public void fail(String error) {
                Log.e("TAG", "保存店铺的名字-----"+error);

                super.fail(error);
            }

        };
        //然后传入代理对象就可以了
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getShopping(), netNavigatorWapper);
    }




}
