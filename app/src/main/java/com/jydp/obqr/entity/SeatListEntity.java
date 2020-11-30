package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/28 15:48 星期二
作用 ：
*/
public class SeatListEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * merchant_id : 3
         * people : 10
         * number : A10
         * qr_url : https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514962_j9maS6RUOl.png
         * status : 0
         * count : 0
         * time : 0
         * created_at : 2019-06-13 13:22:21
         * updated_at : 2019-12-05 11:02:42
         */

        private String id;
        private String merchant_id;
        private String people;
        private String number;
        private String qr_url;
        private int status;
        private String count;
        private String time;
        private String created_at;
        private String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getPeople() {
            return people;
        }

        public void setPeople(String people) {
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


        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", merchant_id='" + merchant_id + '\'' +
                    ", people='" + people + '\'' +
                    ", number='" + number + '\'' +
                    ", qr_url='" + qr_url + '\'' +
                    ", status='" + status + '\'' +
                    ", count='" + count + '\'' +
                    ", time='" + time + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    '}';
        }
    }
}
