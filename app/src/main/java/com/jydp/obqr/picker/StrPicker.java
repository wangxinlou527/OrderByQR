package com.jydp.obqr.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.jydp.obqr.R;


/**
 * 日期选择
 * Created by ycuwq on 17-12-28.
 */
public class StrPicker extends WheelPicker<String>{

    private OnDataSelectedListener mOnDataSelectedListener;

    public StrPicker(Context context) {
        this(context, null);
    }

    public StrPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HourAndMinutePicker);
        int textSize = a.getDimensionPixelSize(R.styleable.HourAndMinutePicker_itemTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        int textColor = a.getColor(R.styleable.HourAndMinutePicker_itemTextColor,
                Color.BLACK);
        boolean isTextGradual = a.getBoolean(R.styleable.HourAndMinutePicker_textGradual, true);
        boolean isCyclic = a.getBoolean(R.styleable.HourAndMinutePicker_wheelCyclic, true);
        int halfVisibleItemCount = a.getInteger(R.styleable.HourAndMinutePicker_halfVisibleItemCount, 2);
        int selectedItemTextColor = a.getColor(R.styleable.HourAndMinutePicker_selectedTextColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_selectedTextColor));
        int selectedItemTextSize = a.getDimensionPixelSize(R.styleable.HourAndMinutePicker_selectedTextSize,
                getResources().getDimensionPixelSize(R.dimen.WheelSelectedItemTextSize));
        int itemWidthSpace = a.getDimensionPixelSize(R.styleable.HourAndMinutePicker_itemWidthSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemWidthSpace));
        int itemHeightSpace = a.getDimensionPixelSize(R.styleable.HourAndMinutePicker_itemHeightSpace,
                getResources().getDimensionPixelOffset(R.dimen.WheelItemHeightSpace));
        boolean isZoomInSelectedItem = a.getBoolean(R.styleable.HourAndMinutePicker_zoomInSelectedItem, true);
        boolean isShowCurtain = a.getBoolean(R.styleable.HourAndMinutePicker_wheelCurtain, true);
        int curtainColor = a.getColor(R.styleable.HourAndMinutePicker_wheelCurtainColor, Color.WHITE);
        boolean isShowCurtainBorder = a.getBoolean(R.styleable.HourAndMinutePicker_wheelCurtainBorder, true);
        int curtainBorderColor = a.getColor(R.styleable.HourAndMinutePicker_wheelCurtainBorderColor,
                getResources().getColor(R.color.com_ycuwq_datepicker_divider));
        a.recycle();

        setTextSize(textSize);
        setTextColor(textColor);
        setTextGradual(isTextGradual);
//        setCyclic(isCyclic);
        setHalfVisibleItemCount(halfVisibleItemCount);
        setSelectedItemTextColor(selectedItemTextColor);
        setSelectedItemTextSize(selectedItemTextSize);
        setItemWidthSpace(itemWidthSpace);
        setItemHeightSpace(itemHeightSpace);
        setZoomInSelectedItem(isZoomInSelectedItem);
        setShowCurtain(isShowCurtain);
        setCurtainColor(curtainColor);
        setShowCurtainBorder(isShowCurtainBorder);
        setCurtainBorderColor(curtainBorderColor);

        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                if ( mOnDataSelectedListener != null) {
                    mOnDataSelectedListener.onTimeSelected(item);
                }
            }
        });
    }



    private void onTimeSelected() {
        if ( mOnDataSelectedListener != null) {
            mOnDataSelectedListener.onTimeSelected(getDataList().get(getCurrentPosition()));
        }
    }

    public String getData(){

        if(getDataList() != null) {
            return getDataList().get(getCurrentPosition());
        }
        return null;
    }




    public void setOnDataSelectedListener(OnDataSelectedListener onDataSelectedListener) {
        mOnDataSelectedListener = onDataSelectedListener;
    }

    public interface OnDataSelectedListener {

        void onTimeSelected(String data);
    }



}
