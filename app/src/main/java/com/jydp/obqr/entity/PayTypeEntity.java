package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/7 11:52 星期四
作用 ：
*/
public class PayTypeEntity {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * id : 1
         * merchant_id : 1
         * name : 支付宝
         * box_type : 0
         * deleted_at : null
         * created_at : 2019-11-23 14:16:08
         * updated_at : 2019-11-23 14:16:08
         */

        public String id;
        public int merchant_id;
        public String name;
        public int box_type;
        public Object deleted_at;
        public String created_at;
        public String updated_at;


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

    }

}
