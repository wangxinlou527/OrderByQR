package com.jydp.obqr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.jydp.obqr.R;
import com.jydp.obqr.activity.LoginActivity;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.databinding.FramTab2LeftBinding;
import com.jydp.obqr.databinding.FramTab2RightBinding;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.picker.DataPickerDialogFragment;
import com.jydp.obqr.picker.DatePickerDialogFragment;
import com.jydp.obqr.picker.StrPickerDialogFragment;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/*
2020/10/20 10:00 星期二
作用 ：
*/
public class Tab2Left extends BaseFragment<FramTab2LeftBinding> implements View.OnClickListener,TextView.OnEditorActionListener, View.OnTouchListener {

    private BillViewModel model;
    private int bookingId;

    //0修改,1添加
    private int type;
    private String seatId;
    private String status;
    private CustomDialog customDialog;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab2_left;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);

       // initLoading();
        initView();
        initData();
    }

    public void initView(){
        binding.addBtn.setOnClickListener(this);
        binding.lft2OkBtn.setOnClickListener(this);
        binding.lft2NoBtn.setOnClickListener(this);
        binding.lft2SeatTv.setOnClickListener(this);
        binding.lft2StatusTv.setOnClickListener(this);
        binding.lft2TimeTv.setOnClickListener(this);
        binding.addBtn.setOnTouchListener(this);
        binding.lft2OkBtn.setOnTouchListener(this);
        binding.lft2NoBtn.setOnTouchListener(this);
        binding.lft2SeatTv.setOnTouchListener(this);
        binding.lft2StatusTv.setOnTouchListener(this);
        binding.lft2TimeTv.setOnTouchListener(this);

//        binding.lft2NameEd.setOnClickListener(this);
        binding.lft2NameEd.setOnEditorActionListener(this);
        binding.lft2TelEd.setOnEditorActionListener(this);
        binding.lft2PeopleTv.setOnEditorActionListener(this);
        binding.lft2BeizhuEd.setOnEditorActionListener(this);
    }

    public void initData(){

    }

    public void addView(){
        binding.lft21Ll.setVisibility(View.GONE);
        binding.lft22Ll.setVisibility(View.VISIBLE);
        binding.lft2NameEd.setText("");
        binding.lft2TelEd.setText("");
        binding.lft2TimeTv.setText("");
        binding.lft2PeopleTv.setText("");
        binding.lft2SeatTv.setText("");
        binding.lft2BeizhuEd.setText("");
        binding.lft2NameEd.setHint(getResources().getString(R.string.tab3_hint_Contacts));
        binding.lft2TelEd.setHint(getResources().getString(R.string.tab3_hint__ContactsPhone));
        binding.lft2TimeTv.setHint(getResources().getString(R.string.tab3_hint__RemarksTime));
        binding.lft2PeopleTv.setHint(getResources().getString(R.string.tab3_hint__people));
        binding.lft2SeatTv.setHint(getResources().getString(R.string.tab3_hint__table));
        binding.lft2BeizhuEd.setHint(getResources().getString(R.string.tab3_hint__Remarks));
        binding.lft2StatusTv.setVisibility(View.INVISIBLE);
        binding.lft2Status2Tv.setVisibility(View.INVISIBLE);
        binding.lft2StatusLine.setVisibility(View.INVISIBLE);
        binding.lft2Btn1Ll.setVisibility(View.GONE);
        binding.lft2Btn2Ll.setVisibility(View.VISIBLE);
        type = 1;
    }

    public void upDateReserve(){
        Log.d("-------","updateReserve:" +  binding.lft2TimeTv.getText().toString() + "---");
        if(!StrUtil.isInt(binding.lft2PeopleTv.getText().toString())) {
            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_2_txt));
            return;
        }else if(Integer.valueOf(binding.lft2PeopleTv.getText().toString()) > 1000){
            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_2_txt));
            return;
        }

        model.getChange(model.reserveListDetail.get().getId(),
                binding.lft2NameEd.getText().toString(),
                binding.lft2TelEd.getText().toString(),
                binding.lft2TimeTv.getText().toString(),
                binding.lft2PeopleTv.getText().toString(),
                //
                Integer.valueOf(seatId),
                binding.lft2BeizhuEd.getText().toString(),
                status,new Callback(){
                    @Override
                    public void success() {
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_YUYUE_UPDATE_RT));

                    }
                }

        );
    }


    public void addReserve(){

//        if(!StrUtil.isInt(binding.lft2TelEd.getText().toString())) {
//            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_1_txt));
//            return;
//        }

//        if(!StrUtil.isInt(binding.lft2PeopleTv.getText().toString())) {
//            Log.d("------------","1111" + binding.lft2PeopleTv.getText().toString());
//            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_2_txt));
//            return;
//        }

        if(!StrUtil.isInt(binding.lft2PeopleTv.getText().toString())) {
            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_2_txt));
            return;
        }else if(Integer.valueOf(binding.lft2PeopleTv.getText().toString()) > 1000){
            ToastUtil.showToastOne(getContext(),getString(R.string.tab2_hint_2_txt));
            return;
        }

        model.getAddOrder(binding.lft2NameEd.getText().toString(),
                binding.lft2TelEd.getText().toString(),
                binding.lft2TimeTv.getText().toString(),
                binding.lft2PeopleTv.getText().toString(),
                Integer.valueOf(seatId),
                binding.lft2BeizhuEd.getText().toString(),
                new Callback(){
                    @Override
                    public void success() {
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_YUYUE_UPDATE_RT));

                    }
                });
    }

    public void clearLft(){
        binding.lft22Ll.setVisibility(View.GONE);
        binding.lft21Ll.setVisibility(View.VISIBLE);
        binding.lft2Btn1Ll.setVisibility(View.VISIBLE);
        binding.lft2Btn2Ll.setVisibility(View.GONE);
        seatId = "";
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_YUYUE_LFT:
                type = 0;
                bookingId = (int) eventMessage.getData();
//                upDateSeatList();
                model.detailsReserveList(bookingId, new Callback() {
                    @Override
                    public void success() {
                        binding.lft2NameEd.setText(model.reserveListDetail.get().getName());
                        binding.lft2TelEd.setText(model.reserveListDetail.get().getPhone());
                        binding.lft2TimeTv.setText(StrUtil.dateFormat3(model.reserveListDetail.get().getBooked_at()));
                        binding.lft2PeopleTv.setText(model.reserveListDetail.get().getPeople());
                        binding.lft2SeatTv.setText(model.reserveListDetail.get().getSeat().getNumber());
                        binding.lft2BeizhuEd.setText(model.reserveListDetail.get().getNote());
                        switch (model.reserveListDetail.get().getStatus()){
                            case "0":
                                binding.lft2StatusTv.setText(getResources().getString(R.string.tab2_stutas_1));
                                break;

                            case "1":
                                binding.lft2StatusTv.setText(getResources().getString(R.string.tab2_stutas_2));
                                break;

                            case "2":
                                binding.lft2StatusTv.setText(getResources().getString(R.string.tab2_stutas_3));
                                break;
                        }
                        binding.lft2StatusTv.setVisibility(View.VISIBLE);
                        binding.lft2Status2Tv.setVisibility(View.VISIBLE);
                        binding.lft2StatusLine.setVisibility(View.VISIBLE);
                        seatId = model.reserveListDetail.get().getSeat_id() + "";
                        status = "0";
                    }
                });
                binding.lft22Ll.setVisibility(View.VISIBLE);
                binding.lft21Ll.setVisibility(View.GONE);
                binding.lft2Btn1Ll.setVisibility(View.GONE);
                binding.lft2Btn2Ll.setVisibility(View.VISIBLE);
                break;

            case EventCode.EVENT_YUYUE_CLEAR_LFT:
                clearLft();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.4f);
                break;

            case MotionEvent.ACTION_UP:
                v.setAlpha(1f);
                break;

            case MotionEvent.ACTION_CANCEL:
                v.setAlpha(1f);
                break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                addView();
                break;

            case R.id.lft2_ok_btn:
                if(type == 1) {
                    addReserve();
                }else{
                    upDateReserve();
                }
                break;

            case R.id.lft2_no_btn:
                clearLft();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_YUYUE_UPDATE_RT));
                break;

            case R.id.lft2_seat_tv:
                showSeatPopup();
                break;

            case R.id.lft2_status_tv:
                showStatusPopup();
                break;

            case R.id.lft2_time_tv:
                showTimePopup();
                break;

            case R.id.lft2_name_ed:
                binding.lft2NameEd.setSelection(binding.lft2NameEd.getText().toString().length());
                break;
        }
    }






    private void showSeatPopup() {

            model.getSeatList(0, new ffCallback() {
                @Override
                public void success() {

                    DataPickerDialogFragment dataPickerDialogFragment = new DataPickerDialogFragment(model.seatList);
                    dataPickerDialogFragment.setOnDateChooseListener(new DataPickerDialogFragment.OnDateChooseListener() {
                        @Override
                        public void onDateChoose(SeatListEntity.DataBean data) {
                            binding.lft2SeatTv.setText(data.getNumber());
                            seatId = data.getId();
                        }
                    });
                    dataPickerDialogFragment.show(getActivity().getFragmentManager(), "DatePickerDialogFragment");
                }


                @Override
                public void file(String s) {
                }
            });


    }

    private void showStatusPopup() {
        List<String>mlist = new ArrayList<>();
        mlist.add(getResources().getString(R.string.tab2_stutas_1));
        mlist.add(getResources().getString(R.string.tab2_stutas_2));
        mlist.add(getResources().getString(R.string.tab2_stutas_3));
        if(model.seatList != null) {
            StrPickerDialogFragment dataPickerDialogFragment = new StrPickerDialogFragment(mlist);
            dataPickerDialogFragment.setOnDateChooseListener(new StrPickerDialogFragment.OnDateChooseListener() {
                @Override
                public void onDateChoose(String data) {
                    binding.lft2StatusTv.setText(data);
                    Log.d("--------","dataPickerDialogFragment:" + data);
                    if(data.equals(getResources().getString(R.string.tab2_stutas_1))) {
                        status = "0";
                    }else if(data.equals(getResources().getString(R.string.tab2_stutas_2))) {
                        status = "1";
                    }else if(data.equals(getResources().getString(R.string.tab2_stutas_3))){
                        status = "2";
                    }
                }
            });
            dataPickerDialogFragment.show(getActivity().getFragmentManager(), "DatePickerDialogFragment");
        }
    }

    public void showTimePopup(){
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
            @Override
            public void onDateChoose(String day,String hour,String min) {
                binding.lft2TimeTv.setText(day + " "+ hour + ":" + min);
                Log.d("-------","tiempopup:" + day + " "+ hour + ":" + min );

            }
        });
        datePickerDialogFragment.show(getActivity().getFragmentManager(), "DatePickerDialogFragment");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
                v.clearFocus();
                break;
        }

        return false;
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
    public void onDestroy() {
        if(customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();
    }


}
