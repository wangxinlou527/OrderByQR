package com.jydp.obqr.entity;

import com.google.gson.annotations.SerializedName;

/*
2020/4/28 18:33 星期二
作用 ：
*/
public class PutMoneyEntity {

    /**
     * id : 4
     * merchant_id : 1
     * order_id : 123456
     * price : 82464
     * type : 3
     * return : 121121
     * total : 247392
     * note : woish
     * staff_id : 1
     * staff_name : staff
     * created_at : 2020-01-17 14:11:58
     * updated_at : 2020-01-17 14:11:58
     */

    private int id;
    private int merchant_id;
    private int order_id;
    private String price;
    private int type;
    @SerializedName("return")
    private String returnX;
    private int total;
    private String note;
    private int staff_id;
    private String staff_name;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReturnX() {
        return returnX;
    }

    public void setReturnX(String returnX) {
        this.returnX = returnX;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
