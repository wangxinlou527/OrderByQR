package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/28 14:10 星期二
作用 ：
*/
public class ReserveListEntity {

    /**
     * data : [{"id":25,"merchant_id":3,"name":"梵蒂冈死贵死贵","phone":"123123123","people":1,"seat_id":37,"booked_at":"2020-12-19 23:43","status":0,"note":"故作高深大哥","seat":{"id":37,"merchant_id":3,"people":8,"number":"A30","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/06/0_1575612015_5MbKVPYhjf.png","deleted_at":null,"created_at":"2019-11-28 15:40:42","updated_at":"2019-12-06 14:00:15"},"created_at":"2020-01-13 14:01:35","updated_at":"2020-04-25 15:20:48"},{"id":26,"merchant_id":3,"name":"梵蒂冈死贵死贵","phone":"123123123","people":1,"seat_id":24,"booked_at":"2020-12-19 23:43","status":0,"note":"故作高深大哥","seat":{"id":24,"merchant_id":3,"people":8,"number":"A20","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514963_ns8SwQEUjT.png","deleted_at":null,"created_at":"2019-06-19 20:45:27","updated_at":"2019-12-05 11:02:43"},"created_at":"2020-01-15 10:07:02","updated_at":"2020-01-15 10:07:02"},{"id":31,"merchant_id":3,"name":"zy","phone":"12580963","people":520,"seat_id":64,"booked_at":"2020-04-25 10:59","status":0,"note":"250","seat":{"id":64,"merchant_id":3,"people":10,"number":"C01","qr_url":"","deleted_at":null,"created_at":"2020-01-17 17:41:22","updated_at":"2020-01-17 17:41:22"},"created_at":"2020-04-22 12:10:30","updated_at":"2020-04-22 12:10:30"},{"id":32,"merchant_id":3,"name":"gg","phone":"1472580","people":520,"seat_id":64,"booked_at":"2020-04-24 11:19","status":0,"note":null,"seat":{"id":64,"merchant_id":3,"people":10,"number":"C01","qr_url":"","deleted_at":null,"created_at":"2020-01-17 17:41:22","updated_at":"2020-01-17 17:41:22"},"created_at":"2020-04-24 12:20:00","updated_at":"2020-04-24 12:20:00"},{"id":30,"merchant_id":3,"name":"pzw","phone":"123456","people":2,"seat_id":64,"booked_at":"2020-04-24 10:59","status":0,"note":"ggggggggg","seat":{"id":64,"merchant_id":3,"people":10,"number":"C01","qr_url":"","deleted_at":null,"created_at":"2020-01-17 17:41:22","updated_at":"2020-01-17 17:41:22"},"created_at":"2020-04-22 11:57:47","updated_at":"2020-04-22 12:21:13"},{"id":28,"merchant_id":3,"name":"wanghaolin","phone":"111","people":1,"seat_id":24,"booked_at":"2020-01-16 09:58","status":0,"note":"1","seat":{"id":24,"merchant_id":3,"people":8,"number":"A20","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514963_ns8SwQEUjT.png","deleted_at":null,"created_at":"2019-06-19 20:45:27","updated_at":"2019-12-05 11:02:43"},"created_at":"2020-01-16 09:48:02","updated_at":"2020-01-16 09:48:34"},{"id":27,"merchant_id":3,"name":"hhhih","phone":"32325","people":1,"seat_id":24,"booked_at":"2020-01-16 09:31","status":0,"note":"iggig","seat":{"id":24,"merchant_id":3,"people":8,"number":"A20","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514963_ns8SwQEUjT.png","deleted_at":null,"created_at":"2019-06-19 20:45:27","updated_at":"2019-12-05 11:02:43"},"created_at":"2020-01-16 09:31:23","updated_at":"2020-01-16 09:31:23"},{"id":23,"merchant_id":3,"name":"啊","phone":"11","people":1,"seat_id":24,"booked_at":"2020-01-10 15:58","status":0,"note":"轻轻巧巧","seat":{"id":24,"merchant_id":3,"people":8,"number":"A20","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514963_ns8SwQEUjT.png","deleted_at":null,"created_at":"2019-06-19 20:45:27","updated_at":"2019-12-05 11:02:43"},"created_at":"2020-01-10 15:01:06","updated_at":"2020-01-10 15:01:06"},{"id":6,"merchant_id":3,"name":"1","phone":"1","people":1,"seat_id":6,"booked_at":"2019-10-25 15:59","status":0,"note":null,"seat":{"id":6,"merchant_id":3,"people":10,"number":"A10","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514962_j9maS6RUOl.png","deleted_at":null,"created_at":"2019-06-13 13:22:21","updated_at":"2019-12-05 11:02:42"},"created_at":"2019-10-25 15:09:49","updated_at":"2020-01-04 16:16:27"},{"id":7,"merchant_id":3,"name":"1","phone":"1","people":2,"seat_id":24,"booked_at":"2019-10-25 15:59","status":0,"note":null,"seat":{"id":24,"merchant_id":3,"people":8,"number":"A20","qr_url":"https://qrorder.jydp.work/storage/manager/qrcode/201912/05/0_1575514963_ns8SwQEUjT.png","deleted_at":null,"created_at":"2019-06-19 20:45:27","updated_at":"2019-12-05 11:02:43"},"created_at":"2019-10-25 15:09:53","updated_at":"2019-10-25 15:09:53"}]
     * meta : {"pagination":{"total":26,"count":10,"per_page":10,"current_page":1,"total_pages":3,"links":{"previous":null,"next":"https://qrorder.jydp.work/staff/bookings?page=2"}}}
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
         * pagination : {"total":26,"count":10,"per_page":10,"current_page":1,"total_pages":3,"links":{"previous":null,"next":"https://qrorder.jydp.work/staff/bookings?page=2"}}
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
             * total : 26
             * count : 10
             * per_page : 10
             * current_page : 1
             * total_pages : 3
             * links : {"previous":null,"next":"https://qrorder.jydp.work/staff/bookings?page=2"}
             */

            private int total;
            private int count;
            private int per_page;
            private String current_page;
            private String total_pages;
//            private LinksBean links;

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

            public String getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(String current_page) {
                this.current_page = current_page;
            }

            public String getTotal_pages() {
                return total_pages;
            }

            public void setTotal_pages(String total_pages) {
                this.total_pages = total_pages;
            }

//            public LinksBean getLinks() {
//                return links;
//            }
//
//            public void setLinks(LinksBean links) {
//                this.links = links;
//            }
//
//            public static class LinksBean {
//                /**
//                 * previous : null
//                 * next : https://qrorder.jydp.work/staff/bookings?page=2
//                 */
//
//                private Object previous;
//                private String next;
//
//                public Object getPrevious() {
//                    return previous;
//                }
//
//                public void setPrevious(Object previous) {
//                    this.previous = previous;
//                }
//
//                public String getNext() {
//                    return next;
//                }
//
//                public void setNext(String next) {
//                    this.next = next;
//                }
//            }
        }
    }

    public static class DataBean {
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
}
