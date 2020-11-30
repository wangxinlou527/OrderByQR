package com.jydp.obqr.entity;

import java.util.List;

/*
2020/5/29 16:47 星期五
作用 ：
*/
public class GuQingEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 19
         * merchant_id : 3
         * parent_id : null
         * is_directory : false
         * level : 0
         * path : -
         * deleted_at : null
         * created_at : 2019-06-11 10:25:09
         * updated_at : 2019-11-25 15:33:39
         * printer_id : 35
         * order_category_translate : {"id":35,"category_id":19,"name":"おつまみ","language":"ja-JP","deleted_at":null,"created_at":"2019-06-11 10:25:09","updated_at":"2019-07-09 10:46:19"}
         * menus : [{"id":7,"category_id":19,"merchant_id":3,"price":3000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560220939_uFtextlxZI.jpg"],"deleted_at":null,"created_at":"2019-06-11 10:42:24","updated_at":"2019-06-11 10:42:24","price_format":"3,000","order_menu_translate":{"id":9,"menu_id":7,"name":"ポテトサラダ","language":"ja-JP","unit":null,"desc":"ポテトサラダポテトサラダポテトサラダポテトサラダ","deleted_at":null,"created_at":"2019-06-11 10:42:24","updated_at":"2019-06-11 10:59:16"},"attributes":[{"id":56,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","pivot":{"menu_id":7,"attribute_id":56}},{"id":57,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","pivot":{"menu_id":7,"attribute_id":57}},{"id":58,"merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","pivot":{"menu_id":7,"attribute_id":58}},{"id":59,"merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","pivot":{"menu_id":7,"attribute_id":59}}],"labels":[{"id":22,"merchant_id":3,"type":1,"created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58","order_label_translate":{"id":37,"label_id":22,"name":"口味","language":"ja-JP","created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58"},"attributes":[{"id":"56","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","order_attribute_translate":{"id":87,"attribute_id":56,"name":"辣","language":"ja-JP","created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45"}},{"id":"57","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","order_attribute_translate":{"id":88,"attribute_id":57,"name":"不辣","language":"ja-JP","created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51"}}]},{"id":23,"merchant_id":3,"type":2,"created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33","order_label_translate":{"id":38,"label_id":23,"name":"加料","language":"ja-JP","created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33"},"attributes":[{"id":"58","merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","order_attribute_translate":{"id":89,"attribute_id":58,"name":"加蛋","language":"ja-JP","created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03"}},{"id":"59","merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","order_attribute_translate":{"id":90,"attribute_id":59,"name":"加可乐","language":"ja-JP","created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12"}}]}],"is_stocked":1,"stock":25},{"id":8,"category_id":19,"merchant_id":3,"price":2800,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560221936_AUTlOWVPwH.jpeg"],"deleted_at":null,"created_at":"2019-06-11 10:59:03","updated_at":"2019-06-11 10:59:03","price_format":"2,800","order_menu_translate":{"id":10,"menu_id":8,"name":"冷奴","language":"ja-JP","unit":null,"desc":"冷奴冷奴冷奴冷奴冷奴","deleted_at":null,"created_at":"2019-06-11 10:59:03","updated_at":"2019-06-11 10:59:24"},"attributes":[{"id":59,"merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","pivot":{"menu_id":8,"attribute_id":59}},{"id":58,"merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","pivot":{"menu_id":8,"attribute_id":58}}],"labels":[{"id":23,"merchant_id":3,"type":2,"created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33","order_label_translate":{"id":38,"label_id":23,"name":"加料","language":"ja-JP","created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33"},"attributes":[{"id":"58","merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","order_attribute_translate":{"id":89,"attribute_id":58,"name":"加蛋","language":"ja-JP","created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03"}},{"id":"59","merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","order_attribute_translate":{"id":90,"attribute_id":59,"name":"加可乐","language":"ja-JP","created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12"}}]}],"is_stocked":1,"stock":369},{"id":9,"category_id":19,"merchant_id":3,"price":1200,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222007_FUZc3gjktm.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:00:13","updated_at":"2019-06-11 11:00:13","price_format":"1,200","order_menu_translate":{"id":11,"menu_id":9,"name":"枝豆","language":"ja-JP","unit":null,"desc":"枝豆枝豆枝豆枝豆枝豆枝豆","deleted_at":null,"created_at":"2019-06-11 11:00:13","updated_at":"2019-06-11 11:00:13"},"attributes":[{"id":56,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","pivot":{"menu_id":9,"attribute_id":56}},{"id":57,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","pivot":{"menu_id":9,"attribute_id":57}},{"id":58,"merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","pivot":{"menu_id":9,"attribute_id":58}},{"id":59,"merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","pivot":{"menu_id":9,"attribute_id":59}}],"labels":[{"id":22,"merchant_id":3,"type":1,"created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58","order_label_translate":{"id":37,"label_id":22,"name":"口味","language":"ja-JP","created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58"},"attributes":[{"id":"56","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","order_attribute_translate":{"id":87,"attribute_id":56,"name":"辣","language":"ja-JP","created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45"}},{"id":"57","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","order_attribute_translate":{"id":88,"attribute_id":57,"name":"不辣","language":"ja-JP","created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51"}}]},{"id":23,"merchant_id":3,"type":2,"created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33","order_label_translate":{"id":38,"label_id":23,"name":"加料","language":"ja-JP","created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33"},"attributes":[{"id":"58","merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","order_attribute_translate":{"id":89,"attribute_id":58,"name":"加蛋","language":"ja-JP","created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03"}},{"id":"59","merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","order_attribute_translate":{"id":90,"attribute_id":59,"name":"加可乐","language":"ja-JP","created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12"}}]}],"is_stocked":1,"stock":0},{"id":12,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222058_RLaUGA6bVe.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:01:09","updated_at":"2019-06-11 11:01:09","price_format":"2,000","order_menu_translate":{"id":15,"menu_id":12,"name":"刺身","language":"ja-JP","unit":null,"desc":"刺身刺身刺身刺身刺身刺身","deleted_at":null,"created_at":"2019-06-11 11:01:09","updated_at":"2019-06-11 11:01:09"},"attributes":[{"id":59,"merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","pivot":{"menu_id":12,"attribute_id":59}}],"labels":[{"id":23,"merchant_id":3,"type":2,"created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33","order_label_translate":{"id":38,"label_id":23,"name":"加料","language":"ja-JP","created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33"},"attributes":[{"id":"59","merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","order_attribute_translate":{"id":90,"attribute_id":59,"name":"加可乐","language":"ja-JP","created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12"}}]}],"is_stocked":1,"stock":0},{"id":14,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222130_MqAfhOdNAz.jpg"],"deleted_at":null,"created_at":"2019-06-11 11:02:14","updated_at":"2019-06-11 11:02:14","price_format":"2,000","order_menu_translate":{"id":17,"menu_id":14,"name":"イカ塩辛","language":"ja-JP","unit":null,"desc":"イカ塩辛イカ塩辛イカ塩辛","deleted_at":null,"created_at":"2019-06-11 11:02:14","updated_at":"2019-06-11 11:02:14"},"attributes":[],"labels":[],"is_stocked":0,"stock":""},{"id":22,"category_id":19,"merchant_id":3,"price":2000,"image":["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560222690_p1OxfIj775.jpeg"],"deleted_at":null,"created_at":"2019-06-11 11:11:38","updated_at":"2019-06-11 11:11:38","price_format":"2,000","order_menu_translate":{"id":29,"menu_id":22,"name":"酒盗","language":"ja-JP","unit":null,"desc":"酒盗酒盗酒盗酒盗","deleted_at":null,"created_at":"2019-06-11 11:11:38","updated_at":"2019-06-11 11:11:38"},"attributes":[],"labels":[],"is_stocked":0,"stock":""}]
         */

        private int id;
        private int category_id;
        private int merchant_id;
        private int price;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private String price_format;
        private orderMenuTranslate order_menu_translate;
        private int stock;
        public static class orderMenuTranslate {
            private int id;
            private int menu_id;
            private String name;
            private String language;
            private String unit;
            private String desc;
            private Object deleted_at;
            private String created_at;
            private String updated_at;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
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

        public orderMenuTranslate getOrder_menu_translate() {
            return order_menu_translate;
        }

        public void setOrder_menu_translate(orderMenuTranslate order_menu_translate) {
            this.order_menu_translate = order_menu_translate;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }
}
