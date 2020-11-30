package com.jydp.obqr.fragment;

/**
 * 以绝望挥剑，着逝者为铠。
 * 一个人可以被毁灭，但绝不可以被打败
 * 饮血的刃，越发空虚
 * 无限接近死亡，更能醒悟生存的真谛
 * 磨砺的不止锋芒，还有灵魂
 * 多管闲事的命运，碎裂与剑士的意志
 * 死亡时：
 * 诅咒，终结于此
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;


import com.chad.library.adapter.base.BaseQuickAdapter;

import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;
import com.html5app.uni_gprinter.GprinterPulg;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.LoginActivity;
import com.jydp.obqr.adapter.CommodityAdapter;
import com.jydp.obqr.adapter.CommodityTabAdapter;
import com.jydp.obqr.adapter.CouponAdapter;
import com.jydp.obqr.adapter.PayTypeAdapter;
import com.jydp.obqr.adapter.SeatBaseAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.dailog.ShowLabelsDialog;
import com.jydp.obqr.dailog.ShowSatffDialog;
import com.jydp.obqr.databinding.FramTab1RightBinding;
import com.jydp.obqr.entity.Ado;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.PayTypeEntity;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.KeyUtil;
import com.jydp.obqr.util.ShowToast;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.view.AutoLineFeedLayoutManager;
import com.jydp.obqr.view.MyLayoutManager;
import com.jydp.obqr.view.WrapContentLinearLayoutManager;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

/*



* 此处应该是传集合吧，数组的长度是固定的，且是不能改变的
* 第一步：把 数组 转化为 集合
 list = Arrays.asList(array);
第二步：向 集合 中添加元素
list.add(index, element);
第三步：将 集合 转化为 数组
 list.toArray(newArray);
*
* */
public class Tab1Right extends BaseFragment<FramTab1RightBinding> implements View.OnClickListener, View.OnTouchListener {
    private RxPermissions permissions;
    private BillViewModel model;
    private String seatId;
    private String discountId;
    private int staffId;
    private int seatStatus;
    private int price;
    private String givePrice;
    private String payType;

    private SeatBaseAdapter seatBaseAdapter;
    private CommodityTabAdapter commodityTabAdapter;
    private CommodityAdapter commodityAdapter;
    private PayTypeAdapter payTypeAdapter;
    private CouponAdapter couponAdapter;
    private int id = 0;
    private ThreadPool threadPool;
    private GroupedGridLayoutManager gridLayoutManager;
    private int moveScor;
    private CustomDialog customDialog;
    private int classes;


    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab1_right;
    }


    @Override
    protected void viewCreate(Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(BillViewModel.class);
        //权限
        permissions = new RxPermissions(getActivity());//
        binding.setViewModel(model);
        threadPool = ThreadPool.getInstantiation();
        initLoading();
        initView();
//        initData();
        onViewClick();

    }

    public void initView() {
        //座位
        binding.rvList.setLayoutManager(new GridLayoutManager(getActivity(), 6));
        seatBaseAdapter = new SeatBaseAdapter(R.layout.item_seat, model.seatList);
        binding.rvList.setAdapter(seatBaseAdapter);
        seatBaseAdapter.setmPotition(-1);
        seatBaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Animation mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_action);
//                view.startAnimation(mAnimation);

                seatId = model.seatList.get(position).getId();
                seatStatus = model.seatList.get(position).getStatus();
                seatBaseAdapter.setmPotition(position);
                EventBus.getDefault().post(new EventMessage(EventCode.EVENT_SEAT_ID, model.seatList.get(position)));
                seatBaseAdapter.notifyDataSetChanged();

            }
        });

        //点菜标签
        WrapContentLinearLayoutManager llm = new WrapContentLinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rgtDiancaiTab.setLayoutManager(llm);
        commodityTabAdapter = new CommodityTabAdapter(R.layout.item_commodity_tab, model.differenGuDian);
        binding.rgtDiancaiTab.setAdapter(commodityTabAdapter);
        commodityTabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                commodityTabAdapter.setmPosition(position);
                commodityTabAdapter.notifyDataSetChanged();

                int position1 = 0;
                for (int i = 0; i < model.differenGuDian.size(); i++) {
                    position1 += model.differenGuDian.get(i).getMenus().size() + 2;
                    if (i >= (position - 1)) {
                        break;
                    }
                    position1 += 1;
                    Log.d("---------position1", position1 + "");
                }

                Log.d("---------position1", position1 + "");
                if (moveScor > position1) {
                    position1 -= 1;
                }
                if (position == 0) {
                    position1 = 0;
                }
                LinearSmoothScroller s1 = new LinearSmoothScroller(getActivity());
                s1.setTargetPosition(position1);
                gridLayoutManager.startSmoothScroll(s1);
                moveScor = position1;
            }
        });

        //优惠券
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rlCouponRv.setLayoutManager(linearLayoutManager);
        couponAdapter = new CouponAdapter(R.layout.item_coupon, model.couponList, getContext());
        couponAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                discountId = model.couponList.get(position).getId();
                showMoney(seatId, discountId);

                couponAdapter.setmPosition(position);
                couponAdapter.notifyDataSetChanged();
            }
        });
        binding.rlCouponRv.setAdapter(couponAdapter);
        binding.rlCouponRv.setVisibility(View.VISIBLE);

        //点菜
        commodityAdapter = new CommodityAdapter(getContext(), model.differenGuDian);
        gridLayoutManager = new GroupedGridLayoutManager(getContext(), 5, commodityAdapter);
        binding.rgtDiancaiMian.setLayoutManager(gridLayoutManager);
        binding.rgtDiancaiMian.setAdapter(commodityAdapter);
        binding.shoukuanBtn.setOnClickListener(this::onClick);
        binding.shoukuanMoney.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        v.clearFocus();
                        KeyUtil.hideInput(getActivity());
                        break;
                }
                return false;
            }
        });

