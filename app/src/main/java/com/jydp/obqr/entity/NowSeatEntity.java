package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/11 10:47 星期一
作用 ：
*/
public class NowSeatEntity {
    /**
     * data : [{"id":9,"category_id":19,"merchant_id":3,"price":3000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560220939_uFtextlxZI.jpg"],"deleted_at":null,"created_at":"2019-06-11 10:42:24","updated_at":"2019-06-11 10:59:16","price_format":"3,000","count":1,"status":2,"cart_id":1583,"attribute_ids":["56"],"attribute":"辣","menu_id":7,"name":"ポテトサラダ","language":"ja-JP","unit":null,"desc":"ポテトサラダポテトサラダポテトサラダポテトサラダ"},{"id":11,"category_id":19,"merchant_id":3,"price":1220,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222007_FUZc3gjktm.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:00:13","updated_at":"2019-06-11 11:00:13","price_format":"1,220","count":1,"status":2,"cart_id":1583,"attribute_ids":["56","59"],"attribute":"辣/加可乐","menu_id":9,"name":"枝豆","language":"ja-JP","unit":null,"desc":"枝豆枝豆枝豆枝豆枝豆枝豆"},{"id":15,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222058_RLaUGA6bVe.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:01:09","updated_at":"2019-06-11 11:01:09","price_format":"2,000","count":1,"status":2,"cart_id":1583,"attribute_ids":["none"],"attribute":null,"menu_id":12,"name":"刺身","language":"ja-JP","unit":null,"desc":"刺身刺身刺身刺身刺身刺身"}]
     * total : 6220
     * total_format : 6,220
     * number : A10
     * people : 366
     * open_at : 2020-05-11 15:26:10
     * note :
     * seat_id : 6
     * tax_rate : 10
     */

    private String total;
    private String total_format;
    private String number;
    private String people;
    private String open_at;
    private String note;
    private int seat_id;
    private String tax_rate;
    private List<DataBean> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_format() {
        return total_format;
    }

    public void setTotal_format(String total_format) {
        this.total_format = total_format;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getOpen_at() {
        return open_at;
    }

    public void setOpen_at(String open_at) {
        this.open_at = open_at;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9
         * category_id : 19
         * merchant_id : 3
         * price : 3000
         * image : ["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560220939_uFtextlxZI.jpg"]
         * deleted_at : null
         * created_at : 2019-06-11 10:42:24
         * updated_at : 2019-06-11 10:59:16
         * price_format : 3,000
         * count : 1
         * status : 2
         * cart_id : 1583
         * attribute_ids : ["56"]
         * attribute : 辣
         * menu_id : 7
         * name : ポテトサラダ
         * language : ja-JP
         * unit : null
         * desc : ポテトサラダポテトサラダポテトサラダポテトサラダ
         */

        private int id;
        private int category_id;
        private int merchant_id;
        private String price;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String price_format;
        private String count;
        private int status;
        private int cart_id;
        private String attribute;
        private int menu_id;
        private String name;
        private String language;
        private Object unit;
        private String desc;
        private List<String> image;
        private List<String> attribute_ids;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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

        public String getPrice_format() {
            return price_format;
        }

        public void setPrice_format(String price_format) {
            this.price_format = price_format;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<String> getAttribute_ids() {
            return attribute_ids;
        }

        public void setAttribute_ids(List<String> attribute_ids) {
            this.attribute_ids = attribute_ids;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", category_id=" + category_id +
                    ", merchant_id=" + merchant_id +
                    ", price='" + price + '\'' +
                    ", deleted_at=" + deleted_at +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", price_format='" + price_format + '\'' +
                    ", count='" + count + '\'' +
                    ", status=" + status +
                    ", cart_id=" + cart_id +
                    ", attribute='" + attribute + '\'' +
                    ", menu_id=" + menu_id +
                    ", name='" + name + '\'' +
                    ", language='" + language + '\'' +
                    ", unit=" + unit +
                    ", desc='" + desc + '\'' +
                    ", image=" + image +
                    ", attribute_ids=" + attribute_ids +
                    '}';
        }
    }

}
