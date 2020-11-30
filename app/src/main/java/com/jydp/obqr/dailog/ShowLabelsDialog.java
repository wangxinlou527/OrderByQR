package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowLabelsDialog {

    private labelsDialog customDialog;




    public ShowLabelsDialog() {
    }

    public void show(final Context context, int type , String titile,DifferentEntity.DataBean.MenusBean menusBeans, final OnBottomClickListener onBottomClickListener) {
        customDialog = new labelsDialog(context,type,titile,menusBeans);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm), new labelsDialog.onYesOnClickListener() {
            @Override
            public void onYesClick() {
                Log.d("------------","onyesClick");

                if (onBottomClickListener != null) {
                    onBottomClickListener.positive();
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel), new labelsDialog.onNoClickListener() {
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

    public labelsDialog getCustomDialog() {
        return customDialog;
    }

    public void setCustomDialog(labelsDialog customDialog) {
        this.customDialog = customDialog;
    }

}
