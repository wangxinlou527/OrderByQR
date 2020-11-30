package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/11 14:03 星期一
作用 ：
*/
public class HouChuPrintEntity {

    /**
     * seat_info : {"count":"7","time":"1589963691","note":"","number":"C01"}
     * menu_info : [{"id":53,"category_id":22,"merchant_id":3,"price":230,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223112_02PlvfALfD.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223114_auC18H785t.png","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223116_vBp1riiVmT.png"],"deleted_at":null,"created_at":"2019-06-11 11:18:37","updated_at":"2019-06-11 11:18:37","price_format":"230","attributes":"","menu_id":43,"name":"ごった煮","language":"ja-JP","unit":null,"desc":"ごった煮","count":1},{"id":38,"category_id":22,"merchant_id":3,"price":120,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222851_uVIdYM8rTi.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222866_32AyMCXYAv.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222869_5nFVPmBuK3.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222872_A23fQ1hOBC.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222874_qPRJ5t1P3C.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:14:38","updated_at":"2019-06-11 11:14:38","price_format":"120","attributes":"","menu_id":30,"name":"おでん","language":"ja-JP","unit":null,"desc":"おでん","count":1},{"id":24,"category_id":21,"merchant_id":3,"price":350,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222463_Gg8Ky0kcVs.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222470_1sTIxB5JY9.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222475_7PBG2fb0q4.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222478_MPlnqVOPy6.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:08:07","updated_at":"2019-06-11 11:08:07","price_format":"350","attributes":"","menu_id":19,"name":"南蛮","language":"ja-JP","unit":null,"desc":"南蛮南蛮","count":1}]
     * order_info : {"merchant_id":3,"total":700,"seat_id":64,"opened_at":"2020/05/20(木)17:34"}
     * merchant : {"id":3,"name":"塚田農場","image":"https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560219180_SIqRDTmVOJ.jpg","phone":"050-5265-9608","address":"東京都渋谷区道玄坂2-6-1　渋谷岩崎ビル3F","type":1,"language_set":["ja-JP","zh-CN","en"],"tax_rate":"5.00","deleted_at":null,"created_at":"2019-06-11 09:24:39","updated_at":"2020-01-17 17:40:57"}
     * backend_info : [{"id":35,"merchant_id":3,"staff_id":4,"ip":"192.168.1.211","type":2,"note":"后厨","deleted_at":null,"created_at":"2019-11-12 09:39:58","updated_at":"2020-01-13 19:44:00","menu":[{"id":53,"category_id":22,"merchant_id":3,"price":230,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223112_02PlvfALfD.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223114_auC18H785t.png","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223116_vBp1riiVmT.png"],"deleted_at":null,"created_at":"2019-06-11 11:18:37","updated_at":"2019-06-11 11:18:37","price_format":"230","attributes":"","menu_id":43,"name":"ごった煮","language":"ja-JP","unit":null,"desc":"ごった煮","count":1},{"id":38,"category_id":22,"merchant_id":3,"price":120,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222851_uVIdYM8rTi.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222866_32AyMCXYAv.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222869_5nFVPmBuK3.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222872_A23fQ1hOBC.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222874_qPRJ5t1P3C.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:14:38","updated_at":"2019-06-11 11:14:38","price_format":"120","attributes":"","menu_id":30,"name":"おでん","language":"ja-JP","unit":null,"desc":"おでん","count":1},{"id":24,"category_id":21,"merchant_id":3,"price":350,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222463_Gg8Ky0kcVs.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222470_1sTIxB5JY9.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222475_7PBG2fb0q4.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222478_MPlnqVOPy6.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:08:07","updated_at":"2019-06-11 11:08:07","price_format":"350","attributes":"","menu_id":19,"name":"南蛮","language":"ja-JP","unit":null,"desc":"南蛮南蛮","count":1}]}]
     * print_time : 2020/05/21(金)12:50
     */

    private SeatInfoBean seat_info;
    private OrderInfoBean order_info;
    private MerchantBean merchant;
    private String print_time;
    private List<MenuInfoBean> menu_info;
    private List<BackendInfoBean> backend_info;

    public SeatInfoBean getSeat_info() {
        return seat_info;
    }

    public void setSeat_info(SeatInfoBean seat_info) {
        this.seat_info = seat_info;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public MerchantBean getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantBean merchant) {
        this.merchant = merchant;
    }

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    public List<MenuInfoBean> getMenu_info() {
        return menu_info;
    }

    public void setMenu_info(List<MenuInfoBean> menu_info) {
        this.menu_info = menu_info;
    }

    public List<BackendInfoBean> getBackend_info() {
        return backend_info;
    }

    public void setBackend_info(List<BackendInfoBean> backend_info) {
        this.backend_info = backend_info;
    }

    public static class SeatInfoBean {
        /**
         * count : 7
         * time : 1589963691
         * note :
         * number : C01
         */

        private String count;
        private String time;
        private String note;
        private String number;

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
    }

