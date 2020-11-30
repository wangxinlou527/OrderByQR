package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.adapter.CheckOutDetailAdapter;
import com.jydp.obqr.adapter.OrderMessageAdapter;
import com.jydp.obqr.databinding.FramTab5LeftBinding;
import com.jydp.obqr.entity.NotbillrightEntity;
import com.jydp.obqr.entity.OrderItemEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*
2020/10/24 17:04 星期六
作用 ：
*/
public class Tab5Left extends BaseFragment<FramTab5LeftBinding> implements View.OnClickListener, View.OnTouchListener {

    private BillViewModel model;
    private NotbillrightEntity.DataBean notbill;
    private CheckOutDetailAdapter checkOutDetailAdapter;
    private ThreadPool threadPool;
    private int id;
    private String order_id;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab5_left;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);
        initView();
        initData();
    }

    public void initView() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.tab5ListLeft.setLayoutManager(llm);
        checkOutDetailAdapter = new CheckOutDetailAdapter(R.layout.item_order,model.notBillData);
        binding.tab5ListLeft.setAdapter(checkOutDetailAdapter);
        threadPool = ThreadPool.getInstantiation();
        binding.lingshouBtn.setOnClickListener(this);
        binding.dingdanBtn.setOnClickListener(this);
        binding.lingshouBtn.setOnTouchListener(this);
        binding.dingdanBtn.setOnTouchListener(this);

    }
    public void initData() {
        binding.tab5ListLeft.setVisibility(View.INVISIBLE);
        binding.tvTime.setText("");
        binding.tvTableNumber.setText("");
        binding.tvPeopleNumber.setText("");//
        binding.tvSumNumber.setText("");
        binding.tvAllMoney.setText("");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_CHECK_OUT_DETAIL:
                notbill = (NotbillrightEntity.DataBean) eventMessage.getData();
                order_id = notbill.getId();
                model.getOrdersListDetail(order_id, new Callback() {
                    @Override
                    public void success() {
                        if (notbill != null) {
                            binding.tab5ListLeft.setVisibility(View.VISIBLE);
                            binding.tvTime.setText(notbill.getOpened_at());
                            binding.tvTableNumber.setText(notbill.getSeat_number());
                            binding.tvPeopleNumber.setText(notbill.getPeople());//
                            binding.tvSumNumber.setText(String.valueOf(model.notBillData.size()));
                            binding.tvAllMoney.setText("¥" + notbill.getTotal_format());

                            checkOutDetailAdapter.notifyDataSetChanged();
                        }
                    }
                });
                break;

            case EventCode.EVENT_LFT5_CLEAR:
                initData();
                    break;
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dingdan_btn:
//                ISharedPreference.getInstance(App.app).saveTime(1111);
                if (order_id == null) {
                    Toast.makeText(getActivity(), getString(R.string.tab5_hint), Toast.LENGTH_SHORT).show();
                } else {
                    model.getMaidanPrint(order_id, new Callback() {
                        @Override
                        public void success() {
                            btnReceiptPrint(6);
                        }
                    });
                }

                break;

            case R.id.lingshou_btn:
//                Log.d("--------",ISharedPreference.getInstance(App.app).getToken());

                if (order_id == null) {
                    Toast.makeText(getActivity(), getString(R.string.tab5_hint), Toast.LENGTH_SHORT).show();
                } else {
                    model.getLingShouPrint(order_id, new Callback() {
                        @Override
                        public void success() {
                            btnReceiptPrint(5);
                        }
                    });
                }
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

    public void btnReceiptPrint(int i) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, false, i);
        }
    }

}
