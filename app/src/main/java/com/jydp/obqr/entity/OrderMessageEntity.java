package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/9 14:55 星期六
作用 ：
*/
public class OrderMessageEntity {

    /**
     * data : [{"id":26,"category_id":20,"merchant_id":3,"price":800,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222800_jSSLXQNDDD.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:13:28","updated_at":"2019-06-11 11:13:28","price_format":"800","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"鸡颈肉串","unit":null,"desc":"鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串","attribute":null},{"id":36,"category_id":20,"merchant_id":3,"price":1000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560223004_9KX9exGq0E.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:17:00","updated_at":"2019-06-11 11:17:00","price_format":"1,000","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"猪五花","unit":null,"desc":"猪五花猪五花猪五花猪五花猪五花猪五花","attribute":null},{"id":34,"category_id":20,"merchant_id":3,"price":1200,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222979_9Bk3DbkNuh.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:16:27","updated_at":"2019-06-11 11:16:27","price_format":"1,200","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"烤鲭鱼","unit":null,"desc":"烤鲭鱼烤鲭鱼烤鲭鱼烤鲭鱼烤鲭鱼烤鲭鱼","attribute":null},{"id":9,"category_id":19,"merchant_id":3,"price":1200,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222007_FUZc3gjktm.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:00:13","updated_at":"2019-06-11 11:00:13","price_format":"1,200","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"毛豆","unit":null,"desc":"毛豆毛豆毛豆毛豆毛豆毛豆","attribute":null},{"id":23,"category_id":20,"merchant_id":3,"price":800,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222735_mEcAo7GA40.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:12:19","updated_at":"2019-06-11 11:12:19","price_format":"800","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"鸡肉串","unit":null,"desc":"鸡肉串鸡肉串鸡肉串鸡肉串鸡肉串鸡肉串","attribute":null},{"id":10,"category_id":21,"merchant_id":3,"price":1300,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222010_v88AnJPo5G.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:00:26","updated_at":"2019-06-11 11:00:26","price_format":"1,300","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"油豆腐","unit":"份","desc":"油豆腐油豆腐","attribute":null},{"id":31,"category_id":20,"merchant_id":3,"price":800,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222887_hpCWgIf2eO.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:14:53","updated_at":"2019-06-11 11:14:53","price_format":"800","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"鸡胗","unit":null,"desc":"鸡胗鸡胗鸡胗鸡胗鸡胗鸡胗","attribute":null},{"id":22,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222690_p1OxfIj775.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:11:38","updated_at":"2019-06-11 11:11:38","price_format":"2,000","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"盐渍生鲣鱼","unit":null,"desc":"盐渍生鲣鱼盐渍生鲣鱼盐渍生鲣鱼盐渍生鲣鱼盐渍生鲣鱼盐渍生鲣鱼","attribute":null},{"id":17,"category_id":21,"merchant_id":3,"price":210,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222351_K95O8pVtOe.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222357_Tm75aU8PKF.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:06:00","updated_at":"2019-06-11 11:06:00","price_format":"210","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"用酱油腌过后再炸的鸡","unit":"份","desc":"用酱油腌过后再炸的鸡","attribute":null},{"id":40,"category_id":20,"merchant_id":3,"price":600,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560223067_ycq3JSTXzY.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:17:59","updated_at":"2019-06-11 11:17:59","price_format":"600","count":1,"status":1,"cart_id":1574,"attribute_ids":["none"],"name":"猪大肠","unit":null,"desc":"猪大肠猪大肠猪大肠猪大肠猪大肠猪大肠","attribute":null}]
     * total : 9910
     * total_format : 9,910
     * cart_id : 1574
     * seat_id : 6
     * number : A10
     * people : 7
     * open_at : 2020-05-08 16:32:01
     */

    private int total;
    private String total_format;
    private int cart_id;
    private int seat_id;
    private String number;
    private String people;
    private String open_at;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTotal_format() {
        return total_format;
    }

    public void setTotal_format(String total_format) {
        this.total_format = total_format;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 26
         * category_id : 20
         * merchant_id : 3
         * price : 800
         * image : ["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222800_jSSLXQNDDD.jpeg"]
         * deleted_at : null
         * created_at : 2019-06-11 11:13:28
         * updated_at : 2019-06-11 11:13:28
         * price_format : 800
         * count : 1
         * status : 1
         * cart_id : 1574
         * attribute_ids : ["none"]
         * name : 鸡颈肉串
         * unit : null
         * desc : 鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串鸡颈肉串
         * attribute : null
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
        private String name;
        private Object unit;
        private String desc;
        private Object attribute;
        private List<String> image;
        private List<String> attribute_ids;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public Object getAttribute() {
            return attribute;
        }

        public void setAttribute(Object attribute) {
            this.attribute = attribute;
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
    }
}
