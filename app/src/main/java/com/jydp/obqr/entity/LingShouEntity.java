package com.jydp.obqr.entity;

/*
2020/5/11 16:11 星期一
作用 ：
*/
public class LingShouEntity {

    /**
     * price : 10,560
     * tax_fee : 960
     * sn : 2020042100000002
     * created_at : 2020/04/21(水)17:22
     * printed_at : 2020/05/11(火)17:08
     * merchant_address : 東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F
     * phone : 050-5265-9608
     * staff_name : jiangmingyu
     * merchant_name : 塚田農場
     */

    private String price;
    private String tax_fee;
    private String sn;
    private String created_at;
    private String printed_at;
    private String merchant_address;
    private String phone;
    private String staff_name;
    private String merchant_name;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax_fee() {
        return tax_fee;
    }

    public void setTax_fee(String tax_fee) {
        this.tax_fee = tax_fee;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPrinted_at() {
        return printed_at;
    }

    public void setPrinted_at(String printed_at) {
        this.printed_at = printed_at;
    }

    public String getMerchant_address() {
        return merchant_address;
    }

    public void setMerchant_address(String merchant_address) {
        this.merchant_address = merchant_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }
}
