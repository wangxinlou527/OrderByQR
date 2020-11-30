package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/11 14:03 星期一
作用 ：
*/
public class AAPrintEntity {
    /**
     * customer_info : [{"menus":[{"attributes":"","count":"1","name":"鶏せせり","price":800,"price_format":"800"},{"attributes":"","count":"1","name":"豚バラ","price":1000,"price_format":"1,000"},{"attributes":"","count":"1","name":"刺身","price":2000,"price_format":"2,000"},{"attributes":"","count":"1","name":"シロ","price":600,"price_format":"600"},{"attributes":"","count":"1","name":"イカ塩辛","price":2000,"price_format":"2,000"},{"attributes":"","count":"1","name":"砂肝","price":800,"price_format":"800"},{"attributes":"辣","count":"1","name":"枝豆","price":1200,"price_format":"1,200"}],"name":"お客様A","total_price":8400,"total_price_format":"8,400"},{"menus":[{"attributes":"辣/加蛋","count":"1","name":"唐揚げ","price":180,"price_format":"180"},{"attributes":"辣/加蛋/加可乐","count":"1","name":"唐揚げ","price":200,"price_format":"200"},{"attributes":"","count":"1","name":"南蛮","price":350,"price_format":"350"},{"attributes":"","count":"1","name":"焼き鳥","price":800,"price_format":"800"}],"name":"お客様B","total_price":1530,"total_price_format":"1,530"}]
     * merchant : {"address":"東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F","created_at":"2019-06-11 09:24:39","id":3,"image":"https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg","language_set":["ja-JP","zh-CN","en"],"name":"塚田農場","phone":"050-5265-9608","tax_rate":"5.00","type":1,"updated_at":"2020-01-17 17:40:57"}
     * order_info : {"merchant_id":3,"opened_at":"2020/05/28(金)17:53","seat_id":64,"total":9930}
     * print_time : 2020/05/28(金)17:58
     * seat_info : {"count":"11","note":"","number":"C01","time":"1590656026"}
     */

    private MerchantBean merchant;
    private OrderInfoBean order_info;
    private String print_time;
    private SeatInfoBean seat_info;
    private List<CustomerInfoBean> customer_info;

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    public SeatInfoBean getSeat_info() {
        return seat_info;
    }

    public void setSeat_info(SeatInfoBean seat_info) {
        this.seat_info = seat_info;
    }

    public List<CustomerInfoBean> getCustomer_info() {
        return customer_info;
    }

    public void setCustomer_info(List<CustomerInfoBean> customer_info) {
        this.customer_info = customer_info;
    }

    public static class MerchantBean {
        /**
         * address : 東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F
         * created_at : 2019-06-11 09:24:39
         * id : 3
         * image : https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg
         * language_set : ["ja-JP","zh-CN","en"]
         * name : 塚田農場
         * phone : 050-5265-9608
         * tax_rate : 5.00
         * type : 1
         * updated_at : 2020-01-17 17:40:57
         */

        private String address;
        private String created_at;
        private int id;
        private String image;
        private String name;
        private String phone;
        private String tax_rate;
        private int type;
        private String updated_at;
        private List<String> language_set;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getTax_rate() {
            return tax_rate;
        }

        public void setTax_rate(String tax_rate) {
            this.tax_rate = tax_rate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

    public static class OrderInfoBean {
        /**
         * merchant_id : 3
         * opened_at : 2020/05/28(金)17:53
         * seat_id : 64
         * total : 9930
         */

        private int merchant_id;
        private String opened_at;
        private int seat_id;
        private int total;

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getOpened_at() {
            return opened_at;
        }

        public void setOpened_at(String opened_at) {
            this.opened_at = opened_at;
        }

        public int getSeat_id() {
            return seat_id;
        }

        public void setSeat_id(int seat_id) {
            this.seat_id = seat_id;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class SeatInfoBean {
        /**
         * count : 11
         * note :
         * number : C01
         * time : 1590656026
         */

        private String count;
        private String note;
        private String number;
        private String time;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class CustomerInfoBean {
        /**
         * menus : [{"attributes":"","count":"1","name":"鶏せせり","price":800,"price_format":"800"},{"attributes":"","count":"1","name":"豚バラ","price":1000,"price_format":"1,000"},{"attributes":"","count":"1","name":"刺身","price":2000,"price_format":"2,000"},{"attributes":"","count":"1","name":"シロ","price":600,"price_format":"600"},{"attributes":"","count":"1","name":"イカ塩辛","price":2000,"price_format":"2,000"},{"attributes":"","count":"1","name":"砂肝","price":800,"price_format":"800"},{"attributes":"辣","count":"1","name":"枝豆","price":1200,"price_format":"1,200"}]
         * name : お客様A
         * total_price : 8400
         * total_price_format : 8,400
         */

        private String name;
        private int total_price;
        private String total_price_format;
        private List<MenusBean> menus;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public String getTotal_price_format() {
            return total_price_format;
        }

        public void setTotal_price_format(String total_price_format) {
            this.total_price_format = total_price_format;
        }

        public List<MenusBean> getMenus() {
            return menus;
        }

        public void setMenus(List<MenusBean> menus) {
            this.menus = menus;
        }

        public static class MenusBean {
            /**
             * attributes :
             * count : 1
             * name : 鶏せせり
             * price : 800
             * price_format : 800
             */

            private String attributes;
            private String count;
            private String name;
            private int price;
            private String price_format;

            public String getAttributes() {
                return attributes;
            }

            public void setAttributes(String attributes) {
                this.attributes = attributes;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getPrice_format() {
                return price_format;
            }

            public void setPrice_format(String price_format) {
                this.price_format = price_format;
            }
        }
    }
}
