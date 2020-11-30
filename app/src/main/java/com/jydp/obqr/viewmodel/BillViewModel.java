package com.jydp.obqr.viewmodel;

import android.app.Application;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.Gson;
import com.jydp.obqr.entity.AAPrintEntity;
import com.jydp.obqr.entity.AddOrderEntity;
import com.jydp.obqr.entity.ChangeEntity;
import com.jydp.obqr.entity.CouponEntity;
import com.jydp.obqr.entity.DataEntity;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.GuQingEntity;
import com.jydp.obqr.entity.HouChuPrintEntity;
import com.jydp.obqr.entity.HouchuMenus;
import com.jydp.obqr.entity.LingShouEntity;
import com.jydp.obqr.entity.LoginEntity;
import com.jydp.obqr.entity.MaiDanEntity;
import com.jydp.obqr.entity.MessageEntuty;
import com.jydp.obqr.entity.MoneyPrintEntity;
import com.jydp.obqr.entity.NotbillrightEntity;
import com.jydp.obqr.entity.OrderItemEntity;
import com.jydp.obqr.entity.OrderMessageEntity;
import com.jydp.obqr.entity.PayTypeEntity;
import com.jydp.obqr.entity.PrintChangeEntity;
import com.jydp.obqr.entity.PrintDetailEntity;
import com.jydp.obqr.entity.PrintListEntity;
import com.jydp.obqr.entity.PrintMaidanlEntity;
import com.jydp.obqr.entity.PutMoneyEntity;
import com.jydp.obqr.entity.QrcodeEntity;
import com.jydp.obqr.entity.RejectedEntity;
import com.jydp.obqr.entity.ReserveDetailEntity;
import com.jydp.obqr.entity.ReserveListEntity;
import com.jydp.obqr.entity.SpriceEntity;
import com.jydp.obqr.entity.StaffEntity;
import com.jydp.obqr.entity.TakeMoney;
import com.jydp.obqr.entity.menus;
import com.jydp.obqr.entity.NowSeatEntity;
import com.jydp.obqr.entity.SeatListEntity;
import com.jydp.obqr.entity.T2Entuty;
import com.jydp.obqr.entity.XiadanEntity;
import com.jydp.obqr.eventbus.EventBusHelper;
import com.jydp.obqr.eventbus.EventCode;
import com.jydp.obqr.eventbus.EventMessage;
import com.jydp.obqr.netnavigator.NetNavigator;
import com.jydp.obqr.netnavigator.NetNavigatorWrapper;
import com.jydp.obqr.netnavigator.SimpleNetNavigator;
import com.jydp.obqr.source.BillDataSource;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.RetrofitUtil;
import com.jydp.obqr.util.RxUtil;
import com.jydp.obqr.util.SingleLiveEvent;
import com.jydp.obqr.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * 账单viewmodel
 */
public class BillViewModel extends AndroidViewModel {

    public SingleLiveEvent<String> snackbarText = new SingleLiveEvent<>();//他跟Toast很像，甚至连使用方式都很像，所以看一遍也就会了。,有弹框提示
    public SingleLiveEvent<String> dialogText = new SingleLiveEvent<>();
    //    public String dailog = "请选择菜品";
//    public String hintText = "请选择桌位";
//    public String hint2Text = "请输入正确数值";
//    public String diancaiHandText = "点菜";
//    public String zhaodanHandText = "客单";
//    public String jiezhangHandText = "结账";
//    public String xiadanTxt = "下单";
//    public String qingkongTxt = "清空";
//    public String tuicaiTxt = "退菜";
    //token有效毫秒数
    public static final int timeS = 3500000;

    public List<HouchuMenus> houchuMenusList = new ArrayList<>();


    public BillViewModel(@NonNull Application application) {
        super(application);

    }


    //座位列表Retrofit
    public ObservableArrayList<SeatListEntity.DataBean> seatList = new ObservableArrayList<>();
    public ObservableField<SeatListEntity> seatDetail = new ObservableField<>();
    //获取当前卓的菜品列表
    public ObservableArrayList<NowSeatEntity.DataBean> nowSeatDataList = new ObservableArrayList<>();
    public ObservableField<NowSeatEntity> nowSeatData = new ObservableField<>();


