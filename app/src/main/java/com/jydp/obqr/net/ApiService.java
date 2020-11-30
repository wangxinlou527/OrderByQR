package com.jydp.obqr.net;


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
import com.jydp.obqr.entity.ShoppingEntity;
import com.jydp.obqr.entity.T2Entuty;
import com.jydp.obqr.entity.UserDetailEntity;
import com.jydp.obqr.entity.XiadanEntity;
import com.jydp.obqr.netnavigator.NetNavigator;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口
 * 这个是真实的代码，
 * Path---拼接的这种类型----staff/carts/:seat_id
 */

public interface ApiService {

    String baseUrl = "https://qrorder.jydp.work/";//测试
    String wsUrl = "wss://qrorder.jydp.work/";//测试
//    String baseUrl = "http://qr-order.test/";//本地
//    String baseUrl = "https://30062cf838c9.ngrok.io/";//测试;/

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码H
     * @return Observable
     * @POST(Contains.LOGIN_URL)
     */
    @FormUrlEncoded
    @POST(Contains.LOGIN_URL)
    Observable<LoginEntity> login(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("language") String language);

    /**
     * 所有用户列表（2020.0428，-e.getMessage()-----------java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $）
     */
    @GET(Contains.PEOPLE_DETAILS)
    Observable<UserDetailEntity> getUseDetail();

    /**
     * 获取店铺详情
     */
    @GET(Contains.STORE_DETAILS)
    Observable<ShoppingEntity> getShopping();

    /**
     * 获取当前桌的订单菜品列表
     * http://{{host}}/staff/carts/order/:seat_id
     */
    @GET(Contains.GET_MENU_LIST)
    Observable<NowSeatEntity> getNowSeat(@Path("seat_id") String seat_id);

    /**
     * 所有订单桌子列表
     * type默认是0
     * 获取所有桌子列表类型 0:获取所有座位 1：获取有人坐的座位 默认是0
     */
    @GET(Contains.GET_SEAT_LIST)
    Observable<SeatListEntity> getSeatList(@Query("type") Integer type);

    /**
     * 清空当前桌子
     * 清空当前桌子
     * 请求URL：
     * http://{{host}}/staff/carts/:seat_id
     * 请求方式：
     * <p>
     * DELETE
     * 参数：
     * <p>
     * 参数名	必选	类型	说明
     * seat_id	是	integer	桌号
     */
    @DELETE(Contains.CLEAR_SATE)
    Observable<Response<Void>> getClearSate(@Path("seat_id") String seat_id);

    /**
     * 下单
     *
     * @param seat_id 座位id  int   必选:  是
     * @param count   客人人数  int  必选: 是
     * @return 帮助顾客开台
     * 返回没有参数，但是状态需要变下
     */
    @FormUrlEncoded
    @POST(Contains.CARTS_SEAT)
    Observable<Response<Void>> requestCartsSeat(
            @Field("seat_id") int seat_id,
            @Field("count") int count);

    /**
     * 退菜
     * staff/carts/order/{seat_id}
     * menu_id	是	integer	菜品id
     * attribute_ids	否	int	标签属性id,不传就是标签属性
     */
    @PUT(Contains.RETURN_VEGETABLES)
    Observable<T2Entuty> getReturn02(@Path("seat_id") String seat_id,
                                     @Query("menu_id") Integer menu_id);

    @PUT(Contains.RETURN_VEGETABLES)
    Observable<T2Entuty> getReturn(@Path("seat_id") String seat_id,
                                   @Query("menu_id") Integer menu_id,
                                   @Query("attribute_ids[]") ArrayList<String> attribute_ids);

    /*
分类列表----沽清
*/
    @GET(Contains.MENU_LIST)
    Observable<DifferentEntity> getDifferentList();


    //下单
    @POST(Contains.CREATE_ORDER)
    Observable<XiadanEntity> getXiadan(@Body RequestBody requestBody);


    /*
支付列表---GET
*/
    @GET(Contains.GET_PAYMENTS)
    Observable<PayTypeEntity> getPayTypeList();

    @GET(Contains.GET_PAYMENTS)
    Observable<Response<Void>> getPayTypeList2();

    /*
优惠券---GET
*/
    @GET(Contains.GET_COUPONS)
    Observable<CouponEntity> getCouponList();

    /**
     * 参数名	必选	类型	说明
     * seat_id	是	integer	桌号
     * discount_id	否	integer	折扣id不传或者传空就是不使用折扣
     * payment_id	是	integer	支付方式id
     * price	是	integer	实付款，前厅计算好的实付款金额，用于校验
     * staff_id	是	integer	操作人员id
     * give_price	payment_id为1必填	integer	客人实际给的现金
     */
    @FormUrlEncoded
    @POST(Contains.POST_MAIDAN)
    Observable<MaiDanEntity> getMaiDan(@Field("seat_id") String seat_id,
                                       @Field("discount_id") String discount_id,
                                       @Field("payment_id") String payment_id,
                                       @Field("price") String price,
                                       @Field("staff_id") int staff_id,
                                       @Field("give_price") String give_price);