//        binding.ivHard.setText(model.zhaodanHandText);
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.wj_cznr), null, null, null);
//        binding.rvZhekouList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
//        binding.rvGuqingShang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        //支付方式
//        GridLayoutManager gridLayoutManager2 =new GridLayoutManager(getActivity(), 4);
        AutoLineFeedLayoutManager autoLineFeedLayoutManager = new AutoLineFeedLayoutManager();
        binding.rlPayRv.setLayoutManager(autoLineFeedLayoutManager);
        payTypeAdapter = new PayTypeAdapter(getContext(), R.layout.item_pay_type, model.payTypeList);
        payTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (position == 0) {
                    binding.shoukuanMoney.setVisibility(View.VISIBLE);
                } else {
                    binding.shoukuanMoney.setText("");
                    binding.shoukuanMoney.setVisibility(View.GONE);
                }
                payType = model.payTypeList.get(position).getId();
                Log.d("-------", "pay:" + payType);
                payTypeAdapter.setmPosition(position);
                payTypeAdapter.notifyDataSetChanged();

            }
        });

        binding.rlPayRv.setAdapter(payTypeAdapter);


    }

    public void initLoading() {
        customDialog = new CustomDialog(getContext(), 1);
        model.dialogText.observe(this, s -> {
            if (s == null) {
                if (customDialog != null) {
                    customDialog.dismiss();
                }
            } else {
                //我的dialog需要一个标题,所以我需要一个接口方法来获取标题
                // dialog = ProgressDialog.show(LoginActivity.this, s, "请稍等……", false, false);
                Log.d("---------111", (model.seatList.size()) + "," + (model.differenGuDian.size()));
                if (model.seatList.size() == 0 || model.differenGuDian.size() == 0) {
                    customDialog.show();
                }

            }
        });
    }

    public void initData() {
        if (ISharedPreference.getInstance(App.app).getTime() < (System.currentTimeMillis() - model.timeS)) {
            model.upDateToken(new ffCallback() {
                @Override
                public void success() {
                    upDataZhaodanData();
                    DiancaiListUpData();
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            upDataZhaodanData();
            DiancaiListUpData();
        }
    }

    public void onViewClick() {
        binding.fltJiezhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upDataJiezhang();


            }
        });

        binding.fltDiancai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seatId != null && seatStatus == 2) {
                    if (classes != 2) {
                        classes = 2;
                        binding.rvList.setVisibility(View.GONE);
                        binding.rlJiezhangCl.setVisibility(View.GONE);
                        binding.rgtDiancaiLl.setVisibility(View.VISIBLE);
                        binding.ivHard.setText(getResources().getString(R.string.tab1_diancai));
                        binding.ivHard.setCompoundDrawablePadding(17);
                        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.canting), null, null, null);
