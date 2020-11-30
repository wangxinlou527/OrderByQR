package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowLanguageDialog {

    private LanguageDialog customDialog;

    public ShowLanguageDialog() {

    }
    public void show(final Context context, int type , String titile, final OnBottomClickListener onBottomClickListener) {
        customDialog = new LanguageDialog(context,type,titile);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm_2), new LanguageDialog.onYesOnClickListener() {
            @Override
            public void onYesClick(String str) {
                Log.d("------------","onyesClick");
                if (onBottomClickListener != null) {
                    onBottomClickListener.positive(str);
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel_2), new LanguageDialog.onNoClickListener() {
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
        void positive(String str);

        void negative();

    }


}