    //员工
    @GET(Contains.USE_LIST)
    Observable<StaffEntity> getStaffList();


    /**
     * @param id 座位id  int   必选:  是
     * @return 获得当前桌的二维码
     */
    @GET(Contains.GET_QRCODE)
    Observable<QrcodeEntity> requestSeatQrcode(@Path("id") String id);


    /**
     * 预定列表
     * 参数名	必选	类型	说明
     * seat_id	否	integer	座位id
     * booked_at	否	date	预定时间
     * keyword	否	string	预订人电话号码或者姓名
     * status	否	integer	预定状态 0:预定中，1:已到店 2:已取消
     */
    @GET(Contains.INSERT_YUDING)
    Observable<ReserveListEntity> getReserveList(@Query("seat_id") String seat_id,
                                                 @Query("booked_at") String booked_at,
                                                 @Query("keyword") String keyword,
                                                 @Query("status") int status,
                                                 @Query("limit") String limit,
                                                 @Query("page") String page);

    /**
     * 预定详细信息
     *
     * @param
     * @return
     */
    @GET(Contains.DETAILS_YUDING)
    Observable<ReserveDetailEntity> detailsReserveList(@Path("booking_id") int id);

    /**
     * 新增预定
     * 提交from表单
     * 参数名	必选	类型	说明
     * name	是	string	预订者姓名
     * phone	是	int	预订者电话号码
     * seat_id	是	int	座位id
     * people	是	int	预订人数
     * note	否	string	备注
     * booked_at	是	date	预定时间 Y-m-d H:i
     */
    @FormUrlEncoded
    @POST(Contains.INSERT_YUDING)
    Observable<AddOrderEntity> getAddOrder(@Field("name") String name,
                                           @Field("phone") String phone,
                                           @Field("seat_id") Integer seat_id,
                                           @Field("people") String people,
                                           @Field("note") String note,
                                           @Field("booked_at") String booked_at);

    /**
     * 预定详细信息
     *
     * @param
     * @return
     */
    @PUT(Contains.DETAILS_YUDING)
    Observable<ChangeEntity> getChange(@Path("booking_id") Integer booking_id,@Body Yunding yunding);
    class Yunding{
        public String name;
        public String phone;
        public Integer seat_id;
        public String people;
        public String note;
        public String status;
        public String booked_at;
    }


    /**
     *
     */
    @GET(Contains.MSG_CENTER)
    Observable<MessageEntuty> getMessageList(@Query("type") Integer type);


    //   沽清列表
    @GET(Contains.GET_GUQING)
    Observable<GuQingEntity> getGuqList();


    //清空
    @DELETE(Contains.CLEAR_GUQING)
    Observable<Response<Void>> clearGuqList();

    //添加
    @FormUrlEncoded
    @POST(Contains.INSERT_GUQING)
    Observable<Response<Void>> addGuqList(@Field("menu_id") int menu_id,
                                          @Field("count") String count);

    //删除
    @DELETE(Contains.DELETE_GUQING)
    Observable<Response<Void>> delGuqList(@Path("menu_id") int menu_id);

//    @DELETE(Contains.DELETE_GUQING)
//    rx.Observable<String> requestGuqDelete(@Path("menu_id") Integer menu_id);


    /**
     * 消息中心列表详情（
     */
    @GET(Contains.MSG_CENTER_ORDER_DETAIL)
    Observable<OrderMessageEntity> getMsgOrderDetail(@Path("seat_id") Integer seat_id,
                                                     @Query("cart_id") Integer cart_id,
                                                     @Query("type") Integer type);

    /**
     * 消息服务铃(
     */
    @PUT(Contains.MSG_CENTER_DETAIL)
    Observable<Response<Void>> getMsgListDetail(@Path("seat_id") Integer seat_id,
                                                @Query("timestamp") Integer timestamp);

    /**
     * 消息中心list---订单的审核的拒绝同意
     * seat_id	是	integer	座位id
     * cart_id	是	integer	购物车id
     * status	是	integer	审核状态 1：审核通过 2：审核不通过
     * pattern_id	否	int	做法id,不传就是没有做法
     */
    @PUT(Contains.MSG_CENTER_ORDER_IS)
    Observable<Response<Void>> getMessageOrderIs(@Path("seat_id") Integer seat_id,
                                                 @Query("cart_id") Integer cart_id,
                                                 @Query("status") Integer status);

    /**
     * 已结账单列表
     * 参数名	必选	类型	说明
     * page	否	integer	当前页，默认1
     * limit	否	integer	显示数量，默认20
     */
    @GET(Contains.ORDER_LIST)
    Observable<NotbillrightEntity> requestOrdersList(@Query("limit") Integer limit,
                                                     @Query("page") Integer page);