    //获取所有桌子列表类型 0:获取所有座位 1：获取有人坐的座位 默认是0
    public void getSeatList(int type, ffCallback ffcallback) {
        Log.d("---------1", ISharedPreference.getInstance(getApplication()).getTime() + "");
        Log.d("---------2", (System.currentTimeMillis() - timeS) + "");
        Log.d("---------3", ISharedPreference.getInstance(getApplication()).getToken());
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getSeatList2(type, ffcallback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getSeatList2(type, ffcallback);
        }

    }


    //获取当前桌的订单菜品列表
    public void getNowSeat(String seat_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getNowSeat2(seat_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getNowSeat2(seat_id, callback);
        }
    }


    //清空当前桌子
    public void getClearSate(String seat_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getClearSate2(seat_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getClearSate2(seat_id, callback);
        }
    }

    //帮顾客开台
    public void requestCartsSeat(int seat_id, int count, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    requestCartsSeat2(seat_id, count, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            requestCartsSeat2(seat_id, count, callback);
        }
    }

    //退菜
    public void getReturn02(String seat_id, int menu_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getReturn02_2(seat_id, menu_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getReturn02_2(seat_id, menu_id, callback);
        }
    }

    //退菜
    public void getReturn(String seat_id, int menu_id, ArrayList<String> attribute_ids, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getReturn2(seat_id, menu_id, attribute_ids, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getReturn2(seat_id, menu_id, attribute_ids, callback);
        }
    }

    //分类列表----沽清和点菜
    public ObservableArrayList<DifferentEntity.DataBean> differenGuDian = new ObservableArrayList<DifferentEntity.DataBean>();
    public ObservableField<DifferentEntity> differenGuDiandetali = new ObservableField<>();
    public ObservableField<DifferentEntity.DataBean> differenGuQ = new ObservableField<>();


