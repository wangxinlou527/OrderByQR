package com.jydp.obqr.entity;

/*
2020/4/29 14:46 星期三
作用 ：
*/
public class PrintDetailEntity {

    /**
     * id : 7
     * merchant_id : 1
     * staff_id : 1
     * ip : 192.168.1.199
     * type : 1
     * created_at : 2019-11-06 15:55:51
     * updated_at : 2019-11-06 17:14:35
     */

    private String id;
    private int merchant_id;
    private int staff_id;
    private String ip;
    private int type;
    private String created_at;
    private String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
