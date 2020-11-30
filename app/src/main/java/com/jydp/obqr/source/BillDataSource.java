package com.jydp.obqr.source;

import android.app.Application;
import android.util.Log;


import com.jydp.obqr.entity.AAPrintEntity;
import com.jydp.obqr.entity.AddOrderEntity;
import com.jydp.obqr.entity.ChangeEntity;
import com.jydp.obqr.entity.CouponEntity;
import com.jydp.obqr.entity.DataEntity;
import com.jydp.obqr.entity.DifferentEntity;
import com.jydp.obqr.entity.GuQingEntity;
import com.jydp.obqr.entity.HouChuPrintEntity;
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
import com.jydp.obqr.net.ApiService;
import com.jydp.obqr.netnavigator.NetNavigator;
import com.jydp.obqr.util.ISharedPreference;
import com.jydp.obqr.util.RetrofitUtil;
import com.jydp.obqr.util.RxUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Response;

public class BillDataSource {

    private static BillDataSource IINSTANCE;


    private WeakReference<Application> app;
    //获取本地存储类对象
    private ISharedPreference sharedPreference;

    private BillDataSource(WeakReference<Application> app) {
        this.app = app;
        sharedPreference = ISharedPreference.getInstance(app.get());
    }


    //获取单例
    public static BillDataSource getINSTANCE(Application application) {

        if (IINSTANCE == null) {
            synchronized (BillDataSource.class) {
                if (IINSTANCE == null) {
                    IINSTANCE = new BillDataSource(new WeakReference<>(application));
                }
            }
        }
        return IINSTANCE;
    }

