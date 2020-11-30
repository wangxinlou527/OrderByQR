package com.jydp.obqr.dailog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.util.GlideUtil;
import com.jydp.obqr.view.CornersGifView;


/**
 * @Date on 2018/12/10 14:17
 * @Author yq
 * @Version 1.0
 * @Describe 自定义对话框
 **/

public class CustomDialog extends ProgressDialog {

//    private static CustomDialog customDialog;
//
//    public static CustomDialog getInstance(Context context, int theme){
//        if (customDialog == null){
//            synchronized (CustomDialog.class){
//                if (customDialog == null){
//                    customDialog = new CustomDialog(context,theme);
//                }
//            }
//        }
//        return customDialog;
//    }

    private int theme;

    public CustomDialog(Context context) {
        super(context);
    }


    public CustomDialog(Context context, int theme) {
        super(context, theme);
        this.theme = theme;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {

        setContentView(R.layout.load_dialog_2);
        ImageView mGifImageView = findViewById(R.id.load_img);
        Glide.with(App.app).load(R.mipmap.loading).apply(GlideUtil.getRoundRe(getContext(), 8)).into(mGifImageView);
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.gravity = Gravity.CENTER;
//         lp.width  = 180;
//        lp.height = 180;
////        getWindow().setFlags(500, 500);
//        getWindow().setAttributes(lp);
//        getWindow().setBackgroundDrawableResource(R.drawable.bg_5);



        Log.d("-------------","theme:" + theme);
        if(theme == 3001){
            //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
            Log.d("------------","theme：" + "滑动取消");
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                        Log.d("-----------", "起始位置1：(" + event.getX() + "," + event.getY());
                        dismiss();
                    }

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if(event.getX() > 180 || event.getX() < 0 || event.getY() > 180 || event.getY() < 0)
                            dismiss();
                        Log.d("-----------", "起始位置2：(" + event.getX() + "," + event.getY());
                    }
                    return true;
                }
            });
        }else{
            setCancelable(false);
            setCanceledOnTouchOutside(false);
        }


    }

    @Override
    public void show() {
        super.show();
    }

}