//                    DiancaiListUpData();
                        EventBus.getDefault().post(new EventMessage(EventCode.EVENT_DIACAI));
                    }

                } else {
                    ToastUtil.showToastOne(getContext(), getResources().getString(R.string.tab1_hint_tv_1));
                }
            }
        });

        binding.fltZhaodan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classes != 1) {
                    classes = 1;
                    upDataZhaodanView();
                }

            }
        });

        binding.fltZhaodan.setOnTouchListener(this);
        binding.fltDiancai.setOnTouchListener(this);
        binding.fltJiezhang.setOnTouchListener(this);
    }

    private BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            Log.d("-----------", position + "");
        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {

        switch (eventMessage.getCode()) {
            case EventCode.EVENT_CLEAR_SEAT:
                initData();
                break;

            case EventCode.EVENT_RT_DATAUP:
                initData();
                binding.rvList.setVisibility(View.VISIBLE);
                binding.rgtDiancaiLl.setVisibility(View.GONE);
                binding.ivHard.setText(getResources().getString(R.string.tab1_zhodan));
                binding.ivHard.setCompoundDrawablePadding(17);
                binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.wj_cznr), null, null, null);
                break;

            case EventCode.EVENT_RT_DATAUP_2:
                classes = 1;
                binding.rvList.setVisibility(View.VISIBLE);
                binding.rgtDiancaiLl.setVisibility(View.GONE);
                binding.ivHard.setText(getResources().getString(R.string.tab1_zhodan));
                binding.ivHard.setCompoundDrawablePadding(17);
                binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.wj_cznr), null, null, null);
                break;

            case EventCode.EVENT_ADD_ORDER:
                Ado ado = (Ado) eventMessage.getData();
                model.differenGuDian.get(ado.getmGroupPosition()).getMenus().get(ado.getmChildPosition()).setStock((Integer.valueOf(model.differenGuDian.get(ado.getmGroupPosition()).getMenus().get(ado.getmChildPosition()).getStock()) - 1) + "");
                commodityAdapter.notifyDataChanged();
                break;

            case EventCode.EVENT_DIACAI_RL:
                DiancaiListUpData();
                break;

            case EventCode.EVENT_TUICAI_2_RT:
                String name = (String) eventMessage.getData();
                for (DifferentEntity.DataBean dataBean : model.differenGuDian) {
                    for (DifferentEntity.DataBean.MenusBean menusBean : dataBean.getMenus()) {
                        if (!menusBean.getStock().equals("") && name.equals(menusBean.getOrder_menu_translate().getName())) {
                            menusBean.setStock((Integer.valueOf(menusBean.getStock()) + 1) + "");
                            commodityAdapter.notifyDataChanged();
                        }
                    }
                }

                break;

            case EventCode.EVENT_UPDATE_SEAT_STATUS:
                if (seatStatus == 0) {
                    seatStatus = 1;
                }
                model.seatList.get(seatBaseAdapter.getmPotition()).setStatus(seatStatus);

                seatBaseAdapter.notifyDataSetChanged();
                break;

        }
    }

    public void DiancaiListUpData() {
        model.getDifferentList(new Callback() {
            @Override
            public void success() {
                commodityTabAdapter.notifyDataSetChanged();
                commodityAdapter.notifyDataChanged();

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoukuan_btn:

                givePrice = binding.shoukuanMoney.getText().toString();
                binding.shoukuanMoney.clearFocus();
                KeyUtil.hideInput(getActivity());
//                Log.d("---------shoukan",seatId +"," + discountId+ ","+  "1" + "," +  price + "," +  27 + "," +  givePrice);
                if (payType.equals("1")) {
                    if (!givePrice.equals("") && givePrice != null && StrUtil.isInt(givePrice)) {
                        if (Integer.valueOf(givePrice) >= price) {

                            model.getStaffList(new Callback() {
                                @Override
                                public void success() {
                                    staffDaiLog();
                                }
                            });

                        } else {
                            ToastUtil.showToastOne(getContext(), getResources().getString(R.string.tab1_hint_tv_2));
                        }
                    } else {
                        ToastUtil.showToastOne(getContext(), getResources().getString(R.string.tab1_hint_tv_2));
                    }
                } else {
                    givePrice = null;
                    model.getStaffList(new Callback() {
                        @Override
                        public void success() {
                            staffDaiLog();
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

    public void upDataZhaodanView() {
        binding.rvList.setVisibility(View.VISIBLE);
        binding.rgtDiancaiLl.setVisibility(View.GONE);
        binding.rlJiezhangCl.setVisibility(View.GONE);
        binding.ivHard.setText(getResources().getString(R.string.tab1_zhodan));
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.wj_cznr), null, null, null);
        if (seatId != null && seatStatus == 2) {

            EventBus.getDefault().post(new EventMessage(EventCode.EVENT_ZHAODAO));
        } else {
            ToastUtil.showToastOne(getContext(), getResources().getString(R.string.tab1_hint_tv_1));

        }

    }

    public void upDataZhaodanView2() {
        binding.rvList.setVisibility(View.VISIBLE);
        binding.rgtDiancaiLl.setVisibility(View.GONE);
        binding.rlJiezhangCl.setVisibility(View.GONE);
        binding.ivHard.setText(getResources().getString(R.string.tab1_zhodan));
        binding.ivHard.setCompoundDrawablePadding(17);
        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.wj_cznr), null, null, null);

    }

    public void upDataJiezhang() {
        if (seatId != null && seatStatus == 2) {
            model.getSprice(seatId, "", new Callback() {
                @Override
                public void success() {
                    if (classes != 3) {


                        binding.rvList.setVisibility(View.GONE);
                        binding.rgtDiancaiLl.setVisibility(View.GONE);
                        binding.rlJiezhangCl.setVisibility(View.VISIBLE);
                        binding.ivHard.setText(getResources().getString(R.string.tab1_jiezhang));
                        binding.ivHard.setCompoundDrawablePadding(17);
                        binding.ivHard.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.right_bill), null, null, null);
                        payType = "1";
                        discountId = "";
//                        price = 0;
                        givePrice = "";
                        staffId = 0;
                        binding.shoukuanMoney.setText("");
                        binding.shoukuanMoney.setVisibility(View.VISIBLE);
                        couponAdapter.setmPosition(0);
                        payTypeAdapter.setmPosition(0);

                        EventBus.getDefault().post(new EventMessage(EventCode.EVENT_ZHAODAO));


                        binding.rlMoneyTv2.setText("¥" + model.sprice.get().getTotal_format());
                        binding.rlMoneyTv4.setText("¥" + model.sprice.get().getTax_fee_format());
                        binding.rlMoneyTv6.setText("¥" + model.sprice.get().getPrice_format());
                        price = model.sprice.get().getPrice();


                        model.getCoupon(new Callback() {
                            @Override
                            public void success() {
                                couponAdapter.notifyDataSetChanged();

                            }
                        });

                        model.getPayTypeList(new Callback() {
                            @Override
                            public void success() {
                                payTypeAdapter.notifyDataSetChanged();
                            }
                        });


                        classes = 3;
                    }

                }
            });
        } else {
            ToastUtil.showToastOne(getContext(), getResources().getString(R.string.tab1_hint_tv_1));
        }
    }

    public void upDataZhaodanData() {

        model.getSeatList(0, new ffCallback() {
            @Override
            public void success() {
                seatId = null;
                seatStatus = -1;
                seatBaseAdapter.setmPotition(-1);
                seatBaseAdapter.notifyDataSetChanged();
//                upDataZhaodanView();
            }


            @Override
            public void file(String s) {
            }
        });
    }

    public void showMoney(String seatId, String discountId) {
        model.getSprice(seatId, discountId, new Callback() {
            @Override
            public void success() {
                binding.rlMoneyTv2.setText("¥" + model.sprice.get().getTotal_format());
                binding.rlMoneyTv4.setText("¥" + model.sprice.get().getTax_fee_format());
                binding.rlMoneyTv6.setText("¥" + model.sprice.get().getPrice_format());
                price = model.sprice.get().getPrice();
            }
        });

    }

    public void staffDaiLog() {

        ShowSatffDialog showSatffDialog = new ShowSatffDialog();
        showSatffDialog.show(getContext(), 1, "担当着", model.staffList, new ShowSatffDialog.OnBottomClickListener() {
            @Override
            public void positive(StaffEntity.DataBean data) {
                staffId = data.getId();
                Log.d("--------", "showSatffDialog:" + seatId + "," + discountId + "," + payType + "," + price + "," + staffId + "," + givePrice);
                model.getMaiDan(seatId, discountId, payType, String.valueOf(price), staffId, givePrice, new Callback() {
                    @Override
                    public void success() {
                        model.getMaidanPrint(model.maidan.get().getOrder_id(), new Callback() {
                            @Override
                            public void success() {
                                if (payType.equals("1")) {
                                    btnReceiptPrint(6, true);
                                    Log.d("-------", "open-----------");
                                } else {
                                    btnReceiptPrint(6, false);
                                }

                            }
                        });
//                            EventBusHelper.post(new EventMessage(EventCode.EVENT_LET_MAIDAN_PRINT));
                        classes = 1;
                        seatId = null;
                        upDataZhaodanData();
                        upDataZhaodanView2();
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_LFT1_CLEAR));
                    }
                });
            }

            @Override
            public void negative() {

            }
        });
    }

    public void btnReceiptPrint(int i, boolean n) {
        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, n, i);
        } else {
            DeviceConnFactoryManager.InitQianting(model, threadPool, i);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroy() {
        if (customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();

    }
}
