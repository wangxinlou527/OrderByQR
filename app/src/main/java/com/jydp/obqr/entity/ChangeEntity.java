package com.jydp.obqr.entity;

/*
2020/5/22 19:12 星期五
作用 ：
*/
public class ChangeEntity {

    /**
     * id : 66
     * merchant_id : 3
     * name : 张三
     * phone : 123456
     * people : 15
     * seat_id : 64
     * booked_at : 2020-05-25 18:56
     * status : 0
     * note : 我们一定准时1
     * seat : {"id":64,"merchant_id":3,"people":10,"number":"C01","qr_url":"","deleted_at":null,"created_at":"2020-01-17 17:41:22","updated_at":"2020-01-17 17:41:22"}
     * created_at : 2020-05-22 19:57:19
     * updated_at : 2020-05-22 20:07:58
     */

    private int id;
    private int merchant_id;
    private String name;
    private String phone;
    private int people;
    private int seat_id;
    private String booked_at;
    private int status;
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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         * id : 64
         * merchant_id : 3
         * people : 10
         * number : C01
         * qr_url :
         * deleted_at : null
         * created_at : 2020-01-17 17:41:22
         * updated_at : 2020-01-17 17:41:22
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
