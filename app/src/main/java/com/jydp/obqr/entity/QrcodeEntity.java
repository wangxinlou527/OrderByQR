package com.jydp.obqr.entity;

/*
2020/4/28 16:09 星期二
作用 ：
*/
public class QrcodeEntity {

    /**
     * number : C01
     * merchant_name : 塚田農場
     * print_time : 2020/04/28(水)16:55
     * path : https://jydp-staging.oss-ap-northeast-1.aliyuncs.com/manager/qrcode/202004/28/lvOW175eDrC3NUSnTgXxVOvCJvYbZR3nNTxSJ2W1.png
     */

    private String number;
    private String merchant_name;
    private String print_time;
    private String path;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
