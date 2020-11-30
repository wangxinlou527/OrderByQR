package com.jydp.obqr.entity;

/*
2020/4/28 14:34 星期二
作用 ：
*/
public class ReserveDetailEntity {

    /**
     * id : 25
     * merchant_id : 3
     * name : 梵蒂冈死贵死贵
     * phone : 123123123
     * people : 1
     * seat_id : 37
     * booked_at : 2020-12-19 23:43
     * status : 0
     * note : 故作高深大哥
     * seat : {"id":37,"merchant_id":3,"people":8,"number":"A30","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/06/0_1575612015_5MbKVPYhjf.png","deleted_at":null,"created_at":"2019-11-28 15:40:42","updated_at":"2019-12-06 14:00:15"}
     * created_at : 2020-01-13 14:01:35
     * updated_at : 2020-04-25 15:20:48
     */

    private int id;
    private int merchant_id;
    private String name;
    private String phone;
    private String people;
    private int seat_id;
    private String booked_at;
    private String status;
    private String note;
    private SeatBean seat;
    private String created_at;
    private String updated_at;

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

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public String getBooked_at() {
        return booked_at;
    }

    public void setBooked_at(String booked_at) {
        this.booked_at = booked_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SeatBean getSeat() {
        return seat;
    }

    public void setSeat(SeatBean seat) {
        this.seat = seat;
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

    public static class SeatBean {
        /**
         * id : 37
         * merchant_id : 3
         * people : 8
         * number : A30
         * qr_url : https://qrorder.jydp.work/storage/manager/qrcode/201912/06/0_1575612015_5MbKVPYhjf.png
         * deleted_at : null
         * created_at : 2019-11-28 15:40:42
         * updated_at : 2019-12-06 14:00:15
         */

        private int id;
        private int merchant_id;
        private int people;
        private String number;
        private String qr_url;
        private Object deleted_at;
        private String created_at;
        private String updated_at;

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

        public int getPeople() {
            return people;
        }

        public void setPeople(int people) {
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
