package com.jydp.obqr.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.jydp.obqr.App;

/**
 * Created by zhangzc on 2017/4/28.
 * Toast 工具类.简化Toast的使用，并且不用关心线程的问题。
 */
public class ToastUtil {

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /**
     * show a short toast
     *
     * @param text
     */
    public static void show(String text) {
        safeShow(App.getContext(), text, Toast.LENGTH_SHORT);
    }

    /**
     * show a short toast
     *
     * @param context
     * @param text
     */
    public static void show(Context context, String text) {
        if (TextUtils.isEmpty(text))return;
        safeShow(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * show a long toast
     *
     * @param context
     * @param text    string text
     */
    public static void showLong(Context context, String text) {
        safeShow(context, text, Toast.LENGTH_LONG);
    }


    /**
     * show a short toast
     *
     * @param context
     * @param resId   string id
     */
    public static void show(Context context, int resId) {
        show(context, context.getString(resId));
    }

    public static void showLong(Context context, int resId) {
        showLong(context, context.getString(resId));
    }


    /**
     * 安全弹出Toast。处理线程的问题。
     *
     * @param context
     * @param text
     * @param durarion
     */
    private static void safeShow(final Context context, final String text, final int durarion) {
        if (Looper.myLooper() != Looper.getMainLooper()) {//如果不是在主线程弹出吐司，那么抛到主线程弹
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, text, durarion);
                }
            });
        } else {
            showToast(context, text, durarion);
        }
    }

    /**
     * 弹出Toast，处理单例的问题。
     *
     * @param context
     * @param text
     * @param durarion
     */
    private static void showToast(Context context, String text, int durarion) {
        if (text==null)return;
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        }
        sToast.setDuration(durarion);
        sToast.setText(text);
        sToast.show();
    }

    private static void showCenterToast(Context context, String text, int durarion) {
        if (text==null)return;
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        }
        sToast.setDuration(durarion);
        sToast.setGravity(Gravity.CENTER,0,0);
        sToast.setText(text);
        sToast.show();
    }

    private static void safeCenterShow(final Context context, final String text, final int durarion) {
        if (Looper.myLooper() != Looper.getMainLooper()) {//如果不是在主线程弹出吐司，那么抛到主线程弹
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    showCenterToast(context, text, durarion);
                }
            });
        } else {
            showCenterToast(context, text, durarion);
        }
    }


    public static void CenterShow(String text) {
        if (TextUtils.isEmpty(text))return;
        safeCenterShow(App.getContext(), text, Toast.LENGTH_SHORT);
    }

    public static void showToastOne(Context context,String message){
        if (sToast==null) {
            sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER,0,0);
        }else{
            View view =sToast.getView();
            sToast.cancel();
            sToast= new Toast(context);
            sToast.setView(view);
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER,0,0);
            sToast.setText(message);
        }
        sToast.show();
    }

}
