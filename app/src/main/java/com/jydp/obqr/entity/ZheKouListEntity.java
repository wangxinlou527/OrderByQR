package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/7 11:23 星期四
作用 ：
*/
public class ZheKouListEntity {


    private List<DataBeanX> data;

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * id : 4
         * merchant_id : 3
         * name : 每满100-50
         * type : 2
         * data : {"full":100,"minus":50}
         * is_open : 1
         * deleted_at : null
         * created_at : 2019-10-24 17:09:26
         * updated_at : 2019-11-15 10:17:26
         */

        private String id;
        private int merchant_id;
        private String name;
        private String type;
        private DataBean data;
        private int is_open;
        private Object deleted_at;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public int getIs_open() {
            return is_open;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
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

        public static class DataBean {
            /**
             * full : 100
             * minus : 50
             * discount: 75
             */

            private int full;
            private int minus;
            private String discount;
// "discount": 75
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

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }
        }
    }
}