    //分类列表
    public void getDifferentList(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getDifferentList2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getDifferentList2(callback);
        }
    }

    //下单
    public void getXiadan(RequestBody requestBody, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {

                    getXiadan2(requestBody, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getXiadan2(requestBody, callback);
        }
    }


    public ObservableArrayList<PayTypeEntity.DataBean> payTypeList = new ObservableArrayList<>();

    //支付列表
    public void getPayTypeList(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getPayTypeList_2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getPayTypeList_2(callback);
        }
    }

    public ObservableArrayList<StaffEntity.DataBean> staffList = new ObservableArrayList<>();

    //工作人员列表
    public void getStaffList(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getStaffList2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getStaffList2(callback);
        }
    }


    //支付列表
    public void getPayTypeList2(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getPayTypeList2_2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getPayTypeList2_2(callback);
        }
    }

    public ObservableArrayList<CouponEntity.DataBean> couponList = new ObservableArrayList<>();

    //优惠券列表
    public void getCoupon(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getCoupon2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getCoupon2(callback);
        }
    }

    //买单
    public void getMaiDan(String seat_id, String discount_id, String payment_id, String price, int staff_id, String give_price, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getMaiDan2(seat_id, discount_id, payment_id, price, staff_id, give_price, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getMaiDan2(seat_id, discount_id, payment_id, price, staff_id, give_price, callback);
        }
    }

    //    打印二维码
    public ObservableField<QrcodeEntity> printQr = new ObservableField<>();

    //座位QR
    public void requestSeatQrcode(String type, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    requestSeatQrcode2(type, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            requestSeatQrcode2(type, callback);
        }
    }

    //预定列表
    public ObservableArrayList<ReserveListEntity.DataBean> reserveLiseData = new ObservableArrayList<>();
    public ObservableField<ReserveListEntity> reserveDetail = new ObservableField<>();

    //预定列表
    public void getReserveList(String seat_id, String booked_at, String keyword, int status, String limit, String page, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getReserveList2(seat_id, booked_at, keyword, status, limit, page, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getReserveList2(seat_id, booked_at, keyword, status, limit, page, callback);
        }
    }

    //预定列表详情
    public ObservableField<ReserveDetailEntity> reserveListDetail = new ObservableField<>();

    //预定列表详情
    public void detailsReserveList(int booking_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    detailsReserveList2(booking_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            detailsReserveList2(booking_id, callback);
        }
    }


    //新增预定列表
    public void getAddOrder(String name, String phone, String booked_at, String people, int seat_id, String note, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getAddOrder2(name, phone, booked_at, people, seat_id, note, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getAddOrder2(name, phone, booked_at, people, seat_id, note, callback);
        }
    }

    //修改预定列表
    public void getChange(int booking_id, String name, String phone, String booked_at, String people, int seat_id, String note, String status, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getChange2(booking_id, name, phone, booked_at, people, seat_id, note, status, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getChange2(booking_id, name, phone, booked_at, people, seat_id, note, status, callback);
        }
    }


    //消息列表
    public ObservableArrayList<MessageEntuty.DataBean> messageList = new ObservableArrayList<>();

    //消息中心的订单的列表详情
    public ObservableArrayList<OrderMessageEntity.DataBean> messOrderDetail = new ObservableArrayList<>();
    public ObservableField<OrderMessageEntity> orderMessage = new ObservableField<>();

    //消息中心---订单的列表详情
    public void getMsgOrderDetail(int seat_id, int cart_id, int type, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getMsgOrderDetail2(seat_id, cart_id, type, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getMsgOrderDetail2(seat_id, cart_id, type, callback);
        }
    }

    //消息列表
    public void getMessageList(Integer type, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getMessageList2(type, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getMessageList2(type, callback);
        }
    }

    //服务铃
    public void getServiceBell(int seat_id, int timestamp, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getServiceBell2(seat_id, timestamp, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getServiceBell2(seat_id, timestamp, callback);
        }
    }

    //消息中心---订单的审核的拒绝同意
    public void getMessageOrderIs(int seat_id, int cart_id, int status, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getMessageOrderIs2(seat_id, cart_id, status, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getMessageOrderIs2(seat_id, cart_id, status, callback);
        }
    }

    //沽清列表
    public ObservableArrayList<GuQingEntity.DataBean> guqingList = new ObservableArrayList<>();

    //沽清列表
    public void getGuqList(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getGuqList2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getGuqList2(callback);
        }
    }

    //沽清清除
    public void clearGuqList(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    clearGuqList2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            clearGuqList2(callback);
        }
    }

    //添加雇请
    public void addGuqList(int menuId, String count, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    addGuqList2(menuId, count, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            addGuqList2(menuId, count, callback);
        }
    }

    //删除沽清
    public void delGuqList(int menuId, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    delGuqList2(menuId, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            delGuqList2(menuId, callback);
        }
    }

    //已结账单
    public ObservableArrayList<NotbillrightEntity.DataBean> checkOutList = new ObservableArrayList<>();
    public ObservableField<NotbillrightEntity.MetaBean> notbillrightMata = new ObservableField<>();

    //已结账单
    public void requestOrdersList(Integer page, Integer limit, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    requestOrdersList2(page, limit, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            requestOrdersList2(page, limit, callback);
        }
    }

    //已结账单详情
    public ObservableArrayList<OrderItemEntity.OrderSnapshotsBean> notBillData = new ObservableArrayList<>();
    public ObservableField<OrderItemEntity> BillData = new ObservableField<>();

    //已结账单详情
    public void getOrdersListDetail(String order_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getOrdersListDetail2(order_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getOrdersListDetail2(order_id, callback);
        }
    }

    //放入准备金详情staff/cashiers/put
    public ObservableField<PutMoneyEntity> InPutMoneyDetail = new ObservableField<>();

    //放入准备金
    public void getPutMoney(int price, String note, int staff_id, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getPutMoney2(price, note, staff_id, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getPutMoney2(price, note, staff_id, callback);
        }
    }


    //打印日记表详情
    public ObservableField<MoneyPrintEntity> PrintCashDetail = new ObservableField<>();

    //收银台详情兼做打印日计表
    public void getCashPrint(Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getCashPrint2(callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getCashPrint2(callback);
        }
    }

    //取出准备金详情
    public ObservableField<TakeMoney> TakeMoneyDetail = new ObservableField<>();

    //取出准备金
    public void getTakeMoney(int staff_id, String note, String is_closed, int price, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    getTakeMoney2(staff_id, note, is_closed, price, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            getTakeMoney2(staff_id, note, is_closed, price, callback);
        }
    }

    //切换语言
    public void changeLanguage(String language, Callback callback) {
        if (ISharedPreference.getInstance(getApplication()).getTime() < (System.currentTimeMillis() - timeS)) {
            upDateToken(new ffCallback() {
                @Override
                public void success() {
                    changeLanguage2(language, callback);
                }

                @Override
                public void file(String s) {

                }
            });
        } else {
            changeLanguage2(language, callback);
        }
    }


    //更新token
    public void upDateToken(ffCallback ffcallback) {


            Log.d("----------", "lock: 1");
            BillDataSource.getINSTANCE(getApplication()).upDateToken(new SimpleNetNavigator<LoginEntity>(snackbarText, dialogText, ffcallback) {
                @Override
                public String getTitle() {
                    return "请求中...";
                }

                @Override
                public void success(LoginEntity data) {

                    Log.e("TAG", "更新token------" + new Gson().toJson(data));
                    ISharedPreference.getInstance(getApplication());
                    ISharedPreference.getInstance(getApplication()).saveToken(data.getAccess_token());
                    ISharedPreference.getInstance(getApplication()).saveTime(System.currentTimeMillis());
                    ffcallback.success();
//                lock.unlock();// 释放锁
                    Log.d("----------", "lock: 2");
                }

                @Override
                public void fail(String error) {
                    snackbarText.setValue(error);
                    Log.e("TAG", "更新token-------error:" + error);
                    ToastUtil.CenterShow(error);
                    ISharedPreference.getInstance(getApplication()).saveToken("");
                    EventBusHelper.post(new EventMessage(EventCode.EVENT_NO_TOKEN));
//                lock.unlock();// 释放锁
                    Log.d("----------", "lock: 3");
                }
            });


    }


    //获取所有桌子列表类型 0:获取所有座位 1：获取有人坐的座位 默认是0
    public void getSeatList2(int type, ffCallback ffcallback) {

        BillDataSource.getINSTANCE(getApplication()).getSeatList(type, new SimpleNetNavigator<SeatListEntity>(snackbarText, dialogText, ffcallback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(SeatListEntity data) {
                Log.e("TAG", "座位列表------" + new Gson().toJson(data));
                seatDetail.set(data);
                seatList.clear();
                seatList.addAll(data.getData());
                ffcallback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ffcallback.file(error);
                Log.e("TAG", "fail: 座位列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //获取当前桌的订单菜品列表
    public void getNowSeat2(String seat_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getNowSeat(seat_id, new SimpleNetNavigator<NowSeatEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(NowSeatEntity data) {
                Log.e("TAG", "获取当前桌的订单菜品列表------" + new Gson().toJson(data));
                nowSeatDataList.clear();
                nowSeatDataList.addAll(data.getData());
                nowSeatData.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取当前桌的订单菜品列表\n的:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    //清空当前桌子
    public void getClearSate2(String seat_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getClearSate(seat_id, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
//                Log.e("TAG", "清空当前桌子\n------" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 清空当前桌子\n:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //帮顾客开台
    public void requestCartsSeat2(int seat_id, int count, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).requestCartsSeat(seat_id, count, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 帮顾客开台:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    //退菜
    public void getReturn02_2(String seat_id, int menu_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getReturn02(seat_id, menu_id, new SimpleNetNavigator<T2Entuty>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(T2Entuty data) {
                Log.e("TAG", "退菜------" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 退菜:报错-111--" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    public ObservableField<T2Entuty> t2Entuty = new ObservableField<>();

    //退菜
    public void getReturn2(String seat_id, int menu_id, ArrayList<String> attribute_ids, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getReturn(seat_id, menu_id, attribute_ids, new SimpleNetNavigator<T2Entuty>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(T2Entuty data) {
                Log.e("TAG", "退菜------" + new Gson().toJson(data));
                t2Entuty.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 退菜:报错-123--" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //分类列表
    public void getDifferentList2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getDifferentList(new SimpleNetNavigator<DifferentEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(DifferentEntity data) {
                Log.e("TAG", "沽清列表成功------");
//                Log.e("TAG", "沽清列表------" + new Gson().toJson(data));
                differenGuDiandetali.set(data);
                differenGuQ.set(data.getData().get(0));
                differenGuDian.clear();
                differenGuDian.addAll(data.getData());
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 沽清列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    public ObservableField<XiadanEntity> xiadan = new ObservableField<>();
    //下单
    public void getXiadan2(RequestBody requestBody, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getXiadan(requestBody, new SimpleNetNavigator<XiadanEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(XiadanEntity data) {
                Log.e("TAG", "下单------" + new Gson().toJson(data));
                xiadan.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 下单:报错-123--" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //支付列表
    public void getPayTypeList_2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getPayTypeList(new SimpleNetNavigator<PayTypeEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PayTypeEntity data) {
                Log.e("TAG", "支付列表----123132132--" + new Gson().toJson(data));
                payTypeList.clear();
                payTypeList.addAll(data.getData());
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 支付列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //工作人员列表
    public void getStaffList2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getStaffList(new SimpleNetNavigator<StaffEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(StaffEntity data) {
                Log.e("TAG", "工作人员列表----123132132--" + new Gson().toJson(data));
                staffList.clear();
                staffList.addAll(data.getData());
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 工作人员列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    //支付列表
    public void getPayTypeList2_2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getPayTypeList2(new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "支付列表----123132132--" + data.body());
//                Log.e("TAG", "支付列表----123132132--" + data.);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 支付列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken())
                ToastUtil.CenterShow(error);
            }
        });
    }


    //优惠券列表
    public void getCoupon2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getCoupon(new SimpleNetNavigator<CouponEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(CouponEntity data) {
                Log.e("TAG", "优惠券列表----123132132--");
                couponList.clear();
                CouponEntity.DataBean dataBean = new CouponEntity.DataBean();
                dataBean.setId("");
                dataBean.setName("无折扣");
                dataBean.setType(-1);
                couponList.add(dataBean);
                couponList.addAll(data.getData());
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
//                Log.e("TAG", "fail: 优惠券列表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    public ObservableField<MaiDanEntity> maidan = new ObservableField<>();
    //买单
    public void getMaiDan2(String seat_id, String discount_id, String payment_id, String price, int staff_id, String give_price, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getMaiDan(seat_id, discount_id, payment_id, price, staff_id, give_price, new SimpleNetNavigator<MaiDanEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(MaiDanEntity data) {
                Log.e("TAG", "买单" + new Gson().toJson(data));
                maidan.set(data);
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ error);
                ToastUtil.CenterShow(error);

            }
        });
    }

    //座位QR
    public void requestSeatQrcode2(String type, Callback callback) {
        Log.e("-------","座位列表中的id二维码------" + "sead:" + type);
        BillDataSource.getINSTANCE(getApplication()).requestSeatQrcode(type, new SimpleNetNavigator<QrcodeEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(QrcodeEntity data) {
                Log.e("TAG", "座位列表中的id二维码------" + new Gson().toJson(data));
                printQr.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 座位列表中的id二维码:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //预定列表
    public void getReserveList2(String seat_id, String booked_at, String keyword, int status, String limit, String page, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getReserveList(seat_id, booked_at, keyword, status, limit, page, new SimpleNetNavigator<ReserveListEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(ReserveListEntity data) {
                Log.e("TAG", "预定列表------" + new Gson().toJson(data));
//                reserveDetail.notifyChange();
                reserveDetail.set(data);
                reserveLiseData.clear();
                reserveLiseData.addAll(data.getData());//添加数据
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //预定列表详情
    public void detailsReserveList2(int booking_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).detailsReserveList(booking_id, new SimpleNetNavigator<ReserveDetailEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(ReserveDetailEntity data) {
                Log.e("TAG", "预定列表详情------" + new Gson().toJson(data));
                reserveListDetail.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }


    //新增预定列表
    public void getAddOrder2(String name, String phone, String booked_at, String people, int seat_id, String note, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getAddOrder(name, phone, seat_id, people, note, booked_at, new SimpleNetNavigator<AddOrderEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(AddOrderEntity data) {
                Log.e("TAG", "新增预定列表------" + new Gson().toJson(data));
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //修改预定列表
    public void getChange2(int booking_id, String name, String phone, String booked_at, String people, int seat_id, String note, String status, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getChange(booking_id, name, phone, seat_id, people, note, status, booked_at, new SimpleNetNavigator<ChangeEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(ChangeEntity data) {
                Log.e("TAG", "修改预定列表------" + new Gson().toJson(data));
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //消息中心---订单的列表详情
    public void getMsgOrderDetail2(int seat_id, int cart_id, int type, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getMsgOrderDetail(seat_id, cart_id, type, new SimpleNetNavigator<OrderMessageEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(OrderMessageEntity data) {
                Log.e("TAG", "消息中心订单列表的------" + new Gson().toJson(data));
                messOrderDetail.clear();
                messOrderDetail.addAll(data.getData());
                orderMessage.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 消息中心订单列表的:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //消息列表
    public void getMessageList2(Integer type, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getMessageList(type, new SimpleNetNavigator<MessageEntuty>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(MessageEntuty data) {
                Log.e("TAG", "消息列表------" + new Gson().toJson(data));
                messageList.clear();
                messageList.addAll(data.getData());
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //服务铃
    public void getServiceBell2(int seat_id, int timestamp, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getMsgListDetail(seat_id, timestamp, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "服务铃------");
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 消息中心:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //消息中心---订单的审核的拒绝同意
    public void getMessageOrderIs2(int seat_id, int cart_id, int status, Callback callback) {
        Log.e("TAG", "订单的审核的拒绝同意---seat_id:" +seat_id + ",cart_id:" + cart_id + ",status:" + status);
        BillDataSource.getINSTANCE(getApplication()).getMessageOrderIs(seat_id, cart_id, status, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "消息中心订单的审核的拒绝or同意---审核状态 1：审核通过 2：审核不通过---" + data.toString() );
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 消息中心订单的审核的拒绝or同意:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }

    //沽清列表
    public void getGuqList2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getGuqList(new SimpleNetNavigator<GuQingEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(GuQingEntity data) {
                Log.e("TAG", "沽清列表------");
                guqingList.clear();
                guqingList.addAll(data.getData());
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //沽清清除
    public void clearGuqList2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).clearGuqList(new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "沽清清除------");
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //添加雇请
    public void addGuqList2(int menuId, String count, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).addGuqList(menuId, count, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "添加沽清------");
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }

    //删除沽清
    public void delGuqList2(int menuId, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).delGuqList(menuId, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "删除沽清------");
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }


    //已结账单
    public void requestOrdersList2(Integer page, Integer limit, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).requestOrdersList(limit, page, new SimpleNetNavigator<NotbillrightEntity>(snackbarText, dialogText, callback) {

            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(NotbillrightEntity data) {
                Log.e("TAG", "已结账单------" + new Gson().toJson(data));
                notbillrightMata.set(data.getMeta());
                checkOutList.clear();
                checkOutList.addAll(data.getData());
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }


    //已结账单详情
    public void getOrdersListDetail2(String order_id, Callback callback) {
        Log.e("TAG", "已结账单详情------" + "order_id:" +  order_id);
        BillDataSource.getINSTANCE(getApplication()).getOrdersListDetail(order_id, new SimpleNetNavigator<OrderItemEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(OrderItemEntity data) {
                Log.e("TAG", "已结账单详情------" + new Gson().toJson(data));
                notBillData.clear();
                notBillData.addAll(data.getOrder_snapshots());
                BillData.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
            }
        });
    }


    //放入准备金
    public void getPutMoney2(int price, String note, int staff_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getPutMoney(price, note, staff_id, new SimpleNetNavigator<PutMoneyEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PutMoneyEntity data) {
                Log.e("TAG", "放入准备金的详情------" + new Gson().toJson(data));
                InPutMoneyDetail.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 放入准备金的详情:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }


    //收银台详情兼做打印日计表
    public void getCashPrint2(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getCashPrint(new SimpleNetNavigator<MoneyPrintEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(MoneyPrintEntity data) {
                Log.e("TAG", "收银台详情兼做打印日计表------" + new Gson().toJson(data));
//                Log.e("TAG", "收银台详情兼做打印日计表------" );

                PrintCashDetail.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 收银台详情兼做打印日计表:报错---" + error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());
                ToastUtil.CenterShow(error);
            }
        });
    }


    //取出准备金
    public void getTakeMoney2(int staff_id, String note, String is_closed, int price, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getTakeMoney(staff_id, note, is_closed, price, new SimpleNetNavigator<TakeMoney>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(TakeMoney data) {
                Log.e("TAG", "取出准备金的详情------" + new Gson().toJson(data));
                TakeMoneyDetail.set(data);
                callback.success();
            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 取出准备金的详情:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //切换语言
    public void changeLanguage2(String language, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).changeLanguage(language, new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
                Log.e("TAG", "切换语言------");
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 消息中心:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //打印后厨详情
    public ObservableField<HouChuPrintEntity> houchuDetail = new ObservableField<>();
    public ObservableArrayList<HouChuPrintEntity.MenuInfoBean> houchuDetailList = new ObservableArrayList<>();

    //获取当前桌非下单的打印信息的副本
    public void getFeiHouChuPrint(String seat_id, int type, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getFeiHouChuPrint(seat_id, type, new SimpleNetNavigator<HouChuPrintEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(HouChuPrintEntity data) {
                houchuDetail.set(data);
                houchuDetailList.addAll(data.getMenu_info());
                Log.e("TAG", "获取当后厨打印信息\n-----" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取当后厨信息\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //打印后厨详情
    public ObservableField<HouChuPrintEntity> xiadanDetail = new ObservableField<>();
    public ObservableArrayList<HouChuPrintEntity.MenuInfoBean> xiadanDetailList = new ObservableArrayList<>();
    //获取当前桌非下单打印
    public void getQianChuPrint(String seat_id, int type,int cart_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getQianChuPrint(seat_id, type,cart_id, new SimpleNetNavigator<HouChuPrintEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(HouChuPrintEntity data) {
                houchuDetail.set(data);
                houchuDetailList.addAll(data.getMenu_info());
                Log.e("TAG", "获取当前桌非下单打印\n-----" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取当前桌非下单打印\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //打印退菜详情
    public ObservableField<RejectedEntity> RejectedDetail = new ObservableField<>();
    //获取退菜的菜品信息
    public void getRejected(  int menu_id , String seat_id , String count, String attribute_ids,Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getRejected(menu_id,seat_id,count,attribute_ids, new SimpleNetNavigator<RejectedEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(RejectedEntity data) {
                RejectedDetail.set(data);
//                RejectedDetail.addAll(data.getMenu_info());
                Log.e("获取退菜打印信息", "获取退菜打印信息-----" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取退菜打印信息:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }


    //获取退菜的菜品信息
    public void getRejected2(  int menu_id , String seat_id , String count,Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getRejected2(menu_id,seat_id,count, new SimpleNetNavigator<RejectedEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(RejectedEntity data) {
                RejectedDetail.set(data);
//                RejectedDetail.addAll(data.getMenu_info());
                Log.e("获取退菜打印信息", "获取退菜打印信息-----" + new Gson().toJson(data));
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取退菜打印信息:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //    获取领收书的打印详情
    public ObservableField<LingShouEntity> LingshoushuDetail = new ObservableField<>();

    //获取领受书打印信息
    public void getLingShouPrint(String order_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getLingShouPrint(order_id, new SimpleNetNavigator<LingShouEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(LingShouEntity data) {
                Log.e("TAG", "获取领受书打印信息\n-----" + new Gson().toJson(data));
                LingshoushuDetail.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取领受书打印信息\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    //获取打印订单详情
    public ObservableField<PrintMaidanlEntity> printMaidan = new ObservableField<>();
    //获取打印订单详情
    public void getMaidanPrint(String order_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getMaidanPrint(order_id, new SimpleNetNavigator<PrintMaidanlEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PrintMaidanlEntity data) {
                Log.e("TAG", "获取打印订单详情\n-----" + new Gson().toJson(data));
                printMaidan.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取打印订单详情\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }

    public ObservableField<AAPrintEntity> aaPrint = new ObservableField<>();
    //获取当前桌AA打印信息
    public void getAAPrint(String seat_id, Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getAAPrint(seat_id, new SimpleNetNavigator<AAPrintEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(AAPrintEntity data) {
                Log.e("TAG", "获取当前桌AA打印信息----" + new Gson().toJson(data));
                aaPrint.set(data);
                callback.success();

            }

            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取当前桌AA打印信息\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }


    public ObservableArrayList<PrintListEntity.Data> printList = new ObservableArrayList<>();
    //获取打印列表
    public void getPrintList(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getPrintList(new SimpleNetNavigator<PrintListEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PrintListEntity data) {
                Log.e("TAG", "获取打印列表信息----" + new Gson().toJson(data));
                printList.clear();
                printList.addAll(data.getData());
                callback.success();
            }
            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取打印列表\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }
        });
    }


    public ObservableField<PrintDetailEntity> staffPrint = new ObservableField<>();
    //打印机详情
    public void getDetailsPrint(Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getDetailsPrint(new SimpleNetNavigator<PrintDetailEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PrintDetailEntity data) {
                Log.e("TAG", "获取打印机详情----" + new Gson().toJson(data));
                staffPrint.set(data);
                callback.success();
            }
            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取打印机详情\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }

        });

    }


    //修改打印机
    public void getChangePrint(String id,String ip, ffCallback ffCallback) {
        BillDataSource.getINSTANCE(getApplication()).getChangePrint(id,ip, new SimpleNetNavigator<PrintChangeEntity>(snackbarText, dialogText, ffCallback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(PrintChangeEntity data) {
                Log.e("TAG", "修改打印机------" + new Gson().toJson(data));
                ffCallback.success();
            }

            @Override
            public void fail(String error) {
                ffCallback.file(error);
                snackbarText.setValue(error);
                ToastUtil.CenterShow(error);
                Log.e("TAG", "修改打印机---" + error);

            }
        });
    }


    //打印机锁
    public void getPrintLock(String ip ,int merchantId,Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getPrintLock(ip,merchantId,new SimpleNetNavigator<Response<Void>>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(Response<Void> data) {
//                Log.e("TAG", "打印机锁------" + new Gson().toJson(data));
                callback.success();
            }
            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 打印机锁\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }

        });

    }


    public ObservableField<SpriceEntity.Data> sprice = new ObservableField<>();
    //获取当前桌的消费情况
    public void getSprice(String seat_id,String discount_id,Callback callback) {
        BillDataSource.getINSTANCE(getApplication()).getSprice(seat_id,discount_id,new SimpleNetNavigator<SpriceEntity>(snackbarText, dialogText, callback) {
            @Override
            public String getTitle() {
                return "请求中...";
            }

            @Override
            public void success(SpriceEntity data) {
                Log.e("TAG", "获取当前桌的消费情况\n----" + new Gson().toJson(data));
                sprice.set(data.getData());
                callback.success();
            }
            @Override
            public void fail(String error) {
                snackbarText.setValue(error);
                Log.e("TAG", "fail: 获取当前桌的消费情况\n:报错---" + error);
                ToastUtil.CenterShow(error);
//                        Log.e("TAG", "fail: 已结账单:报错---"+ sharedPreference.getToken());

            }

        });

    }
}
