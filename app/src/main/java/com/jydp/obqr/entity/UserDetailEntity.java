package com.jydp.obqr.entity;

import java.util.List;

/*
2020/6/4 9:53 星期四
作用 ：
*/
public class UserDetailEntity {

    /**
     * id : 4
     * username : jinjiabin
     * nickname : jinjiabin
     * email : jinjiabin@qq.com
     * phone : 1
     * avatar : https://qrorder.jydp.work/storage/manager/avatars/201906/13/4_1560417241_gq6UJPDNI3.jpg
     * merchant_id : 3
     * language : zh-CN
     * created_at : 2019-06-13 17:14:36
     * updated_at : 2020-04-24 18:29:09
     * area_ids : [15,6,5]
     * printer : {"id":35,"merchant_id":3,"staff_id":4,"ip":"192.168.1.211","type":2,"note":"后厨","deleted_at":null,"created_at":"2019-11-12 09:39:58","updated_at":"2020-01-13 19:44:00"}
     */

    private String id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private String merchant_id;
    private String language;
    private String created_at;
    private String updated_at;
    private PrinterBean printer;
    private List<Integer> area_ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public PrinterBean getPrinter() {
        return printer;
    }

    public void setPrinter(PrinterBean printer) {
        this.printer = printer;
    }

    public List<Integer> getArea_ids() {
        return area_ids;
    }

    public void setArea_ids(List<Integer> area_ids) {
        this.area_ids = area_ids;
    }

    public static class PrinterBean {
        /**
         * id : 35
         * merchant_id : 3
         * staff_id : 4
         * ip : 192.168.1.211
         * type : 2
         * note : 后厨
         * deleted_at : null
         * created_at : 2019-11-12 09:39:58
         * updated_at : 2020-01-13 19:44:00
         */

        private int id;
        private int merchant_id;
        private int staff_id;
        private String ip;
        private int type;
        private String note;
        private Object deleted_at;
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

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
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
}