    //获取当前桌的订单菜品列表
    public void getNowSeat(String seat_id , NetNavigator<NowSeatEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getNowSeat(seat_id), navigator);
    }

    /**
     * 获取座位列表
     * 获取所有桌子列表类型 0:获取所有座位 1：获取有人坐的座位 默认是0
     */
    public void getSeatList(int type, NetNavigator<SeatListEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getSeatList(type), navigator);
    }

    //清空当前桌子
    public void getClearSate( String seat_id , NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getClearSate(seat_id), navigator);
    }

    //帮顾客开台
    public void requestCartsSeat( int seat_id ,int count, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.requestCartsSeat(seat_id,count), navigator);
    }

    //退菜
    public void getReturn02(String seat_id , int menu_id,  NetNavigator<T2Entuty> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getReturn02(seat_id,menu_id), navigator);
    }

    //退菜 + 属性
    public void getReturn(String seat_id , int menu_id, ArrayList<String> attribute_ids, NetNavigator<T2Entuty> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getReturn(seat_id,menu_id,attribute_ids), navigator);
    }

    //分类列表（沽清列表）
    public void getDifferentList( NetNavigator<DifferentEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getDifferentList(), navigator);
    }

    //点菜
    public void getXiadan(RequestBody requestBody, NetNavigator<XiadanEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getXiadan(requestBody), navigator);
    }

    //支付列表
    public void getPayTypeList( NetNavigator<PayTypeEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getPayTypeList(), navigator);
    }

    //工作人员列表
    public void getStaffList( NetNavigator<StaffEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getStaffList(), navigator);
    }

    //支付列表
    public void getPayTypeList2(NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getPayTypeList2(), navigator);
    }

    //优惠券
    public void getCoupon( NetNavigator<CouponEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getCouponList(), navigator);
    }

    /**
     * 买单
     */
    public void getMaiDan( String seat_id, String discount_id, String payment_id, String price, int staff_id, String give_price, NetNavigator<MaiDanEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMaiDan(seat_id, discount_id, payment_id, price, staff_id, give_price), navigator);
    }

    /**
     * 获取座位的二维码
     */
    public void requestSeatQrcode(String type, NetNavigator<QrcodeEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.requestSeatQrcode(type), navigator);
    }

    /**
     * /**
     * 预定列表
     * 参数名	必选	类型	说明
     * seat_id	否	integer	座位id
     * booked_at	否	date	预定时间
     * keyword	否	string	预订人电话号码或者姓名
     * status	否	integer	预定状态 0:预定中，1:已到店 2:已取消
     */
    public void getReserveList(String seat_id, String booked_at, String keyword, int status,String limit,String page,  NetNavigator<ReserveListEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getReserveList(seat_id, booked_at, keyword, status,limit,page), navigator);
    }

    /**
     * 预定列表详情
     * detailsReserveList(@Path("booking_id") Integer id);
     */
    public void detailsReserveList(int booking_id, NetNavigator<ReserveDetailEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.detailsReserveList(booking_id), navigator);
    }

    /**
     * 预定列表详情
     * detailsReserveList(@Path("booking_id") Integer id);
     */
    public void getAddOrder(String name, String phone, int seat_id, String people, String note, String booked_at, NetNavigator<AddOrderEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getAddOrder(name, phone, seat_id, people, note, booked_at), navigator);
    }
    /**
     * 预定修改
     * detailsReserveList(@Path("booking_id") Integer id);
     */
    public void getChange(int booking_id ,String name, String phone, int seat_id, String people, String note, String status,String booked_at, NetNavigator<ChangeEntity> navigator) {
        ApiService.Yunding yunding = new ApiService.Yunding();
        yunding.name = name;
        yunding.seat_id = seat_id;
        yunding.phone = phone;
        yunding.seat_id = seat_id;
        yunding.people  = people;
        yunding.note = note;
        yunding.status = status;
        yunding.booked_at = booked_at;
        Log.d("--------","YUDING--------");
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getChange(booking_id,yunding), navigator);
    }

    /**
     * 消息列表
     * detailsReserveList(@Path("booking_id") Integer id);
     */
    public void getMessageList(Integer type, NetNavigator<MessageEntuty> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMessageList(type), navigator);
    }

    /**
     * 沽清列表
     *
     */
    public void getGuqList( NetNavigator<GuQingEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getGuqList(),navigator);
    }

    /**
     * 沽清列表
     *
     */
    public void clearGuqList( NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.clearGuqList(),navigator);
    }

    /**
     * 增加沽清
     *
     */
    public void addGuqList( int menu_id,String count, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.addGuqList(menu_id,count),navigator);
    }

    /**
     * 删除沽清
     *
     */
    public void delGuqList( int menu_id, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.delGuqList(menu_id),navigator);
    }

    //消息中心-订单的列表详情
    /**
     * @Path("seat_id") Integer seat_id,
     *                                                      @Path("cart_id") Integer cart_id,
     *                                                      @Query("type") Integer type
     *                                                      */
    public void getMsgOrderDetail( int seat_id ,int cart_id, int type,NetNavigator<OrderMessageEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMsgOrderDetail(seat_id,cart_id,type), navigator);
    }

    //消息服务铃
    public void getMsgListDetail( int seat_id ,int timestamp, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMsgListDetail(seat_id,timestamp), navigator);
    }

    //消息中心---订单的审核的拒绝同意
    public void getMessageOrderIs( int seat_id ,int cart_id,int status,NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMessageOrderIs(seat_id,cart_id,status), navigator);
    }

    /**
     * 已结订单的列表
     * 参数名	必选	类型	说明
     * page	否	integer	当前页，默认1
     * limit	否	integer	显示数量，默认20
     */
    public void requestOrdersList(Integer limit, Integer page, NetNavigator<NotbillrightEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.requestOrdersList(limit, page), navigator);

    }

    /**
     * 已结订单的列表详情
     * 参数名	必选	类型	说明
     */
    public void getOrdersListDetail(String order_id, NetNavigator<OrderItemEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getOrdersListDetail(order_id), navigator);
    }

    /**
     * 放入准备金
     * detailsReserveList(@Path("booking_id") Integer id);//
     */
    public void getPutMoney(int price, String note, int staff_id, NetNavigator<PutMoneyEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getPutMoney(price, note, staff_id), navigator);
    }

    //收银台详情兼做打印日计表
    public void getCashPrint( NetNavigator<MoneyPrintEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getCashPrint(), navigator);
    }

    /**
     * 放入准备金
     * detailsReserveList(@Path("booking_id") Integer id);//
     */
    public void getTakeMoney( int staff_id,String note, String is_closed, int price, NetNavigator<TakeMoney> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getTakeMoney(staff_id, note, is_closed,price), navigator);
    }

    //切换语言
    public void changeLanguage( String language, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.changeLanguage(language), navigator);
    }

    //更新token
    public void upDateToken( NetNavigator<LoginEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.upDateToken(), navigator);
    }

    //获取当前桌非下单的打印信息的副本
    public void getFeiHouChuPrint( String seat_id   ,int type ,NetNavigator<HouChuPrintEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getFeiHouChuPrint(seat_id,type), navigator);
    }

    public void getQianChuPrint( String seat_id ,int type ,int cart_id,NetNavigator<HouChuPrintEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getQianChuPrint(seat_id,type,cart_id), navigator);
    }



    //获取获取退菜打印信息
    public void getRejected( int menu_id , String seat_id , String count, String attribute_ids,NetNavigator<RejectedEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getRejected(menu_id,seat_id,count,attribute_ids), navigator);
    }
    //获取获取退菜打印信息
    public void getRejected2( int menu_id , String seat_id , String count,NetNavigator<RejectedEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getRejected2(menu_id,seat_id,count), navigator);
    }

    //获取领受书打印信息
    public void getLingShouPrint( String order_id ,NetNavigator<LingShouEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getLingShouPrint(order_id), navigator);
    }

    //获取领受书打印信息
    public void getMaidanPrint( String order_id ,NetNavigator<PrintMaidanlEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getMaidanPrint(order_id),navigator);
    }

    //获取当前桌AA打印信息
    public void getAAPrint( String seat_id ,NetNavigator<AAPrintEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getAAPrint(seat_id), navigator);
    }

    //获取当前桌AA打印信息
    public void getPrintList( NetNavigator<PrintListEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getPrintList(),navigator);
    }

    //打印机详情
    public void getDetailsPrint( NetNavigator<PrintDetailEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getDetailsPrint(), navigator);
    }

    //修改打印机
    public void getChangePrint(String id,String ip, NetNavigator<PrintChangeEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getChangePrint(id,ip), navigator);
    }

    //打印机详情
    public void getPrintLock(String ip,int merchant_id, NetNavigator<Response<Void>> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getPrintLock(ip,merchant_id), navigator);
    }

    //获取当前桌的消费情况
    public void getSprice(String seat_id,String discount_id, NetNavigator<SpriceEntity> navigator) {
        RxUtil.ioMain(RetrofitUtil.getInstance().apiService.getSprice(seat_id,discount_id), navigator);
    }

}
