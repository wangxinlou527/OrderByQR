package com.jydp.obqr.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jydp.obqr.App;
import com.jydp.obqr.R;
import com.jydp.obqr.activity.MainActivity;
import com.jydp.obqr.adapter.OrderAdapter;
import com.jydp.obqr.dailog.CustomDialog;
import com.jydp.obqr.dailog.ShowIOSDialog;
import com.jydp.obqr.databinding.FramTab1LeftBinding;
import com.jydp.obqr.entity.HouchuMenus;
import com.jydp.obqr.entity.QrcodeEntity;
import com.jydp.obqr.entity.menus;
import com.jydp.obqr.entity.NowSeatEntity;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.printer.DeviceConnFactoryManager;
import com.jydp.obqr.printer.PrinterCommand;
import com.jydp.obqr.printer.ThreadPool;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.StrUtil;
import com.jydp.obqr.util.ToastUtil;
import com.jydp.obqr.viewmodel.BillViewModel;
import com.jydp.obqr.viewmodel.Callback;
import com.jydp.obqr.viewmodel.ffCallback;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tools.command.EscCommand;
import com.tools.command.LabelCommand;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import butterknife.internal.Utils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.jydp.obqr.printer.DeviceConnFactoryManager.idList;


public class Tab1Left extends BaseFragment<FramTab1LeftBinding> implements View.OnClickListener, View.OnTouchListener {
    private RxPermissions permissions;
    private BillViewModel model;

    //order
    private String getSeatId;//座位id
    private int menuId;
    private String count;
    private int cancelOrder;
    private ArrayList<String> attributeList;
    private int orderStatus;


    //diandan
    private List<NowSeatEntity.DataBean> dianDanList = new ArrayList<NowSeatEntity.DataBean>();

    //view
    private OrderAdapter orderAdapter;
    private OrderAdapter orderAdapter2;
    private TextView chedanBtn;
    private TextView kaitaiBtn;
    private TextView tuicaiBtn;
    private ThreadPool threadPool;
    //printer
    private int id = 0;
    private boolean printAble = true;
    private boolean openboX;
    private static List<String> ipList = new ArrayList<>();
    private CustomDialog customDialog;


    @Override
    public int getContentLayoutId() {
        return R.layout.fram_tab1_left;
    }

    @Override
    protected void viewCreate(Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(BillViewModel.class);
        //权限
        permissions = new RxPermissions(getActivity());
        binding.setViewModel(model);
        binding.tab1ListLeft.setVisibility(View.VISIBLE);
//        binding.tab1ListLeftGuqing.setVisibility(View.GONE);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       // initLoading();
        intiView();
        initDate();//


    }


