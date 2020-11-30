package com.jydp.obqr.entity;

import java.util.List;

/*
2020/6/19 15:19 星期五
作用 ：
*/
public class ShoppingEntity {

    /**
     * id : 3
     * name : 塚田農場
     * image : https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg
     * phone : 050-5265-9608
     * address : 東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F
     * type : 2
     * language_set : ["ja-JP","zh-CN","en"]
     * created_at : 2019-06-11 09:24:39
     * updated_at : 2020-06-16 15:39:36
     */

    private int id;
    private String name;
    private String image;
    private String phone;
    private String address;
    private int type;
    private String created_at;
    private String updated_at;
    private List<String> language_set;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<String> getLanguage_set() {
        return language_set;
    }

    public void setLanguage_set(List<String> language_set) {
        this.language_set = language_set;
    }
}
