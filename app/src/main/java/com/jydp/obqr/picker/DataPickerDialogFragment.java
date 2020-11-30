package com.jydp.obqr.picker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.jydp.obqr.R;
import com.jydp.obqr.entity.SeatListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间选择器，弹出框
 * Created by ycuwq on 2018/1/6.
 * @deprecated If you need to use it, please use support or androidx to achieve.
 */
@SuppressLint("ValidFragment")
@Deprecated
public class DataPickerDialogFragment extends DialogFragment {

	protected DataPicker mDataPicker;
	private OnDateChooseListener mOnDateChooseListener;
	private boolean mIsShowAnimation = true;
	protected TextView mCancelButton, mDecideButton;
	private List<SeatListEntity.DataBean> mList;


	@SuppressLint("ValidFragment")
	public DataPickerDialogFragment(List<SeatListEntity.DataBean> mList) {
		this.mList = mList;
	}

	public void setOnDateChooseListener(OnDateChooseListener onDateChooseListener) {
		mOnDateChooseListener = onDateChooseListener;
	}

	public void showAnimation(boolean show) {
		mIsShowAnimation = show;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_data, container);

		mDataPicker = view.findViewById(R.id.data_picker_dialog);

		mDataPicker.setDataList(mList);
		mCancelButton = view.findViewById(R.id.btn_dialog_date_cancel);
		mDecideButton = view.findViewById(R.id.btn_dialog_date_decide);
		mCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		mDecideButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mOnDateChooseListener != null) {
					mOnDateChooseListener.onDateChoose(mDataPicker.getData());
				}
				dismiss();
			}
		});


//		setSelectedDate();
		initChild();
		return view;
	}

	protected void initChild() {

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity(), R.style.DatePickerBottomDialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

		dialog.setContentView(R.layout.dialog_date);
		dialog.setCanceledOnTouchOutside(true); // 外部点击取消

		Window window = dialog.getWindow();
		if (window != null) {
			if (mIsShowAnimation) {
				window.getAttributes().windowAnimations = R.style.DatePickerDialogAnim;
			}
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.gravity = Gravity.BOTTOM; // 紧贴底部
			lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
			lp.dimAmount = 0.35f;
			window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		}

		return dialog;
	}


	private void setSelectedDate() {
//		if (mDatePicker != null) {
//			mDatePicker.setDate(mSelectedYear, mSelectedMonth, mSelectedDay, false);
//		}
	}

	public interface OnDateChooseListener {
		void onDateChoose(SeatListEntity.DataBean data);
	}


}
