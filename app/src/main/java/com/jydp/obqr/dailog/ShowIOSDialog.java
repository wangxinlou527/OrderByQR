package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowIOSDialog {

    private IsoDialog customDialog;

    public ShowIOSDialog() {

    }
    public void show(final Context context, int type , String titile, final OnBottomClickListener onBottomClickListener) {
        customDialog = new IsoDialog(context,type,titile);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm), new IsoDialog.onYesOnClickListener() {
            @Override
            public void onYesClick() {
                Log.d("------------","onyesClick");
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel), new IsoDialog.onNoClickListener() {
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
    public void show2(final Context context, String confirm,int type , String titile, final OnBottomClickListener onBottomClickListener) {
        customDialog = new IsoDialog(context,type,titile);
        customDialog.setYesOnClickListener(confirm, new IsoDialog.onYesOnClickListener() {
            @Override
            public void onYesClick() {
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel), new IsoDialog.onNoClickListener() {
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

    public IsoDialog getCustomDialog() {
        return customDialog;
    }

    public void setCustomDialog(IsoDialog customDialog) {
        this.customDialog = customDialog;
    }
}
