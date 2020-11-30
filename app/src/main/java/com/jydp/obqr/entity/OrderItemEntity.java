package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/28 14:50 星期二
作用 ：
*/
public class OrderItemEntity {

    /**
     * id : 25
     * merchant_id : 1
     * merchant_name : 213
     * sn : 2019112300000001
     * price : 4046
     * total : 3800
     * discount : 53
     * discount_id : 7
     * discount_name : 满489元减53元
     * discount_content : {"full":489,"minus":53}
     * discount_type : 0
     * seat_id : 1
     * seat_number : A10
     * people : 2
     * tax_rate : 8.00
     * tax_fee : 299
     * payment_id : 1
     * payment_name : 支付宝
     * staff_id : 1
     * staff_name : jinjiabin
     * opened_at : 2019-11-14 10:35:31
     * created_at : 2019-11-23 15:33:00
     * updated_at : 2019-11-23 15:33:00
     * merchant : {"id":1,"name":"213","image":"11111","phone":"123","address":"11111","type":2,"language_set":["en","ja-JP"],"tax_rate":"1.00","deleted_at":null,"created_at":"2019-06-06 14:35:06","updated_at":"2019-08-28 14:52:47"}
     * order_snapshots : [{"menu_id":1,"menu_name":"ステーキ","menu_price":2240,"menu_price_format":"2,240","count":2,"attribute_ids":["15","17"],"attribute_name":"清蒸-jp/酸-jp"}]
     */

    private int id;
    private int merchant_id;
    private String merchant_name;
    private String sn;
    private String price;
    private String total;
    private int discount;
    private int discount_id;
    private String discount_name;
    private DiscountContentBean discount_content;
    private int discount_type;
    private int seat_id;
    private String seat_number;
    private String people;
    private String tax_rate;
    private String tax_fee;
    private int payment_id;
    private String payment_name;
    private int staff_id;
    private String staff_name;
    private String opened_at;
    private String created_at;
    private String updated_at;
    private MerchantBean merchant;
    private List<OrderSnapshotsBean> order_snapshots;

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

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public DiscountContentBean getDiscount_content() {
        return discount_content;
    }

    public void setDiscount_content(DiscountContentBean discount_content) {
        this.discount_content = discount_content;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getTax_fee() {
        return tax_fee;
    }

    public void setTax_fee(String tax_fee) {
        this.tax_fee = tax_fee;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
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

    public String getOpened_at() {
        return opened_at;
    }

    public void setOpened_at(String opened_at) {
        this.opened_at = opened_at;
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

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public List<OrderSnapshotsBean> getOrder_snapshots() {
        return order_snapshots;
    }

    public void setOrder_snapshots(List<OrderSnapshotsBean> order_snapshots) {
        this.order_snapshots = order_snapshots;
    }

    public static class DiscountContentBean {
        /**
         * full : 489
         * minus : 53
         */

        private int full;
        private int minus;

        public int getFull() {
            return full;
        }

        public void setFull(int full) {
            this.full = full;
        }

        public int getMinus() {
            return minus;
        }

        public void setMinus(int minus) {
            this.minus = minus;
        }
    }

    public static class MerchantBean {
        /**
         * id : 1
         * name : 213
         * image : 11111
         * phone : 123
         * address : 11111
         * type : 2
         * language_set : ["en","ja-JP"]
         * tax_rate : 1.00
         * deleted_at : null
         * created_at : 2019-06-06 14:35:06
         * updated_at : 2019-08-28 14:52:47
         */

        private int id;
        private String name;
        private String image;
        private String phone;
        private String address;
        private int type;
        private String tax_rate;
        private Object deleted_at;
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

        public String getTax_rate() {
            return tax_rate;
        }

        public void setTax_rate(String tax_rate) {
            this.tax_rate = tax_rate;
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

        public List<String> getLanguage_set() {
            return language_set;
        }

        public void setLanguage_set(List<String> language_set) {
            this.language_set = language_set;
        }
    }

    public static class OrderSnapshotsBean {
        /**
         *
         * : 1
         * menu_name : ステーキ
         * menu_price : 2240
         * menu_price_format : 2,240
         * count : 2
         * attribute_ids : ["15","17"]
         * attribute_name : 清蒸-jp/酸-jp
         */

        private String menu_id;
        private String menu_name;
        private String menu_price;
        private String menu_price_format;
        private String count;
        private String attribute_name;
        private List<String> attribute_ids;

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getMenu_price() {
            return menu_price;
        }

        public void setMenu_price(String menu_price) {
            this.menu_price = menu_price;
        }

        public String getMenu_price_format() {
            return menu_price_format;
        }

        public void setMenu_price_format(String menu_price_format) {
            this.menu_price_format = menu_price_format;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getAttribute_name() {
            return attribute_name;
        }

        public void setAttribute_name(String attribute_name) {
            this.attribute_name = attribute_name;
        }

        public List<String> getAttribute_ids() {
            return attribute_ids;
        }

        public void setAttribute_ids(List<String> attribute_ids) {
            this.attribute_ids = attribute_ids;
        }
    }
}
