package com.jydp.obqr.entity;

import java.util.List;

/*
2020/6/19 17:08 星期五
作用 ：
*/
public class RejectedEntity {

    /**
     * seat_info : {"id":64,"merchant_id":3,"people":10,"number":"C01","qr_url":"https://jydp-staging.oss-ap-northeast-1.aliyuncs.com/manager/qrcode/202006/16/bRNymxiL5OqfAsaotxKonex0bc5x3uKUnv904Dha.png","deleted_at":null,"created_at":"2020-01-17 17:41:22","updated_at":"2020-06-16 15:39:46","count":2}
     * menu_info : {"id":22,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222690_p1OxfIj775.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:11:38","updated_at":"2019-06-11 11:11:38","name":"酒盗","attributes":"","price_format":"2,000"}
     * count : 1
     * before_count : 2
     * after_count : 1
     * printer : {"id":35,"merchant_id":3,"staff_id":4,"ip":"192.168.199.213","type":2,"note":"后厨","deleted_at":null,"created_at":"2019-11-12 09:39:58","updated_at":"2020-06-06 10:27:07"}
     * attributes :
     * print_time : 2020/06/19(土)18:07
     */

    private SeatInfoBean seat_info;
    private MenuInfoBean menu_info;
    private String count;
    private int before_count;
    private int after_count;
    private PrinterBean printer;
    private String attributes;
    private String print_time;

    public SeatInfoBean getSeat_info() {
        return seat_info;
    }

    public void setSeat_info(SeatInfoBean seat_info) {
        this.seat_info = seat_info;
    }

    public MenuInfoBean getMenu_info() {
        return menu_info;
    }

    public void setMenu_info(MenuInfoBean menu_info) {
        this.menu_info = menu_info;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getBefore_count() {
        return before_count;
    }

    public void setBefore_count(int before_count) {
        this.before_count = before_count;
    }

    public int getAfter_count() {
        return after_count;
    }

    public void setAfter_count(int after_count) {
        this.after_count = after_count;
    }

    public PrinterBean getPrinter() {
        return printer;
    }

    public void setPrinter(PrinterBean printer) {
        this.printer = printer;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    public static class SeatInfoBean {
        /**
         * id : 64
         * merchant_id : 3
         * people : 10
         * number : C01
         * qr_url : https://jydp-staging.oss-ap-northeast-1.aliyuncs.com/manager/qrcode/202006/16/bRNymxiL5OqfAsaotxKonex0bc5x3uKUnv904Dha.png
         * deleted_at : null
         * created_at : 2020-01-17 17:41:22
         * updated_at : 2020-06-16 15:39:46
         * count : 2
         */

        private int id;
        private int merchant_id;
        private int people;
        private String number;
        private String qr_url;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private int count;

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

        public int getPeople() {
            return people;
        }

        public void setPeople(int people) {
            this.people = people;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getQr_url() {
            return qr_url;
        }

        public void setQr_url(String qr_url) {
            this.qr_url = qr_url;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static class MenuInfoBean {
        /**
         * id : 22
         * category_id : 19
         * merchant_id : 3
         * price : 2000
         * image : ["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222690_p1OxfIj775.jpeg"]
         * deleted_at : null
         * created_at : 2019-06-11 11:11:38
         * updated_at : 2019-06-11 11:11:38
         * name : 酒盗
         * attributes :
         * price_format : 2,000
         */

        private int id;
        private int category_id;
        private int merchant_id;
        private int price;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String name;
        private String attributes;
        private String price_format;
        private List<String> image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }

        public String getPrice_format() {
            return price_format;
        }

        public void setPrice_format(String price_format) {
            this.price_format = price_format;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }

    public static class PrinterBean {
        /**
         * id : 35
         * merchant_id : 3
         * staff_id : 4
         * ip : 192.168.199.213
         * type : 2
         * note : 后厨
         * deleted_at : null
         * created_at : 2019-11-12 09:39:58
         * updated_at : 2020-06-06 10:27:07
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
