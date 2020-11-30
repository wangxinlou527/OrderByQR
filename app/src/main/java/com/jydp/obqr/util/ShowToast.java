package com.jydp.obqr.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.jydp.obqr.App;

/*
2020/11/20 15:03 星期五
作用 ：
*/
public class ShowToast {



    public static void showToastOne(Context context,String message){
        if (mToast==null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }else{
            View view = mToast.getView();
            mToast.cancel();
            mToast= new Toast(context);
            mToast.setView(view);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(message);
        }
        mToast.show();
    }

    private static String oldMsg;
    protected static Toast mToast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;



}