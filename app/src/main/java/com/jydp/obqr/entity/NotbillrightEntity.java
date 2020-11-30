package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/27 15:18 星期一
作用 ：
*/
public class NotbillrightEntity {

    /**
     * data : [{"id":191,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042500000002,"price":3300,"price_format":"3,300","total":3000,"total_format":"3,000","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":37,"seat_number":"A30","people":255,"tax_rate":"10.00","tax_fee":300,"tax_fee_format":"300","payment_id":2,"payment_name":"支付宝","opened_at":"2020-04-24 16:48:03","created_at":"2020-04-25 15:08:04","updated_at":"2020-04-25 15:08:04"},{"id":190,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042500000001,"price":9790,"price_format":"9,790","total":8900,"total_format":"8,900","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":6,"seat_number":"A10","people":253,"tax_rate":"10.00","tax_fee":890,"tax_fee_format":"890","payment_id":1,"payment_name":"現金","opened_at":"2020-04-24 16:47:43","created_at":"2020-04-25 15:05:07","updated_at":"2020-04-25 15:05:07"},{"id":189,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042400000001,"price":13200,"price_format":"13,200","total":12000,"total_format":"12,000","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":24,"seat_number":"A20","people":10,"tax_rate":"10.00","tax_fee":1200,"tax_fee_format":"1,200","payment_id":1,"payment_name":"現金","opened_at":"2020-04-24 15:40:10","created_at":"2020-04-24 16:44:59","updated_at":"2020-04-24 16:44:59"},{"id":188,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042200000003,"price":12760,"price_format":"12,760","total":11600,"total_format":"11,600","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":6,"seat_number":"A10","people":52,"tax_rate":"10.00","tax_fee":1160,"tax_fee_format":"1,160","payment_id":1,"payment_name":"現金","opened_at":"2020-04-22 12:37:26","created_at":"2020-04-22 17:37:21","updated_at":"2020-04-22 17:37:21"},{"id":187,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042200000002,"price":6600,"price_format":"6,600","total":6000,"total_format":"6,000","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":6,"seat_number":"A10","people":52,"tax_rate":"10.00","tax_fee":600,"tax_fee_format":"600","payment_id":1,"payment_name":"現金","opened_at":"2020-04-22 11:36:59","created_at":"2020-04-22 12:35:27","updated_at":"2020-04-22 12:35:27"},{"id":186,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042200000001,"price":7590,"price_format":"7,590","total":13800,"total_format":"13,800","discount":6900,"discount_id":4,"discount_name":"每满100-50","discount_content":{"full":100,"minus":50},"discount_type":2,"seat_id":24,"seat_number":"A20","people":63,"tax_rate":"10.00","tax_fee":690,"tax_fee_format":"690","payment_id":1,"payment_name":"現金","opened_at":"2020-04-22 11:31:41","created_at":"2020-04-22 11:33:09","updated_at":"2020-04-22 11:33:09"},{"id":185,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042100000003,"price":15400,"price_format":"15,400","total":14000,"total_format":"14,000","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":6,"seat_number":"A10","people":2,"tax_rate":"10.00","tax_fee":1400,"tax_fee_format":"1,400","payment_id":1,"payment_name":"現金","opened_at":"2020-04-21 17:29:53","created_at":"2020-04-21 17:58:26","updated_at":"2020-04-21 17:58:26"},{"id":184,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042100000002,"price":10560,"price_format":"10,560","total":9600,"total_format":"9,600","discount":0,"discount_id":null,"discount_name":null,"discount_content":null,"discount_type":null,"seat_id":6,"seat_number":"A10","people":7,"tax_rate":"10.00","tax_fee":960,"tax_fee_format":"960","payment_id":1,"payment_name":"現金","opened_at":"2020-04-21 17:18:12","created_at":"2020-04-21 17:22:31","updated_at":"2020-04-21 17:22:31"},{"id":183,"merchant_id":3,"merchant_name":"塚田農場","sn":2020042100000001,"price":8042,"price_format":"8,042","total":7410,"total_format":"7,410","discount":99,"discount_id":2,"discount_name":"满100-99","discount_content":{"full":100,"minus":99},"discount_type":0,"seat_id":24,"seat_number":"A20","people":3,"tax_rate":"10.00","tax_fee":731,"tax_fee_format":"731","payment_id":1,"payment_name":"現金","opened_at":"2020-04-21 14:48:33","created_at":"2020-04-21 14:53:50","updated_at":"2020-04-21 14:53:50"}]
     * meta : {"pagination":{"total":9,"count":9,"per_page":20,"current_page":1,"total_pages":1,"links":null}}
     */

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * pagination : {"total":9,"count":9,"per_page":20,"current_page":1,"total_pages":1,"links":null}
         */

