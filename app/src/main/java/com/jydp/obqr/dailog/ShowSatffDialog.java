package com.jydp.obqr.dailog;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.jydp.obqr.R;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.fragment.Tab1Right;

import java.util.List;

/**
 * @author yeq
 * @description:
 * @date :2020/6/29 16:11
 */
public class ShowSatffDialog {

    private StaffDialog customDialog;


    public ShowSatffDialog() {
    }

    public void show(final Context context, int type , String titile, List<StaffEntity.DataBean> staffList, final OnBottomClickListener onBottomClickListener) {
        customDialog = new StaffDialog(context,type,titile,staffList);

        customDialog.setYesOnClickListener(context.getString(R.string.str_confirm), new StaffDialog.onYesOnClickListener() {
            @Override
            public void onYesClick(StaffEntity.DataBean data) {
                Log.d("------------","onyesClick");

                if (onBottomClickListener != null) {
                    onBottomClickListener.positive(data);
                }
                customDialog.dismiss();
            }
        });

        customDialog.setNoOnClickListener(context.getString(R.string.cancel), new StaffDialog.onNoClickListener() {
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
        void positive(StaffEntity.DataBean data);

        void negative();

    }

    public StaffDialog getCustomDialog() {
        return customDialog;
    }

    public void setCustomDialog(StaffDialog customDialog) {
        this.customDialog = customDialog;
    }
}
