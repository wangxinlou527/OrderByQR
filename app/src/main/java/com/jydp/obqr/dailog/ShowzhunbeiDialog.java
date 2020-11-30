package com.jydp.obqr.dailog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;
import com.jydp.obqr.activity.BaseActivity;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.viewmodel.BillViewModel;

import java.util.List;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowzhunbeiDialog {

    private Zhunbeilog customDialog;


    public ShowzhunbeiDialog() {
    }

    public void show(final Activity activity, int type , String titile, BillViewModel model, final OnBottomClickListener onBottomClickListener) {
        customDialog = new Zhunbeilog(activity,type,titile,model);

        customDialog.setYesOnClickListener(activity.getString(R.string.str_confirm_2), new Zhunbeilog.onYesOnClickListener() {

            @Override
            public void onYesClick() {
                Log.d("------------","onyesClick");

                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.hideKeyBoard();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(activity.getString(R.string.cancel_2), new Zhunbeilog.onNoClickListener() {
            @Override
            public void onNoClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.negative();
                }
                customDialog.hideKeyBoard();
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
