package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowLogOutDialog {

    private LogOutDialog customDialog;

    public ShowLogOutDialog() {

    }
    public void show(final Context context, int type , String titile, final OnBottomClickListener onBottomClickListener) {
        customDialog = new LogOutDialog(context,type,titile);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm_2), new LogOutDialog.onYesOnClickListener() {
            @Override
            public void onYesClick() {
                Log.d("------------","onyesClick");
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel_2), new LogOutDialog.onNoClickListener() {
            @Override
            public void onNoClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.negative();
                }
                customDialog.dismiss();
            }
        });
        customDialog.show();

    }

    public interface OnBottomClickListener {
        void positive();

        void negative();

    }


}
