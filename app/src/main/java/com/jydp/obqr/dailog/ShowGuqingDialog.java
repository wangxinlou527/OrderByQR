package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowGuqingDialog {

    private GuqingDialog customDialog;




    public ShowGuqingDialog() {
    }

    public void show(final Context context, int type , String titile,final OnBottomClickListener onBottomClickListener) {
        customDialog = new GuqingDialog(context,type,titile);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm), new GuqingDialog.onYesOnClickListener() {
            @Override
            public void onYesClick(int i,String s) {
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive(i,s);
                }
                customDialog.dismiss();
            }

        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel), new GuqingDialog.onNoClickListener() {
            @Override
            public void onNoClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.negative();
                }
                customDialog.dismiss();
            }
        });
        customDialog.setDelOnClickListener("删除", new GuqingDialog.onDelClickListener() {

            @Override
            public void onDelClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.del();
                    Log.d("------","删除2");

                }
                Log.d("------","删除");
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }

    public interface OnBottomClickListener {
        void positive(int i ,String s);

        void negative();

        void del();

    }


}
