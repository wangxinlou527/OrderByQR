package com.jydp.obqr.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.LoginActivity;
import com.jydp.obqr.adapter.DateAdapter;
import com.jydp.obqr.adapter.ResvrveAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.databinding.FramTab1RightBinding;
import com.jydp.obqr.databinding.FramTab2RightBinding;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.picker.DataPickerDialogFragment;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.KeyUtil;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.view.CommonPopWindow;
import com.jydp.obqr.view.PickerScrollView;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
2020/10/20 10:00 星期二
作用 ：
*/
public class Tab2Right extends BaseFragment<FramTab2RightBinding> implements View.OnClickListener, CommonPopWindow.ViewClickListener {

    private BillViewModel model;
    private DateAdapter dateAdapter;
    private ResvrveAdapter resvrveAdapter;

    //预约
    private String seatId, bookedAt, keyword;
    private int status;
    private String limit = "12";
    private String page = "1";
    private List<String> dateList;
    private CustomDialog customDialog;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab2_right;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);
//        threadPool = ThreadPool.getInstantiation();
        initLoading();
        initView();
//        initData();
    }

    public void initView() {
        binding.page1Btn.setText("<");
        binding.page2Btn.setText(">");
        binding.page1Btn.setOnClickListener(this);
        binding.page2Btn.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.rl2QuanbuBtn.setOnClickListener(this);
        binding.rl2QuedingBtn.setOnClickListener(this);
        binding.rl2QuanbuBtn.setOnClickListener(this);
        binding.rt2KeyEd.setOnEditorActionListener((v, actionId, event) ->{
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    v.clearFocus();
                    break;
            }
            return false;
        });
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.right_book), null, null, null);

        //日期
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rl2DateRv.setLayoutManager(linearLayoutManager);
        dateList = StrUtil.getDateList(getResources().getString(R.string.tab2_zhuohaoyilan));
        dateAdapter = new DateAdapter(R.layout.item_date, dateList);
        dateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dateAdapter.setmPosition(position);
                dateAdapter.notifyDataSetChanged();
                bookedAt = dateList.get(position).trim();
                if (bookedAt.equals(getResources().getString(R.string.tab2_zhuohaoyilan)))
                    bookedAt = "";
                upDateResvrve();
            }
        });
        binding.rl2DateRv.setAdapter(dateAdapter);

        //预约列表
        binding.rl2YudingRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        resvrveAdapter = new ResvrveAdapter(R.layout.item_resvrve, model.reserveLiseData);
        resvrveAdapter.setmPosition(-1);
        resvrveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                resvrveAdapter.setmPosition(position);
                resvrveAdapter.notifyDataSetChanged();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_YUYUE_LFT, model.reserveLiseData.get(position).getId()));
            }
        });
        binding.rl2YudingRv.setAdapter(resvrveAdapter);

    }


    public void initData() {

        seatId = "";
        bookedAt = "";
        keyword = "";
        limit = "12";
        page = "1";

//        Log.d("------",model.reserveDetail.get().getMeta().getPagination().getTotal_pages());
        dateAdapter.setmPosition(0);
        dateAdapter.notifyDataSetChanged();
        resvrveAdapter.setmPosition(-1);
        resvrveAdapter.notifyDataSetChanged();
        binding.btn1.setChecked(true);
        switch (status){
            case 0:
                binding.btn1.setChecked(true);
                break;
            case 1:
                binding.btn2.setChecked(true);
                break;
            case 2:
                binding.btn3.setChecked(true);
        }
        binding.rt2KeyEd.setText("");
        upDate();

    }

    public void upDate(){
        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - model.timeS)) {
            model.upDateToken(new ffCallback() {
                @Override
                public void success() {
                    upDateResvrve();
                    upDateSeatList();
                }
                @Override
                public void file(String s) {

                }
            });
        }else{
            upDateResvrve();
            upDateSeatList();
        }

    }

    public void initLoading(){
        customDialog = new CustomDialog(getContext(), 1);
        model.dialogText.observe(this, s -> {
            if (s == null) {
                if (customDialog != null) {
                    customDialog.dismiss();
                }
            } else {
                //我的dialog需要一个标题,所以我需要一个接口方法来获取标题
                if(model.reserveDetail.get() == null ) {
                    customDialog.show();
                }

            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_1:
                status = 0;
                page = "1";
                upDateResvrve();
                break;

            case R.id.btn_2:
                status = 1;
                page = "1";
                upDateResvrve();

                break;

            case R.id.btn_3:
                status = 2;
                page = "1";
                upDateResvrve();

                break;

            case R.id.page_1_btn:
                if (Integer.valueOf(page) > 1) {
                    page = String.valueOf(Integer.valueOf(page) - 1);
                    binding.rt2PageTv.setText(page + "/" + model.reserveDetail.get().getMeta().getPagination().getTotal_pages());
                    upDateResvrve();
                }
                break;

            case R.id.page_2_btn:
                if (Integer.valueOf(page)  < Integer.valueOf(model.reserveDetail.get().getMeta().getPagination().getTotal_pages())) {
                    page = String.valueOf(Integer.valueOf(page) + 1);
                    binding.rt2PageTv.setText(page + "/" + model.reserveDetail.get().getMeta().getPagination().getTotal_pages());
                    upDateResvrve();
                }
                break;

            case R.id.rl2_quanbu_btn:
//                setAddressSelectorPopup(v);
                getSeatPopup();
                break;

            case R.id.rl2_queding_btn:
                binding.rt2KeyEd.clearFocus();
                KeyUtil.hideInput(getActivity());
                upDateResvrve();
                break;

        }

    }

    public void upDateResvrve() {
        Log.d("------","seatId" + seatId);
        Log.d("------","status" + status);
        model.getReserveList(seatId, bookedAt, binding.rt2KeyEd.getText().toString(), status, limit, page, new Callback() {
            @Override
            public void success() {
                resvrveAdapter.notifyDataSetChanged();
                binding.rt2PageTv.setText(page + "/" + model.reserveDetail.get().getMeta().getPagination().getTotal_pages());
                if(model.reserveLiseData.size() != 0){
                    binding.rt2Ll.setVisibility(View.VISIBLE);
                    binding.rt2Txt.setVisibility(View.GONE);
                }else{
                    binding.rt2Ll.setVisibility(View.GONE);
                    binding.rt2Txt.setVisibility(View.VISIBLE);
                }
            }
        });

        resvrveAdapter.setmPosition(-1);
        resvrveAdapter.notifyDataSetChanged();
        EventBusHelper.post(new EventMessage(EventCode.EVENT_YUYUE_CLEAR_LFT));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_YUYUE_UPDATE_RT:
                initData();
                break;

        }
    }



    private void getSeatPopup() {
        List<SeatListEntity.DataBean> mList = new ArrayList<>();
        SeatListEntity.DataBean dataBean = new SeatListEntity.DataBean();
        dataBean.setNumber("全部");
        mList.add(dataBean);
        mList.addAll(model.seatList);
        DataPickerDialogFragment dataPickerDialogFragment = new DataPickerDialogFragment(mList);
        dataPickerDialogFragment.setOnDateChooseListener(new DataPickerDialogFragment.OnDateChooseListener() {
               @Override
               public void onDateChoose(SeatListEntity.DataBean data) {
                   binding.rl2QuanbuBtn.setText(data.getNumber());
                   if(!data.getNumber().equals("全部")) {
                       seatId = data.getId();
                   }else{
                       seatId = "";
                   }

               }
        });
        dataPickerDialogFragment.show(getActivity().getFragmentManager(), "DatePickerDialogFragment");

    }

    /**
     * 将选择器放在底部弹出框
     *
     * @param v
     */
    private void setAddressSelectorPopup(View v) {
        int screenHeigh = getResources().getDisplayMetrics().heightPixels;

        CommonPopWindow.newBuilder()
                .setView(R.layout.pop_picker_selector_bottom)
//                .setAnimationStyle(R.style.AnimUp)
                .setBackgroundDrawable(new BitmapDrawable())
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(screenHeigh * 0.3f))
                .setViewOnClickListener(this)
                .setBackgroundDarkEnable(true)
                .setBackgroundAlpha(0.7f)
                .setBackgroundDrawable(new ColorDrawable(999999))
                .build(getContext())
                .showAsBottom(v);
    }

    @Override
    public void getChildView(PopupWindow mPopupWindow, View view, int mLayoutResId) {
        switch (mLayoutResId) {
            case R.layout.pop_picker_selector_bottom:
                TextView imageBtn = view.findViewById(R.id.img_guanbi);
                PickerScrollView addressSelector = view.findViewById(R.id.address);

                // 设置数据，默认选择第一条
                addressSelector.setData(model.seatList);
                addressSelector.setSelected(0);

                //滚动监听
                addressSelector.setOnSelectListener(new PickerScrollView.onSelectListener() {
                    @Override
                    public void onSelect(SeatListEntity.DataBean pickers) {
//                        categoryName = pickers.getNumber();
                    }
                });

                //完成按钮
                imageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
//                        text.setText(categoryName);

                    }
                });
                break;
        }
    }

    public void upDateSeatList() {
        model.getSeatList(0, new ffCallback() {
            @Override
            public void success() {

//                upDataZhaodanView();
            }


            @Override
            public void file(String s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        upDate();
    }

    @Override
    public void onDestroy() {
        if(customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();
    }
}
