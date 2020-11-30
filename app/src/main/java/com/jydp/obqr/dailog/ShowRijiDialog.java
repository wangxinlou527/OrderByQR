package com.jydp.obqr.dailog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;
import com.jydp.obqr.entity.MoneyPrintEntity;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.entity.TakeMoney;
import com.jydp.obqr.viewmodel.BillViewModel;

import java.util.List;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowRijiDialog {

    private RijiDailog customDialog;


    public ShowRijiDialog() {
    }

    public void show(final Activity activity, int type , String titile, BillViewModel model, final OnBottomClickListener onBottomClickListener) {
        customDialog = new RijiDailog(activity,type,titile,model);

        customDialog.setYesOnClickListener(activity.getString(R.string.str_confirm_2), new RijiDailog.onYesOnClickListener() {

            @Override
            public void onYesClick() {

                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.hideKeyBoard();
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(activity.getString(R.string.cancel_2), new RijiDailog.onNoClickListener() {
            @Override
            public void onNoClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.negative();
                }
                customDialog.dismiss();
            }
        });
        customDialog.hideKeyBoard();
        customDialog.show();

    }

    public interface OnBottomClickListener {
        void positive();

        void negative();

    }

    public RijiDailog getCustomDialog() {
        return customDialog;
    }

    public void setCustomDialog(RijiDailog customDialog) {
        this.customDialog = customDialog;
    }
}
