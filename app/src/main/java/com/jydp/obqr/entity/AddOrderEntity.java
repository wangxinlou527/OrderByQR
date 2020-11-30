package com.jydp.obqr.entity;

/*
2020/4/28 17:29 星期二
作用 ：
*/
public class AddOrderEntity {

    /**
     * id : 6
     * merchant_id : 1
     * name : 张三
     * phone : 123456
     * people : 5
     * seat_id : 1
     * booked_at : 2019-10-05 10:05
     * status : 0
     * note : 我们一定准时
     * created_at : 2019-09-16 14:24:58
     * updated_at : 2019-09-16 14:24:58
     */

    private int id;
    private int merchant_id;
    private String name;
    private String phone;
    private int people;
    private int seat_id;
    private String booked_at;
    private int status;
    private String note;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getBooked_at() {
        return booked_at;
    }

    public void setBooked_at(String booked_at) {
        this.booked_at = booked_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
