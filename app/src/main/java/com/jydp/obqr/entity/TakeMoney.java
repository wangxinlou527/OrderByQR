package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/28 18:43 星期二
作用 ：
*/
public class TakeMoney {
    /**
     * merchant : {"id":3,"name":"塚田農場","image":"https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg","phone":"050-5265-9608","address":"東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F","type":1,"language_set":["ja-JP","zh-CN","en"],"tax_rate":"5.00","deleted_at":null,"created_at":"2019-06-11 09:24:39","updated_at":"2020-01-17 17:40:57","payments":[{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2019-11-26 11:34:41","updated_at":"2020-01-14 09:11:00","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":1}},{"id":2,"name":"支付宝","box_type":0,"deleted_at":null,"created_at":"2019-11-26 11:34:46","updated_at":"2019-11-26 11:34:46","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":2}},{"id":3,"name":"微信支付","box_type":0,"deleted_at":null,"created_at":"2019-11-27 09:48:40","updated_at":"2019-11-27 09:48:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":3}},{"id":4,"name":"VISA","box_type":0,"deleted_at":null,"created_at":"2019-11-27 10:55:43","updated_at":"2019-11-27 10:55:43","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":4}},{"id":5,"name":"PayPay","box_type":0,"deleted_at":null,"created_at":"2020-01-14 09:10:40","updated_at":"2020-01-14 09:10:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":5}}]}
     * payments : [{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2019-11-26 11:34:41","updated_at":"2020-01-14 09:11:00","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":1}},{"id":2,"name":"支付宝","box_type":0,"deleted_at":null,"created_at":"2019-11-26 11:34:46","updated_at":"2019-11-26 11:34:46","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":2}},{"id":3,"name":"微信支付","box_type":0,"deleted_at":null,"created_at":"2019-11-27 09:48:40","updated_at":"2019-11-27 09:48:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":3}},{"id":4,"name":"VISA","box_type":0,"deleted_at":null,"created_at":"2019-11-27 10:55:43","updated_at":"2019-11-27 10:55:43","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":4}},{"id":5,"name":"PayPay","box_type":0,"deleted_at":null,"created_at":"2020-01-14 09:10:40","updated_at":"2020-01-14 09:10:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":5}}]
     * staff_name : jiangmingyu
     * check_input : 29200
     * check_input_format : 29,200
     * total : 0
     * total_format : 0
     * price : 100
     * price_format : 100
     * date : 2020-05-19 18:09 - 2020-05-19 18:27
     * note : 123
     */

    private MerchantBean merchant;
    private String staff_name;
    private String check_input;
    private String check_input_format;
    private String total;
    private String total_format;
    private String price;
    private String price_format;
    private String date;
    private String note;
    private List<PaymentsBeanX> payments;

    private int staff_id;
    private int is_closed;

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(int is_closed) {
        this.is_closed = is_closed;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getCheck_input() {
        return check_input;
    }

    public void setCheck_input(String check_input) {
        this.check_input = check_input;
    }

    public String getCheck_input_format() {
        return check_input_format;
    }

    public void setCheck_input_format(String check_input_format) {
        this.check_input_format = check_input_format;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<PaymentsBeanX> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsBeanX> payments) {
        this.payments = payments;
    }

    public static class MerchantBean {
        /**
         * id : 3
         * name : 塚田農場
         * image : https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg
         * phone : 050-5265-9608
         * address : 東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F
         * type : 1
         * language_set : ["ja-JP","zh-CN","en"]
         * tax_rate : 5.00
         * deleted_at : null
         * created_at : 2019-06-11 09:24:39
         * updated_at : 2020-01-17 17:40:57
         * payments : [{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2019-11-26 11:34:41","updated_at":"2020-01-14 09:11:00","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":1}},{"id":2,"name":"支付宝","box_type":0,"deleted_at":null,"created_at":"2019-11-26 11:34:46","updated_at":"2019-11-26 11:34:46","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":2}},{"id":3,"name":"微信支付","box_type":0,"deleted_at":null,"created_at":"2019-11-27 09:48:40","updated_at":"2019-11-27 09:48:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":3}},{"id":4,"name":"VISA","box_type":0,"deleted_at":null,"created_at":"2019-11-27 10:55:43","updated_at":"2019-11-27 10:55:43","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":4}},{"id":5,"name":"PayPay","box_type":0,"deleted_at":null,"created_at":"2020-01-14 09:10:40","updated_at":"2020-01-14 09:10:40","total":0,"total_format":"0","pivot":{"merchant_id":3,"payment_id":5}}]
         */

        private String id;
        private String name;
        private String image;
        private String phone;
        private String address;
        private String type;
        private String tax_rate;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private List<String> language_set;
        private List<PaymentsBean> payments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
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

        public List<PaymentsBean> getPayments() {
            return payments;
        }

        public void setPayments(List<PaymentsBean> payments) {
            this.payments = payments;
        }

        public static class PaymentsBean {
            /**
             * id : 1
             * name : 現金
             * box_type : 2
             * deleted_at : null
             * created_at : 2019-11-26 11:34:41
             * updated_at : 2020-01-14 09:11:00
             * total : 0
             * total_format : 0
             * pivot : {"merchant_id":3,"payment_id":1}
             */

            private String id;
            private String name;
            private String box_type;
            private Object deleted_at;
            private String created_at;
            private String updated_at;
            private String total;
            private String total_format;
            private PivotBean pivot;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBox_type() {
                return box_type;
            }

            public void setBox_type(String box_type) {
                this.box_type = box_type;
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

            public PivotBean getPivot() {
                return pivot;
            }

            public void setPivot(PivotBean pivot) {
                this.pivot = pivot;
            }

            public static class PivotBean {
                /**
                 * merchant_id : 3
                 * payment_id : 1
                 */

                private int merchant_id;
                private int payment_id;

                public int getMerchant_id() {
                    return merchant_id;
                }

                public void setMerchant_id(int merchant_id) {
                    this.merchant_id = merchant_id;
                }

                public int getPayment_id() {
                    return payment_id;
                }

                public void setPayment_id(int payment_id) {
                    this.payment_id = payment_id;
                }
            }
        }
    }

    public static class PaymentsBeanX {
        /**
         * id : 1
         * name : 現金
         * box_type : 2
         * deleted_at : null
         * created_at : 2019-11-26 11:34:41
         * updated_at : 2020-01-14 09:11:00
         * total : 0
         * total_format : 0
         * pivot : {"merchant_id":3,"payment_id":1}
         */

        private String id;
        private String name;
        private String box_type;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String total;
        private String total_format;
        private PivotBeanX pivot;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBox_type() {
            return box_type;
        }

        public void setBox_type(String box_type) {
            this.box_type = box_type;
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

        public PivotBeanX getPivot() {
            return pivot;
        }

        public void setPivot(PivotBeanX pivot) {
            this.pivot = pivot;
        }

        public static class PivotBeanX {
            /**
             * merchant_id : 3
             * payment_id : 1
             */

            private int merchant_id;
            private int payment_id;

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public int getPayment_id() {
                return payment_id;
            }

            public void setPayment_id(int payment_id) {
                this.payment_id = payment_id;
            }
        }
    }
}