        private PaginationBean pagination;

        public PaginationBean getPagination() {
            return pagination;
        }

        public void setPagination(PaginationBean pagination) {
            this.pagination = pagination;
        }

        public static class PaginationBean {
            /**
             * total : 9
             * count : 9
             * per_page : 20
             * current_page : 1
             * total_pages : 1
             * links : null
             */

            private int total;
            private int count;
            private int per_page;
            private int current_page;
            private int total_pages;
            private Object links;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getTotal_pages() {
                return total_pages;
            }

            public void setTotal_pages(int total_pages) {
                this.total_pages = total_pages;
            }

            public Object getLinks() {
                return links;
            }

            public void setLinks(Object links) {
                this.links = links;
            }
        }
    }

    public static class DataBean {
        /**
         * id : 191
         * merchant_id : 3
         * merchant_name : 塚田農場
         * sn : 2020042500000002
         * price : 3300
         * price_format : 3,300
         * total : 3000
         * total_format : 3,000
         * discount : 0
         * discount_id : null
         * discount_name : null
         * discount_content : null
         * discount_type : null
         * seat_id : 37
         * seat_number : A30
         * people : 255
         * tax_rate : 10.00
         * tax_fee : 300
         * tax_fee_format : 300
         * payment_id : 2
         * payment_name : 支付宝
         * opened_at : 2020-04-24 16:48:03
         * created_at : 2020-04-25 15:08:04
         * updated_at : 2020-04-25 15:08:04
         */

        private String id;
        private int merchant_id;
        private String merchant_name;
        private long sn;
        private String price;
        private String price_format;
        private int total;
        private String total_format;
        private int discount;
        private Object discount_id;
        private Object discount_name;
        private Object discount_content;
        private Object discount_type;
        private int seat_id;
        private String seat_number;
        private String people;
        private String tax_rate;
        private int tax_fee;
        private String tax_fee_format;
        private int payment_id;
        private String payment_name;
        private String opened_at;
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

        public String getMerchant_name() {
            return merchant_name;
        }

        public void setMerchant_name(String merchant_name) {
            this.merchant_name = merchant_name;
        }

        public long getSn() {
            return sn;
        }

        public void setSn(long sn) {
            this.sn = sn;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_format() {
            return price_format;
        }

        public void setPrice_format(String price_format) {
            this.price_format = price_format;
        }

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

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public Object getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(Object discount_id) {
            this.discount_id = discount_id;
        }

        public Object getDiscount_name() {
            return discount_name;
        }

        public void setDiscount_name(Object discount_name) {
            this.discount_name = discount_name;
        }

        public Object getDiscount_content() {
            return discount_content;
        }

        public void setDiscount_content(Object discount_content) {
            this.discount_content = discount_content;
        }

        public Object getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(Object discount_type) {
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

        public int getTax_fee() {
            return tax_fee;
        }

        public void setTax_fee(int tax_fee) {
            this.tax_fee = tax_fee;
        }

        public String getTax_fee_format() {
            return tax_fee_format;
        }

        public void setTax_fee_format(String tax_fee_format) {
            this.tax_fee_format = tax_fee_format;
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
    }
}