    /**
     * 已结账单列表
     * 参数名	必选	类型	说明
     * page	否	integer	当前页，默认1
     * limit	否	integer	显示数量，默认20
     */
    @GET(Contains.ORDER_LIST_LEFT)
    Observable<OrderItemEntity> getOrdersListDetail(@Path("order_id") String id);

    //放入准备金
    /*
    * 参数名	必传	类型	说明
price	是	int	放入准备金金额
note	否	string	备注
staff_id	是	int	操作者id
* */
    @FormUrlEncoded
    @POST(Contains.PUT_MONEY)
    Observable<PutMoneyEntity> getPutMoney(@Field("price") int price,
                                           @Field("note") String note,
                                           @Field("staff_id") int staff_id);

    /*
    GET
    打印日记表
* */
    @GET(Contains.CASH_PRINT)
    Observable<MoneyPrintEntity> getCashPrint();

    /*
参数名	必传	类型	说明
staff_id	是	int	当担者id
note	否	string	备注
is_closed	是	bool	是否打烊
price	是	int	取出金额
*   //取出准备金
* */
    @FormUrlEncoded
    @POST(Contains.TAKE_MONEY)
    Observable<TakeMoney> getTakeMoney(@Field("staff_id") int staff_id,
                                       @Field("note") String note,
                                       @Field("is_closed") String is_closed,
                                       @Field("price") int price);


    /**
     * 切换语言(
     * language  1.en;2.ja-JP;3.zh-CN;4.zh-HK;5.ko-KR
     */
    @PUT(Contains.PEOPLE_DETAILS)
    Observable<Response<Void>> changeLanguage(@Query("language") String language);

    //更新token
    @PUT(Contains.LOGIN_OUT)
    Observable<LoginEntity> upDateToken();


    //获取打印订单列表
    @GET(Contains.GET_PRINT_CART)
    Observable<HouChuPrintEntity> getFeiHouChuPrint(@Query("seat_id") String seat_id,
                                                    @Query("type") int type);

    //获取打印订单列表
    @GET(Contains.GET_PRINT_CART)
    Observable<HouChuPrintEntity>  getQianChuPrint(@Query("seat_id") String seat_id,
                                                    @Query("type") int type,
                                                    @Query("cart_id")int cart_id);

    /**
     * 获取退菜打印信息
     * 参数：
     * <p>
     * 参数名	必选	类型	说明
     * menu_id	是	integer	菜单id
     * seat_id	是	integer	座位id
     * count	是	integer	退菜数量
     * attribute_ids	否	string	属性标签id,id与id之间用,分隔
     */
    @GET(Contains.GET_PRINTS_REJECTED)
    Observable<RejectedEntity> getRejected(@Query("menu_id") int menu_id,
                                           @Query("seat_id") String seat_id,
                                           @Query("count") String count,
                                           @Query("attribute_ids") String attribute_ids);
    @GET(Contains.GET_PRINTS_REJECTED)
    Observable<RejectedEntity> getRejected2(@Query("menu_id") int menu_id,
                                           @Query("seat_id") String seat_id,
                                           @Query("count") String count);

    /**
     * 获取领受书打印信息
     * 订单id----order_id
     */
    @GET(Contains.GET_PRINTS_LINGSHOU)
    Observable<LingShouEntity> getLingShouPrint(@Query("order_id") String order_id);

    /**
     * 打印机详情
     *
     * @return
     * @param无
     */
    @GET(Contains.PRINT_DETAILS)
    Observable<PrintDetailEntity> getDetailsPrint();


    /**
     * 打印机详情
     *
     * @return
     * @param无
     */
    @GET(Contains.GET_PRINTS_ORDER)
    Observable<PrintMaidanlEntity> getMaidanPrint(@Query("order_id") String order_id);

    /**
     * 获取当前桌AA打印信息
     * {{host}}/staff/print/aa-cart-list?seat_id=1
     * 座位id
     */
    @GET(Contains.GET_PRINTS_AA)
    Observable<AAPrintEntity> getAAPrint(@Query("seat_id") String seat_id);

    /**
     * 获取当打印机列表
     *
     */
    @GET(Contains.PRINT_LIST)
    Observable<PrintListEntity> getPrintList();

    /**
     * 修改打印机-----2020.0429.出错了 修改打印机---HTTP 404
     *
     * @param
     * @return 404 Not Found - 请求一个不存在的资源---是否是参数错误
     */
    @PUT(Contains.CHANGE_PRINT)
    Observable<PrintChangeEntity> getChangePrint(@Path("id") String id,
                                                 @Query("ip") String ip);

    /**
     * 获取当打印机列表
     *
     */
    @GET(Contains.PRINT_LOCK)
    Observable<Response<Void>> getPrintLock(@Query("ip") String ip,
                                            @Query("merchant_id") int merchant_id);

    /**
     * 获取当前桌的消费情况
     *
     */
    @GET(Contains.ORDER_SPRICE)
    Observable<SpriceEntity> getSprice(@Query("seat_id")String seat_id,
                                       @Query("discount_id")String discount_id);

}