    public static class OrderInfoBean {
        /**
         * merchant_id : 3
         * total : 700
         * seat_id : 64
         * opened_at : 2020/05/20(木)17:34
         */

        private int merchant_id;
        private int total;
        private int seat_id;
        private String opened_at;

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSeat_id() {
            return seat_id;
        }

        public void setSeat_id(int seat_id) {
            this.seat_id = seat_id;
        }

        public String getOpened_at() {
            return opened_at;
        }

        public void setOpened_at(String opened_at) {
            this.opened_at = opened_at;
        }
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
    }

    public static class MenuInfoBean {
        /**
         * id : 53
         * category_id : 22
         * merchant_id : 3
         * price : 230
         * image : ["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223112_02PlvfALfD.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223114_auC18H785t.png","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223116_vBp1riiVmT.png"]
         * deleted_at : null
         * created_at : 2019-06-11 11:18:37
         * updated_at : 2019-06-11 11:18:37
         * price_format : 230
         * attributes :
         * menu_id : 43
         * name : ごった煮
         * language : ja-JP
         * unit : null
         * desc : ごった煮
         * count : 1
         */

        private int id;
        private int category_id;
        private int merchant_id;
        private int price;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String price_format;
        private String attributes;
        private int menu_id;
        private String name;
        private String language;
        private Object unit;
        private String desc;
        private String count;
        private List<String> image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public String getPrice_format() {
            return price_format;
        }

        public void setPrice_format(String price_format) {
            this.price_format = price_format;
        }

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }

    public static class BackendInfoBean {
        /**
         * id : 35
         * merchant_id : 3
         * staff_id : 4
         * ip : 192.168.1.211
         * type : 2
         * note : 后厨
         * deleted_at : null
         * created_at : 2019-11-12 09:39:58
         * updated_at : 2020-01-13 19:44:00
         * menu : [{"id":53,"category_id":22,"merchant_id":3,"price":230,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223112_02PlvfALfD.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223114_auC18H785t.png","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223116_vBp1riiVmT.png"],"deleted_at":null,"created_at":"2019-06-11 11:18:37","updated_at":"2019-06-11 11:18:37","price_format":"230","attributes":"","menu_id":43,"name":"ごった煮","language":"ja-JP","unit":null,"desc":"ごった煮","count":1},{"id":38,"category_id":22,"merchant_id":3,"price":120,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222851_uVIdYM8rTi.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222866_32AyMCXYAv.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222869_5nFVPmBuK3.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222872_A23fQ1hOBC.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222874_qPRJ5t1P3C.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:14:38","updated_at":"2019-06-11 11:14:38","price_format":"120","attributes":"","menu_id":30,"name":"おでん","language":"ja-JP","unit":null,"desc":"おでん","count":1},{"id":24,"category_id":21,"merchant_id":3,"price":350,"image":["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222463_Gg8Ky0kcVs.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222470_1sTIxB5JY9.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222475_7PBG2fb0q4.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560222478_MPlnqVOPy6.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:08:07","updated_at":"2019-06-11 11:08:07","price_format":"350","attributes":"","menu_id":19,"name":"南蛮","language":"ja-JP","unit":null,"desc":"南蛮南蛮","count":1}]
         */

        private int id;
        private int merchant_id;
        private int staff_id;
        private String ip;
        private int type;
        private String note;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private List<MenuBean> menu;

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

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
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

        public List<MenuBean> getMenu() {
            return menu;
        }

        public void setMenu(List<MenuBean> menu) {
            this.menu = menu;
        }

        public static class MenuBean {
            /**
             * id : 53
             * category_id : 22
             * merchant_id : 3
             * price : 230
             * image : ["https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223112_02PlvfALfD.jpg","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223114_auC18H785t.png","https://qrorder.jydp.work/storage/admin/others/201906/11/1_1560223116_vBp1riiVmT.png"]
             * deleted_at : null
             * created_at : 2019-06-11 11:18:37
             * updated_at : 2019-06-11 11:18:37
             * price_format : 230
             * attributes :
             * menu_id : 43
             * name : ごった煮
             * language : ja-JP
             * unit : null
             * desc : ごった煮
             * count : 1
             */

            private int id;
            private int category_id;
            private int merchant_id;
            private int price;
            private Object deleted_at;
            private String created_at;
            private String updated_at;
            private String price_format;
            private String attributes;
            private int menu_id;
            private String name;
            private String language;
            private Object unit;
            private String desc;
            private int count;
            private List<String> image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public String getPrice_format() {
                return price_format;
            }

            public void setPrice_format(String price_format) {
                this.price_format = price_format;
            }

            public String getAttributes() {
                return attributes;
            }

            public void setAttributes(String attributes) {
                this.attributes = attributes;
            }

            public int getMenu_id() {
                return menu_id;
            }

            public void setMenu_id(int menu_id) {
                this.menu_id = menu_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public Object getUnit() {
                return unit;
            }

            public void setUnit(Object unit) {
                this.unit = unit;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }
        }
    }
}