    private void intiView() {

        //zhaodan
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.tab1ListLeft.setLayoutManager(llm);
        orderAdapter = new OrderAdapter(R.layout.item_order, model.nowSeatDataList);
        orderAdapter.setmPosition(-1);
        binding.tab1ListLeft.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                menuId = 0;
                attributeList = null;
                count = "";

                orderAdapter.setmPosition(position);
                menuId = model.nowSeatDataList.get(position).getMenu_id();
                Log.d("-------", "menuId:" + menuId);
                count = model.nowSeatDataList.get(position).getCount();
                attributeList = (ArrayList<String>) model.nowSeatDataList.get(position).getAttribute_ids();
                orderAdapter.notifyDataSetChanged();
            }
        });

        //
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        binding.tab2ListLeft.setLayoutManager(llm2);
        orderAdapter2 = new OrderAdapter(R.layout.item_order, dianDanList);
        orderAdapter2.setmPosition(-1);
        binding.tab2ListLeft.setAdapter(orderAdapter2);
        orderAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                cancelOrder = position;
                orderAdapter2.setmPosition(position);
                orderAdapter2.notifyDataSetChanged();
            }
        });


        binding.xiadanBtn.setOnClickListener(this);
        binding.qingkongBtn.setOnClickListener(this);
        binding.tuicai2Btn.setOnClickListener(this);
        binding.chedanBtn.setOnClickListener(this);
        binding.kaitaiBtn.setOnClickListener(this);
        binding.tuicaiBtn.setOnClickListener(this);
        binding.dayinBtn.setOnClickListener(this);
        binding.dayin2Btn.setOnClickListener(this);
        binding.dayin3Btn.setOnClickListener(this);
        binding.qrBtn.setOnClickListener(this);
        binding.aaBtn.setOnClickListener(this);

        binding.xiadanBtn.setOnTouchListener(this);
        binding.qingkongBtn.setOnTouchListener(this);
        binding.tuicai2Btn.setOnTouchListener(this);
        binding.chedanBtn.setOnTouchListener(this);
        binding.kaitaiBtn.setOnTouchListener(this);
        binding.tuicaiBtn.setOnTouchListener(this);
        binding.dayinBtn.setOnTouchListener(this);
        binding.dayin2Btn.setOnTouchListener(this);
        binding.dayin3Btn.setOnTouchListener(this);
        binding.qrBtn.setOnTouchListener(this);
        binding.aaBtn.setOnTouchListener(this);

        customDialog = new CustomDialog(getContext(), 1);
    }


    private void initDate() {
        threadPool = ThreadPool.getInstantiation();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EventMessage eventMessage) {
        switch (eventMessage.getCode()) {
            case EventCode.EVENT_SEAT_ID:
                SeatListEntity.DataBean dataBean = (SeatListEntity.DataBean) eventMessage.getData();
                getSeatId = dataBean.getId();
                orderStatus = dataBean.getStatus();
                binding.tab1ListLeft.setVisibility(View.VISIBLE);
                Log.d("-------",orderStatus + "");
                dataUpOrder();
//                getQR();
                break;

            case EventCode.EVENT_DIACAI:
                dianDanList.clear();
                binding.tvSumNumber.setText("0");
                binding.tvAllMoney.setText("¥ 0");
                binding.tab1ListLeft.setVisibility(View.GONE);
                binding.tab2ListLeft.setVisibility(View.VISIBLE);
                binding.tab1DaicaiLl.setVisibility(View.VISIBLE);
                binding.tab1Status0Ll.setVisibility(View.GONE);
                binding.tab1Status2Ll.setVisibility(View.GONE);
//                getQR();
                break;

            case EventCode.EVENT_ZHAODAO:
//                binding.tvSumNumber.setText(String.valueOf(model.nowSeatDataList.size()));
//                binding.tvAllMoney.setText("¥" + model.nowSeatData.get().getTotal_format());
                binding.tab1ListLeft.setVisibility(View.VISIBLE);
                binding.tab2ListLeft.setVisibility(View.GONE);
                binding.tab1DaicaiLl.setVisibility(View.GONE);
                dataUpOrder();
//                getQR();
                break;

            case EventCode.EVENT_DIACAI_LFT:
                boolean n = false;

                NowSeatEntity.DataBean dataBean2 = (NowSeatEntity.DataBean) eventMessage.getData();
                Log.d("-----------", "isSame:" + dataBean2.toString());
                for (int i = 0; dianDanList.size() > i; i++) {
                    if (isSame(dianDanList.get(i), dataBean2)) {
                        dianDanList.get(i).setCount((Integer.valueOf(dianDanList.get(i).getCount()) + 1) + "");
                        n = true;
                    }

                }
                if (!n) {
                    dianDanList.add(dataBean2);
                }
                orderAdapter2.notifyDataSetChanged();
                dianCaiUpData();
                break;

            case EventCode.EVENT_DIACAI_2_LFT:
                boolean n2 = false;
                NowSeatEntity.DataBean dataBean3 = (NowSeatEntity.DataBean) eventMessage.getData();
                for (int i = 0; dianDanList.size() > i; i++) {
                    if (dianDanList.get(i).getName().equals(dataBean3.getName())) {
                        dianDanList.get(i).setCount((Integer.valueOf(dianDanList.get(i).getCount()) + 1) + "");
                        n2 = true;
                    }
                }
                if (!n2) {
                    dianDanList.add(dataBean3);
                }
                orderAdapter2.notifyDataSetChanged();
                dianCaiUpData();
                break;

            case EventCode.EVENT_DAYIN_QR:
//                sendReceiptWithResponse2();//打印
                break;

            case EventCode.EVENT_LFT1_CLEAR:
                binding.tab1Status0Ll.setVisibility(View.VISIBLE);
                binding.tab1Status2Ll.setVisibility(View.GONE);
                binding.tvTime.setText("");
                binding.tvTableNumber.setText("");
                binding.tvPeopleNumber.setText("");//
                binding.tvSumNumber.setText("");
                binding.tvAllMoney.setText("");
                model.nowSeatDataList.clear();
                orderAdapter.notifyDataSetChanged();
                break;

        }
    }

    public void initLoading(){

        model.dialogText.observe(this, s -> {
            if (s == null) {
                if (customDialog != null) {
                    customDialog.dismiss();
                }
            } else {
                //我的dialog需要一个标题,所以我需要一个接口方法来获取标题
                // dialog = ProgressDialog.show(LoginActivity.this, s, "请稍等……", false, false);
                customDialog.show();
            }
        });
    }


    private void tipDialog2() {
        ShowIOSDialog showIOSDialog = new ShowIOSDialog();
        showIOSDialog.show(getActivity(), 1, getActivity().getString(R.string.is_chedan), new ShowIOSDialog.OnBottomClickListener() {
            @Override
            public void positive() {
                model.getClearSate(getSeatId, new Callback() {
                    @Override
                    public void success() {
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_CLEAR_SEAT));//发消息给右边告诉你我要清空桌子了
//                        dataUpOrder();
                        clearLeft();
                    }
                });
            }

            @Override
            public void negative() {

            }
        });

    }

    private void tipDialog() {
        ShowIOSDialog showIOSDialog = new ShowIOSDialog();

        showIOSDialog.show(getActivity(), 1, getActivity().getString(R.string.is_tuicai), new ShowIOSDialog.OnBottomClickListener() {
            @Override
            public void positive() {
                Log.d("-----------", "Return:" + getSeatId + "," + menuId + "," + (attributeList != null));
                for (String s : attributeList) {
                    Log.d("-----------", "Return attributeList:" + s);
                }
                ArrayList listparam = new ArrayList<>();
                listparam.addAll(attributeList);
                int paramId = menuId;
                String mCount = count;

                if (attributeList != null) {
                    if (attributeList.size() != 0) {

                        if (attributeList.size() == 1 && attributeList.get(0).equals("none")) {
                            model.getReturn02(getSeatId, menuId, new Callback() {
                                @Override
                                public void success() {
                                    dataUpOrder();
                                    model.getRejected2(paramId, getSeatId, "1", new Callback() {
                                        @Override
                                        public void success() {

                                            ShowIOSDialog showIOSDialog = new ShowIOSDialog();
                                            showIOSDialog.show(getActivity(), 1, getActivity().getString(R.string.is_kaipiao), new ShowIOSDialog.OnBottomClickListener() {
                                                @Override
                                                public void positive() {
//                                                    btnReceiptPrint(7);

                                                    houchuPrint(model.RejectedDetail.get().getPrinter().getIp(), 2);
                                                }

                                                @Override
                                                public void negative() {

                                                }
                                            });

                                        }
                                    });

                                }
                            });

                        } else {
                            model.getReturn(getSeatId, menuId, attributeList, new Callback() {

                                @Override
                                public void success() {
                                    dataUpOrder();
                                    String str4 = null;

                                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                        str4 = String.join(",", listparam);
                                        Log.d("-----------", "Return:" + getSeatId + "," + paramId + "," + str4);
                                    }
                                    model.getRejected(paramId, getSeatId, "1", str4, new Callback() {
                                        @Override
                                        public void success() {

                                            ShowIOSDialog showIOSDialog = new ShowIOSDialog();
                                            showIOSDialog.show(getActivity(), 1, getActivity().getString(R.string.is_kaipiao), new ShowIOSDialog.OnBottomClickListener() {
                                                @Override
                                                public void positive() {
                                                    houchuPrint(model.RejectedDetail.get().getPrinter().getIp(), 2);;
                                                }

                                                @Override
                                                public void negative() {

                                                }
                                            });

                                        }
                                    });
                                }
                            });
                        }
                    }
                } else {
                    model.getReturn02(getSeatId, menuId, new Callback() {
                        @Override
                        public void success() {
                            dataUpOrder();
                        }
                    });
                }

            }
            @Override
            public void negative() {

            }
        });

    }

    private void showEditDialog() {
        final EditText editText = new EditText(getActivity());
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(R.mipmap.icon_start);
        builder.setTitle(R.string.tab3_hint__people);
//    builder.setMessage("这是一个编辑对话框");
        builder.setView(editText);
        builder.setPositiveButton(getString(R.string.str_confirm_2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HideKeyboard(editText);
                if(!StrUtil.isInt(editText.getText().toString())) {
                    ToastUtil.showToastOne(getActivity(),getString(R.string.tab2_hint_2_txt));
                    return;
                }
                int input = Integer.parseInt(editText.getText().toString());
                HideKeyboard(editText);

                model.requestCartsSeat(Integer.parseInt(getSeatId), input, new Callback() {
                    @Override
                    public void success() {
                        model.getSeatList(0, new ffCallback() {
                            @Override
                            public void success() {
                                EventBusHelper.post(new EventMessage(EventCode.EVENT_CLEAR_SEAT));//发消息给右边告诉你我要清空桌子了

                            }

                            @Override
                            public void file(String s) {

                            }

                        });
                    }
                });

//                Toast.makeText(getActivity(), "此时应该访问接口（具体几个看需求）右边状态变化，右边数据变化", Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton(getString(R.string.cancel_2), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HideKeyboard(editText);
            }
        });
        builder.create().show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dayin_btn:
                Log.d("------------", "------dayin1---");
                getQR();
                break;

            case R.id.dayin_2_btn:
                Log.d("------------", "------dayin2---");
                getQR();
                break;

            case R.id.qr_btn:
                Log.d("------------", "------dayin3---");
                getQR();
                break;

            case R.id.dayin_3_btn:
                model.getFeiHouChuPrint(getSeatId, 2, new Callback() {
                    @Override
                    public void success() {
//                        btnPrintHouChuOrder();//打印的数据
                        btnReceiptPrint(2);
                    }
                });
                break;

            case R.id.chedan_btn:
                Log.d("------------", "------chedan---");
                tipDialog2();
                break;

            case R.id.kaitai_btn:
                showEditDialog();
                break;

            case R.id.tuicai_btn:
                if (menuId != 0) {
                    tipDialog();
                } else {
                    ToastUtil.showToastOne(getContext(),getResources().getString(R.string.tab1_hint_tv_3));
                }
                break;

            case R.id.xiadan_btn:
                ArrayList<menus> menuList = new ArrayList<>();
                ArrayList<HouchuMenus> houchuMenusArrayList = new ArrayList<>();
                for (NowSeatEntity.DataBean dataBean : dianDanList) {
                    menus menu = new menus();
                    menu.setMenu_id(dataBean.getMenu_id());
                    menu.setCount(dataBean.getCount());
                    menu.setAttribute_ids(dataBean.getAttribute_ids());
                    menuList.add(menu);

                    HouchuMenus houchuMenus = new HouchuMenus();
                    houchuMenus.setName(dataBean.getName());
                    houchuMenus.setCount(dataBean.getCount());
                    if (dataBean.getAttribute() != null && !dataBean.getAttribute().trim().equals("")) {
                        houchuMenus.setAttribute(dataBean.getAttribute());
                    }
                    houchuMenus.setNumber(model.nowSeatData.get().getNumber());
                    houchuMenus.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    houchuMenus.setPeople(model.nowSeatData.get().getPeople());
                    houchuMenusArrayList.add(houchuMenus);
                    Log.d("-------", "houchuMenus:" + houchuMenus.toString());
                }
                model.houchuMenusList.clear();
                model.houchuMenusList.addAll(houchuMenusArrayList);

                if (menuList.size() > 0) {
                    model.getXiadan(StrUtil.getCreatOrderBody(getSeatId, menuList), new Callback() {

                        @Override
                        public void success() {
                            dianDanList.clear();
                            xiadanPrint();
                            EventBusHelper.post(new EventMessage(EventCode.EVENT_RT_DATAUP_2));
                            clearLeft();
                            binding.tab1ListLeft.setVisibility(View.VISIBLE);
                            orderStatus = 2;
                            dataUpOrder();
//                            clearLeft();
                        }
                    });
                }

                break;

            case R.id.qingkong_btn:
                ShowIOSDialog showIOSDialog = new ShowIOSDialog();

                showIOSDialog.show(getActivity(), 1, getActivity().getString(R.string.is_qingkong), new ShowIOSDialog.OnBottomClickListener() {
                    @Override
                    public void positive() {
                        dianDanList.clear();
                        orderAdapter2.setmPosition(-1);
                        orderAdapter2.notifyDataSetChanged();
                        dianCaiUpData();
                        EventBusHelper.post(new EventMessage(EventCode.EVENT_DIACAI_RL));
                    }

                    @Override
                    public void negative() {

                    }
                });


                break;

            case R.id.tuicai_2_btn:

                if (orderAdapter2.getmPosition() != -1) {
                    EventBusHelper.post(new EventMessage(EventCode.EVENT_TUICAI_2_RT, dianDanList.get(cancelOrder).getName()));
                    if (Integer.valueOf(dianDanList.get(cancelOrder).getCount()) > 1) {
                        dianDanList.get(cancelOrder).setCount((Integer.valueOf(dianDanList.get(cancelOrder).getCount()) - 1) + "");
                        orderAdapter2.notifyDataSetChanged();
                        dianCaiUpData();

                    } else {
                        dianDanList.remove(cancelOrder);
                        orderAdapter2.setmPosition(-1);
                        orderAdapter2.notifyDataSetChanged();
                        dianCaiUpData();
                    }
                } else {
                    ToastUtil.showToastOne(getContext(),getResources().getString(R.string.tab1_hint_tv_3));
                }

                break;

            case R.id.aa_btn:

                model.getAAPrint(getSeatId, new Callback() {
                    @Override
                    public void success() {
                        btnReceiptPrint(10);
                    }
                });

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


    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }

    public void dataUpOrder() {
        menuId = 0;
        attributeList = null;

        switch (orderStatus) {
            //没人入座
            case 0:
                binding.tab1Status0Ll.setVisibility(View.VISIBLE);
                binding.tab1Status2Ll.setVisibility(View.GONE);
                break;
            //已打印二维码
            case 1:
                binding.tab1Status0Ll.setVisibility(View.VISIBLE);
                binding.tab1Status2Ll.setVisibility(View.GONE);
                break;
            //有人入座
            case 2:
                binding.tab1Status0Ll.setVisibility(View.GONE);
                binding.tab1Status2Ll.setVisibility(View.VISIBLE);
                break;

            case 3:
                binding.tab1Status0Ll.setVisibility(View.GONE);
                binding.tab1Status2Ll.setVisibility(View.GONE);
                break;

        }

        model.getNowSeat(getSeatId, new Callback() {
            @Override
            public void success() {
                if (model.nowSeatData.get() != null) {
                    binding.tvTime.setText(model.nowSeatData.get().getOpen_at());
                    binding.tvTableNumber.setText(model.nowSeatData.get().getNumber());
                    binding.tvPeopleNumber.setText(model.nowSeatData.get().getPeople());//
                    binding.tvSumNumber.setText(String.valueOf(model.nowSeatDataList.size()));
                    binding.tvAllMoney.setText("¥" + model.nowSeatData.get().getTotal_format());
                    orderAdapter.notifyDataSetChanged();
                }

            }
        });
        orderAdapter.setmPosition(-1);
    }

    public void clearLeft() {

        binding.tab1Status0Ll.setVisibility(View.GONE);
        binding.tab1Status2Ll.setVisibility(View.GONE);
        binding.tab1DaicaiLl.setVisibility(View.GONE);
        binding.tvTime.setText("");
        binding.tvTableNumber.setText("");
        binding.tvPeopleNumber.setText("");//
        binding.tvSumNumber.setText("");
        binding.tvAllMoney.setText("");
        binding.tab1ListLeft.setVisibility(View.INVISIBLE);
        binding.tab2ListLeft.setVisibility(View.GONE);
    }


    public boolean isSame(NowSeatEntity.DataBean data1, NowSeatEntity.DataBean data2) {

        if (data1.getName().equals(data2.getName()) && data1.getPrice().equals(data2.getPrice()) && data1.getAttribute_ids().equals(data2.getAttribute_ids())) {
            return true;
        } else {
            return false;
        }

    }

    public void dianCaiUpData() {
        int diancaiTotle = 0;
        binding.tvSumNumber.setText(String.valueOf(dianDanList.size()));
        for (NowSeatEntity.DataBean data : dianDanList) {
            diancaiTotle += (Integer.valueOf(data.getPrice()) * Integer.valueOf(data.getCount()));
        }
        binding.tvAllMoney.setText("¥" + diancaiTotle);
    }

    public void getQR() {
        customDialog.show();
        if(!customDialog.isShowing()) {
            customDialog.show();
        }
        countDownTimer.start();
            model.requestSeatQrcode(getSeatId, new Callback() {
                @Override
                public void success() {
                    if (threadPool.getPoolSize() == 0) {
                        btnReceiptPrint(1);
                    }

                }
            });


    }


    public void btnReceiptPrint(int i) {

        if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
            Log.d("---------", "Print0----DeviceConnFactoryManagers != null");
            Log.d("---------", "Print0-----------" + DeviceConnFactoryManager.getDeviceConnFactoryManagers()[0].getConnState());
            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].onConnect(threadPool, model, false, i);
        }else{
//            DeviceConnFactoryManager.InitQianting(model,threadPool,i);
           Log.d("---------", "Print0----DeviceConnFactoryManagers()= null");
//            ToastUtil.showToastOne(getContext(),"未找到打印机");
        }
    }





    public void xiadanPrint() {

        Log.d("------", "xiadanPrint  SeatId:" + getSeatId + ",Cart_id:" + model.xiadan.get().getCart_id());
        model.getQianChuPrint(getSeatId, 1, model.xiadan.get().getCart_id(), new Callback() {
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
 //                   DeviceConnFactoryManager.InitHouchu(model,threadPool,ip,n,i);
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

    @Override
    public void onDestroy() {
        if(customDialog != null) {
            customDialog.dismiss();
        }
        super.onDestroy();
    }

}

