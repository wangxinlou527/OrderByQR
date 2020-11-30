package com.jydp.obqr.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.adapter.OrderAdapter;
import com.jydp.obqr.adapter.OrderMessageAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.dailog.ShowIOSDialog;
import com.jydp.obqr.databinding.FramTab3LeftBinding;
import com.jydp.obqr.entity.MessageEntuty;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/*
2020/10/24 17:04 星期六
作用 ：
*/
public class Tab3Left extends BaseFragment<FramTab3LeftBinding> implements View.OnClickListener, View.OnTouchListener {

    private BillViewModel model;
    //    private String getSeatId = "89";//座位id
    private OrderMessageAdapter orderAdapter;
    private MessageEntuty.DataBean messageData;

    //1订单,2服务
    private int type;
    //1同意2不同意
    private int status;
    private int id;
    private ThreadPool threadPool;

    private CustomDialog customDialog;

    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab3_left;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(BillViewModel.class);
        binding.setViewModel(model);
        threadPool = ThreadPool.getInstantiation();
        initView();
        initData();
    }

    public void initView() {
        binding.tongyiBtn.setOnClickListener(this);
        binding.jujueBtn.setOnClickListener(this);
        binding.lft3Print1.setOnClickListener(this);
        binding.lft3Print2.setOnClickListener(this);

        binding.tongyiBtn.setOnTouchListener(this);
        binding.jujueBtn.setOnTouchListener(this);
        binding.lft3Print1.setOnTouchListener(this);
        binding.lft3Print2.setOnTouchListener(this);

        //zhaodan
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.tab3ListLeft.setLayoutManager(llm);
        orderAdapter = new OrderMessageAdapter(R.layout.item_order, model.messOrderDetail);

        binding.tab3ListLeft.setAdapter(orderAdapter);
//        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            }
//        });
    }

    public void initData() {
        binding.tab3Status1Ll.setVisibility(View.GONE);
        binding.tab3Status2Ll.setVisibility(View.GONE);
        binding.tab3ListLeft.setVisibility(View.INVISIBLE);
        binding.rt3TopLl.setVisibility(View.VISIBLE);
        binding.jujueBtn.setVisibility(View.VISIBLE);
//        binding.tab3Status1Ll.setVisibility(View.VISIBLE);
        binding.tvTime.setText("");
        binding.tvTableNumber.setText("");
        binding.tvPeopleNumber.setText("");//
        binding.tvSumNumber.setText("");
        binding.tvAllMoney.setText("");
//        orderAdapter.setmPosition(-1);
//        orderAdapter.notifyDataSetChanged();
        messageData = null;
        customDialog = new CustomDialog(getContext(), 1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_MESSAGE_DATA:
                messageData = (MessageEntuty.DataBean) eventMessage.getData();
                status = messageData.getStatus();
                Log.d("---------", messageData.getType() + "");
                if (messageData.getType() == 1) {

                    type = 1;
                    binding.rt3TopLl.setVisibility(View.VISIBLE);
                    binding.jujueBtn.setVisibility(View.VISIBLE);
                    binding.tab3ListLeft.setVisibility(View.VISIBLE);
                    binding.lft3Print1.setVisibility(View.VISIBLE);
                    binding.lft3Print2.setVisibility(View.VISIBLE);
                    statusShow();
                    dataUpOrder(messageData);
                } else {
                    type = 2;
//                    initData();
                    binding.rt3TopLl.setVisibility(View.INVISIBLE);
                    binding.jujueBtn.setVisibility(View.GONE);
                    binding.tab3ListLeft.setVisibility(View.INVISIBLE);
                    binding.lft3Print1.setVisibility(View.GONE);
                    binding.lft3Print2.setVisibility(View.GONE);

                    statusShow();
                    dataUpOrder2(messageData);
                }

                break;

            case EventCode.EVENT_LFT3_CLEAR:
                initData();
                break;
        }
    }

    public void dataUpOrder(MessageEntuty.DataBean messageData) {

        model.getMsgOrderDetail(messageData.getSeat_id(), messageData.getCart_id(), status, new Callback() {
            @Override
            public void success() {
                if (model.orderMessage.get() != null) {
                    binding.tab3ListLeft.setVisibility(View.VISIBLE);
                    binding.tvTime.setText(model.orderMessage.get().getOpen_at());
                    binding.tvTableNumber.setText(model.orderMessage.get().getNumber());
                    binding.tvPeopleNumber.setText(model.orderMessage.get().getPeople());//
                    binding.tvSumNumber.setText(String.valueOf(model.messOrderDetail.size()));
                    binding.tvAllMoney.setText("¥" + model.orderMessage.get().getTotal_format());
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });
        orderAdapter.setmPosition(-1);
    }


    public void dataUpOrder2(MessageEntuty.DataBean messageData) {

        binding.tab3ListLeft.setVisibility(View.INVISIBLE);
        binding.tvTime.setText("");
        binding.tvTableNumber.setText(messageData.getNumber());
        binding.tvPeopleNumber.setText(messageData.getPeople() + "");//
        binding.tvSumNumber.setText("");
        binding.tvAllMoney.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tongyi_btn:
                status = 1;
                if (messageData == null) {
                    ToastUtil.showToastOne(getContext(), getString(R.string.tab3_btn_3_hint));
                } else {
                    if (type == 1) {
                        checkOrder();
                    } else {
                        serviceBell();
                    }

                }


                break;
            case R.id.jujue_btn:
                if (messageData == null) {
                    ToastUtil.showToastOne(getContext(), getString(R.string.tab3_btn_3_hint));
                } else {
                    ShowIOSDialog showIOSDialog = new ShowIOSDialog();
                    showIOSDialog.show(getContext(), 1, getString(R.string.tab3_btn_hint), new ShowIOSDialog.OnBottomClickListener() {
                        @Override
                        public void positive() {
                            status = 2;
                            if (type == 1) {
                                checkOrder();
                            }
                        }

                        @Override
                        public void negative() {

                        }
                    });
                }

                break;

            case R.id.lft3_print_1:
                qiantingPrint(String.valueOf(messageData.getSeat_id()), messageData.getCart_id());
                break;

            case R.id.lft3_print_2:
                houchuPrint2(String.valueOf(messageData.getSeat_id()), messageData.getCart_id());
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

    public void serviceBell() {

        if (messageData != null) {
            model.getServiceBell(messageData.getSeat_id(), messageData.getTimestamp(), new Callback() {
                @Override
                public void success() {
                    initData();
                    EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_RT_UPDATE));
                }
            });
        }
    }

    public void checkOrder() {
        Log.d("--------","xiadanPrint: " + String.valueOf(messageData.getSeat_id() ) +  ","  +  messageData.getCart_id());
        model.getMessageOrderIs(messageData.getSeat_id(), messageData.getCart_id(), status, new Callback() {
            @Override
            public void success() {

                xiadanPrint(String.valueOf(messageData.getSeat_id()), messageData.getCart_id());
                initData();
                EventBusHelper.post(new EventMessage(EventCode.EVENT_MESSAGE_RT_UPDATE));
                customDialog.show();
                countDownTimer.start();
            }
        });
    }

    public void statusShow() {
        if (status == 1) {
            binding.tab3Status2Ll.setVisibility(View.GONE);
            binding.tab3Status1Ll.setVisibility(View.VISIBLE);

        } else {
            binding.tab3Status1Ll.setVisibility(View.GONE);
            binding.tab3Status2Ll.setVisibility(View.VISIBLE);
        }

    }


    public void btnReceiptPrint(int i) {

        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, false, i);
        }
    }



    public void xiadanPrint(String getSeatId, int cartId) {
        Log.d("--------","xiadanPrint:" + getSeatId  +  ","  +  cartId);
        Log.d("--------","xiadanPrint: 不是整数" + StrUtil.isInt(getSeatId.trim()));


        model.getQianChuPrint(getSeatId, 1, cartId, new Callback() {
            @Override
            public void success() {
                //前厅小票
                btnReceiptPrint(2);

                //后厨小票
                if (model.houchuDetail.get() != null) {
                    if (model.houchuDetail.get().getBackend_info().size() > 0) {
                        for (int i = 0; model.houchuDetail.get().getBackend_info().size() > i; i++) {
                            houchuPrint(model.houchuDetail.get().getBackend_info().get(i).getIp(), 1);
                        }
                    }
                }
            }
        });
    }


    public void qiantingPrint(String getSeatId, int cartId) {
        model.getQianChuPrint(getSeatId, 1, cartId, new Callback() {
            @Override
            public void success() {
                //前厅小票
                btnReceiptPrint(2);

            }
        });
    }

    public void houchuPrint2(String getSeatId, int cartId) {
        model.getQianChuPrint(getSeatId, 1, cartId, new Callback() {
            @Override
            public void success() {

                //后厨小票
                if (model.houchuDetail.get() != null) {
                    if (model.houchuDetail.get().getBackend_info().size() > 0) {
                        for (int i = 0; model.houchuDetail.get().getBackend_info().size() > i; i++) {
                            houchuPrint(model.houchuDetail.get().getBackend_info().get(i).getIp(), 1);
                        }
                    }
                }
            }
        });
    }


    public void houchuPrint(String ip,  int i) {
        Log.d("-------", "Print  houchu ip:" + ip);
        List<String> prints = MainActivity.getPrints();
        for (int n = 0;n < prints.size();n++){
            if(ip.equals(prints.get(n))) {
                Log.d("-------", "Print houchu ip:" + prints.get(n) + "," + (n + 1));
                if(DeviceConnFactoryManager.getDeviceConnFactoryManagers()[n + 1] != null) {
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[n + 1].onConnect(threadPool, n + 1, model, false, ip, i);
                    Log.d("-------", "Print houchu ip 2:" + prints.get(n) + "," + (n + 1));
                }else{
//                    DeviceConnFactoryManager.InitHouchu(model,threadPool,ip,n,i);
                }
            }
        }
    }


    // 倒计时
    // 总时间 24 * 60 * 60 * 1000，间隔 1000s 回调一次 onTick
    CountDownTimer countDownTimer = new CountDownTimer(1000, 500) {
        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if(customDialog.isShowing()) {
                customDialog.cancel();
            }
            // 倒计时结束时的回调
        }
    };

}
