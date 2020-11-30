package com.jydp.obqr.net;

/*
2020/4/27 13:46 星期一
作用 ：接口对接的变量
*/
public interface Contains {
    String LOGIN_URL = "staff/authorizations";//登录  POST

    String LOGIN_OUT = "staff/authorizations/current";//退出  DELETE

    String USE_LIST = "staff/staff";//获取当前所有用户列表---GET

    String GET_SEAT_LIST = "staff/seats"; //获取座位列表 GET
    String GET_PEO_LIST = "/staff/staff"; //获取电源列表 GET
    //下单
    String CARTS_SEAT = "staff/carts/seat";//帮助顾客开台
    String CREATE_ORDER = "staff/carts/order";//帮助顾客下单


    String ORDER_SPRICE = "staff/orders/price";//获取当前桌的消费情况

    String POST_MAIDAN = "staff/orders";//买单

    String CLEAR_SATE = "staff/carts/{seat_id}";//清空当前桌子

    String GET_QRCODE = "staff/seats/qrcode/{id}";

    String RETURN_VEGETABLES = "staff/carts/order/{seat_id}";//退菜

    String PEOPLE_DETAILS = "staff/staff/current"; //获取用户详细信息  GET

    String MENU_LIST = "staff/categories";//获取分类列表菜单列表 GET

    String LANGUAGES = "manager/merchants/languages";// 获取系统语言列表 GET

    String STORE_DETAILS = "staff/merchants";   //店铺详情  GET


    String GET_SEAT_DETAILS = "staff/carts/order/{id}";  //获取座位信息   GET  拼接座位id参数

    //折扣
    String GET_ZHEKOU = "staff/discounts"; //列表

    String GET_ZHEKOU_DETAILS = "staff/discounts/{discount_id}"; //详情

    //支付列表
    String GET_PAYMENTS = "staff/payments";

    //优惠券列表
    String GET_COUPONS = "staff/discounts";

    String INSERT_SEAT = "staff/seat"; //新增座位信息 POST

    String UPDATE_SEAT = "staff/seats";//修改座位信息 PATCH




    String GET_MENU_DETAILS = "staff/menus";//获取菜单详情   GET

    String GET_MENU_LIST = "staff/carts/order/{seat_id}";//获取当前卓的菜品列表  GET


//预定

    String INSERT_YUDING = "staff/bookings";   //预定列表  POST


    String GET_YUDING_LIST = "staff/bookings";//预定列表  GET


    String DETAILS_YUDING = "staff/bookings/{booking_id}";//预定详情   GET   拼接booking_id 参数


    String UPDATE_YUDING = "staff/bookings"; //修改预定

//沽清

    String GET_GUQING = "staff/stocks";//列表

    String INSERT_GUQING = "staff/stocks";//新增

    String DELETE_GUQING = "staff/stocks/{menu_id}";//删除

    String CLEAR_GUQING = "staff/stocks";//清空


    //已结账单
    String ORDER_LIST = "staff/orders";
    //已结账单详情
    String ORDER_LIST_LEFT = "staff/order/{order_id}";


    String MSG_CENTER = "staff/messages"; //消息中心list 列表
    String MSG_CENTER_DETAIL = "staff/messages/{seat_id}"; //消息中心list 列表
    String MSG_CENTER_ORDER_DETAIL = "staff/carts/order-seat/{seat_id}"; //消息中心list---订单的订单的审核的拒绝同意
    String MSG_CENTER_ORDER_IS = "/staff/carts/{seat_id}"; //消息中心list---订单的审核的拒绝同意---http://{{host}}/staff/carts/:seat_id

    String GET_MEALS = "staff/meals"; //取餐桌位列表


    String GET_MEALS_LEFT = "staff/meals/6";//待取餐桌位列表 GET 拼接seat_id 参数


    String UPLOAD_USERIMAGE = "/elecBaoDian/file_uploadUserImage.do";//图片上传


    String PUT_MONEY = "/staff/cashiers/put";//放入准备金
    String TAKE_MONEY = "/staff/cashiers/take";//取出准备金
    String CASH_PRINT = "/staff/cashiers";//收银台详情兼做打印日计表

     //打印
    String GET_PRINT_CART = "staff/print/cart-list"; //获取当前桌下单的打印信息  GET---状态2的打印
    String GET_PRINTS_ORDER = "staff/print/order-list";//获取买单打印信息  GET
    String GET_PRINTS_REJECTED = "staff/print/rejected-list"; //获取退菜打印信息
    String GET_PRINTS_AA = "staff/print/aa-cart-list"; //获取当前桌AA打印信息--GET
    String GET_PRINTS_LINGSHOU = "staff/print/receiving-book"; //打印领收书--GET

    String PRINT_DETAILS = "staff/printers"; //打印机详情 GET-
    String INSERT_PRINT = "staff/printers"; //新增打印机 GET
    String CHANGE_PRINT = "staff/printers/{id}";//修改打印机   PUT

    String PRINT_LIST = "staff/printers/all";//打印机列表
    String PRINT_LOCK = "staff/printers/getSign";//打印锁

}
