package com.jydp.obqr.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 本地存储类
 */
public class ISharedPreference {
    public static ISharedPreference preference;

    public SharedPreferences sharedPreferences;//存储对象

//    public ISharedPreference(Application app) {
//    }
    private ISharedPreference(Application app) {
// Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.SharedPreferences android.app.Application.getSharedPreferences(java.lang.String, int)' on a null object reference
        sharedPreferences = app.getSharedPreferences("tz", Context.MODE_PRIVATE);
    }
    /**
     * 使用单例模式
     *
     * @param app app
     * @return
     */
    public static ISharedPreference getInstance(Application app) {
        if (preference == null) {
            synchronized (ISharedPreference.class) {
                if (preference == null) {
                    preference = new ISharedPreference(app);
                }
            }
        }
        return preference;
    }


    //退出登录   清除数据
    public void loginOut() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * 保存token
     *
     * @param token token
     */
    public void saveToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString("token", "");
    }
    /**
     * 保存店铺id
     */
    public void saveMerchant_id(String merchant_id) {
        sharedPreferences.edit().putString("merchant_id", merchant_id).apply();
    }

    public String getMerchant_id() {
        return sharedPreferences.getString("merchant_id", "");
    }
    /**
     * 保存店铺名字
     */
    public void saveShop_name(String shop_name) {
        sharedPreferences.edit().putString("name", shop_name).apply();
    }

    public String getShop_name() {
        return sharedPreferences.getString("name", "");
    }
    /**
     * 保存店铺打印机详情ip
     */
    public void savePrint_ip(String ip) {
        sharedPreferences.edit().putString("ip", ip).apply();
    }

    public String getPrint_ip() {
        return sharedPreferences.getString("ip", "");
    }

    /**
     * 保存用户id
     *
     * @param userId id
     */
    public void saveUserId(int userId) {
        sharedPreferences.edit().putInt("userId", userId).apply();
    }

    public void saveUserClientId(String userClientId){
        sharedPreferences.edit().putString("userClientId", userClientId).apply();
    }
    public void saveImei(String imei){
        sharedPreferences.edit().putString("imei", imei).apply();
    }

    public String getImei(){
        return sharedPreferences.getString("imei","");
    }

    public void saveSN(String SN){
        sharedPreferences.edit().putString("SN", SN).apply();
    }

    public String getSN(){
        return sharedPreferences.getString("SN","");
    }
    public String getUserClientId(){
        return sharedPreferences.getString("userClientId","");
    }

    public int getUserId() {
        return sharedPreferences.getInt("userId", 0);
    }

    /**
     * 保存用户手机号
     *
     * @param phone 手机号
     */
    public void savePhone(String phone) {
        sharedPreferences.edit().putString("phone", phone).apply();
    }

    public String getPhone() {
        return sharedPreferences.getString("phone", "");
    }

    /**
     * 保存用户名
     *
     * @param userName 用户名
     */
    public void saveUserName(String userName) {
        sharedPreferences.edit().putString("userName", userName).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    //储存是否接收通知
    public void saveReceiveNotice(boolean is) {
        sharedPreferences.edit().putBoolean("is", is).apply();
    }

    //默认接收通知====在设置里
    public boolean getReceiveNotice() {
        return sharedPreferences.getBoolean("is", true);
    }

    //储存是通知字符串
    public void saveNoticeStr(String notice) {
        sharedPreferences.edit().putString("notice", notice).apply();
    }

    public String getNoticeStr() {
        return sharedPreferences.getString("notice", "");
    }
    //储存是预授权通知字符串
    public void saveFristNoticeStr(String notice) {
        sharedPreferences.edit().putString("outnotice", notice).apply();
    }
//获取储存是预授权通知字符串
    public String getFristNoticeStr() {
        return sharedPreferences.getString("outnotice", "");
    }

    //保存cid
    public void saveCid(String cid) {
        sharedPreferences.edit().putString("cid", cid).apply();
    }

    //获取cid
    public String getCid() {
        return sharedPreferences.getString("cid", "");
    }

    /**
     * 保存locale
     *
     * @param locale locale
     */
    public void saveLocale(String locale) {
        sharedPreferences.edit().putString("locale", locale).apply();
    }

    public String getLocale() {
        return sharedPreferences.getString("locale", "");
    }

    /**
     * 保存Time
     *
     * @param long time
     */
    public void saveTime(long time) {
        sharedPreferences.edit().putLong("time", time).apply();
    }

    public long getTime() {
        return sharedPreferences.getLong("time",0);
    }

}
