package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;
import com.jydp.obqr.viewmodel.BillViewModel;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowDayinDialog {

    private DayinDialog customDialog;

    public ShowDayinDialog() {

    }
    public void show(final Context context, int type , String titile,BillViewModel model,final OnBottomClickListener onBottomClickListener) {
        customDialog = new DayinDialog(context,type,titile,model);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm_2), new DayinDialog.onYesOnClickListener() {
            @Override
            public void onYesClick() {
                Log.d("------------","onyesClick");
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel_2), new DayinDialog.onNoClickListener() {
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
