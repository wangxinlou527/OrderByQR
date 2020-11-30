package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/29 9:24 星期三
作用 ：
*/
public class MoneyPrintEntity {

    /**
     * merchant : {"id":1,"name":"测试餐厅122","image":"default/tzdyh.jpg","phone":"7654321123","address":"拱墅区万达广场","type":1,"language_set":["zh-CN","en","ja-JP"],"tax_rate":"0.00","deleted_at":null,"created_at":"2020-01-13 15:39:46","updated_at":"2020-01-14 20:44:19","payments":[{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2020-01-16 19:18:08","updated_at":"2020-01-16 19:18:08","total":0,"pivot":{"merchant_id":1,"payment_id":1}},{"id":2,"name":"wechatpay","box_type":1,"deleted_at":null,"created_at":null,"updated_at":null,"total":0,"pivot":{"merchant_id":1,"payment_id":2}}]}
     * payments : [{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2020-01-16 19:18:08","updated_at":"2020-01-16 19:18:08","total":0,"pivot":{"merchant_id":1,"payment_id":1}},{"id":2,"name":"wechatpay","box_type":1,"deleted_at":null,"created_at":null,"updated_at":null,"total":0,"pivot":{"merchant_id":1,"payment_id":2}}]
     * check_input : 82464
     * total : 0
     * date : 2020-01-17 14:11 - 2020-01-17 15:51
     */

    private MerchantBean merchant;
    private String check_input;
    private String check_input_format;
    private String total;
    private String total_format;
    private String date;
    private List<PaymentsBeanX> payments;

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public String getCheck_input() {
        return check_input;
    }

    public void setCheck_input(String check_input) {
        this.check_input = check_input;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheck_input_format() {
        return check_input_format;
    }

    public void setCheck_input_format(String check_input_format) {
        this.check_input_format = check_input_format;
    }

    public String getTotal_format() {
        return total_format;
    }

    public void setTotal_format(String total_format) {
        this.total_format = total_format;
    }

    public List<PaymentsBeanX> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsBeanX> payments) {
        this.payments = payments;
    }

    public static class MerchantBean {
        /**
         * id : 1
         * name : 测试餐厅122
         * image : default/tzdyh.jpg
         * phone : 7654321123
         * address : 拱墅区万达广场
         * type : 1
         * language_set : ["zh-CN","en","ja-JP"]
         * tax_rate : 0.00
         * deleted_at : null
         * created_at : 2020-01-13 15:39:46
         * updated_at : 2020-01-14 20:44:19
         * payments : [{"id":1,"name":"現金","box_type":2,"deleted_at":null,"created_at":"2020-01-16 19:18:08","updated_at":"2020-01-16 19:18:08","total":0,"pivot":{"merchant_id":1,"payment_id":1}},{"id":2,"name":"wechatpay","box_type":1,"deleted_at":null,"created_at":null,"updated_at":null,"total":0,"pivot":{"merchant_id":1,"payment_id":2}}]
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
        private List<PaymentsBean> payments;

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
             * created_at : 2020-01-16 19:18:08
             * updated_at : 2020-01-16 19:18:08
             * total : 0
             * pivot : {"merchant_id":1,"payment_id":1}
             */

            private int id;
            private String name;
            private int box_type;
            private Object deleted_at;
            private String created_at;
            private String updated_at;
            private String total;
            private PivotBean pivot;

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

            public int getBox_type() {
                return box_type;
            }

            public void setBox_type(int box_type) {
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

            public PivotBean getPivot() {
                return pivot;
            }

            public void setPivot(PivotBean pivot) {
                this.pivot = pivot;
            }

            public static class PivotBean {
                /**
                 * merchant_id : 1
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
         * created_at : 2020-01-16 19:18:08
         * updated_at : 2020-01-16 19:18:08
         * total : 0
         * pivot : {"merchant_id":1,"payment_id":1}
         */

        private int id;
        private String name;
        private int box_type;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String total;
        private String total_format;
        private PivotBeanX pivot;

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

        public int getBox_type() {
            return box_type;
        }

        public void setBox_type(int box_type) {
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
             * merchant_id : 1
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
