package com.jydp.obqr;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.jydp.obqr.util.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/*
2020/9/28 8:52 星期一
作用 ：
*/
public class App extends MultiDexApplication {
    private Activity topActivity;
    private List<Activity> activities = new ArrayList<>();

    //多语言
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        languageWork();
    }

    private void languageWork() {
        Locale locale = LanguageUtil.getLocale(this);
        LanguageUtil.updateLocale(this, locale);
    }

    public static Context mContext;//应用中使用到的上下文
    public static Handler handler;//应用中使用到的handler对象声明
    public static Thread mainThread;//获取主线程
    public static int mainThreadId;//获取主线程的id

    public static App app;
    public static Context getContext() {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/pingfang.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
 //       languageWork();//多语言

        app = (App) getApplicationContext();


        mContext = getApplicationContext();

        handler = new Handler();
        mainThread = Thread.currentThread();//当前用于初始化Application的线程，即为主线程
        mainThreadId = android.os.Process.myTid();//获取当前主线程的id


        //二维码扫描
//        ZXingLibrary.initDisplayOpinion(this);

        //在这里注册了Activity
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                topActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // 初始化
    }

    //获取到栈顶activity
    public Activity getActivity() {
        //这个要判断是否为空
        Activity activity = activities.size() == 0 ? null : activities.get(activities.size() - 1);
        /*if (activity != null)
            Log.d("TzApplication", "getActivity: 当前栈顶Activity===  " + activity.getLocalClassName());*/
        return activity;
    }

    /**
     * 这个去每个Activity的onCreate里面调用一次
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 这个去每个Activity的onDestroy里面调用一次
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static App getInstance() {
        return app;
    }


}
