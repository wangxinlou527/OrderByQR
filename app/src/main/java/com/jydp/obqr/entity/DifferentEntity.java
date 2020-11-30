package com.jydp.obqr.entity;

import java.util.List;


public class DifferentEntity {
    public boolean isSelected;
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
        public boolean isSelected;
        private int id;
        private int merchant_id;
        private Object parent_id;
        private boolean is_directory;
        private int level;
        private String path;
        private Object deleted_at;
        private String created_at;
        private String updated_at;
        private int printer_id;
        private OrderCategoryTranslateBean order_category_translate;
        private List<MenusBean> menus;

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

        public Object getParent_id() {
            return parent_id;
        }

        public void setParent_id(Object parent_id) {
            this.parent_id = parent_id;
        }

        public boolean isIs_directory() {
            return is_directory;
        }

        public void setIs_directory(boolean is_directory) {
            this.is_directory = is_directory;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
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

        public int getPrinter_id() {
            return printer_id;
        }

        public void setPrinter_id(int printer_id) {
            this.printer_id = printer_id;
        }

        public OrderCategoryTranslateBean getOrder_category_translate() {
            return order_category_translate;
        }

        public void setOrder_category_translate(OrderCategoryTranslateBean order_category_translate) {
            this.order_category_translate = order_category_translate;
        }

        public List<MenusBean> getMenus() {
            return menus;
        }

        public void setMenus(List<MenusBean> menus) {
            this.menus = menus;
        }

        public static class OrderCategoryTranslateBean {
            /**
             * id : 35
             * category_id : 19
             * name : おつまみ
             * language : ja-JP
             * deleted_at : null
             * created_at : 2019-06-11 10:25:09
             * updated_at : 2019-07-09 10:46:19
             */

            private int id;
            private int category_id;
            private String name;
            private String language;
            private Object deleted_at;
            private String created_at;
            private String updated_at;

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

        public static class MenusBean {
            /**
             * id : 7
             * category_id : 19
             * merchant_id : 3
             * price : 3000
             * image : ["https://qrorder.jydp.work/storage/manager/others/201906/11/1_1560220939_uFtextlxZI.jpg"]
             * deleted_at : null
             * created_at : 2019-06-11 10:42:24
             * updated_at : 2019-06-11 10:42:24
             * price_format : 3,000
             * order_menu_translate : {"id":9,"menu_id":7,"name":"ポテトサラダ","language":"ja-JP","unit":null,"desc":"ポテトサラダポテトサラダポテトサラダポテトサラダ","deleted_at":null,"created_at":"2019-06-11 10:42:24","updated_at":"2019-06-11 10:59:16"}
             * attributes : [{"id":56,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","pivot":{"menu_id":7,"attribute_id":56}},{"id":57,"merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","pivot":{"menu_id":7,"attribute_id":57}},{"id":58,"merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","pivot":{"menu_id":7,"attribute_id":58}},{"id":59,"merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","pivot":{"menu_id":7,"attribute_id":59}}]
             * labels : [{"id":22,"merchant_id":3,"type":1,"created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58","order_label_translate":{"id":37,"label_id":22,"name":"口味","language":"ja-JP","created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58"},"attributes":[{"id":"56","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","order_attribute_translate":{"id":87,"attribute_id":56,"name":"辣","language":"ja-JP","created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45"}},{"id":"57","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","order_attribute_translate":{"id":88,"attribute_id":57,"name":"不辣","language":"ja-JP","created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51"}}]},{"id":23,"merchant_id":3,"type":2,"created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33","order_label_translate":{"id":38,"label_id":23,"name":"加料","language":"ja-JP","created_at":"2020-05-11 12:49:33","updated_at":"2020-05-11 12:49:33"},"attributes":[{"id":"58","merchant_id":3,"label_id":23,"price":10,"created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03","price_format":"10","order_attribute_translate":{"id":89,"attribute_id":58,"name":"加蛋","language":"ja-JP","created_at":"2020-05-11 12:50:03","updated_at":"2020-05-11 12:50:03"}},{"id":"59","merchant_id":3,"label_id":23,"price":20,"created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12","price_format":"20","order_attribute_translate":{"id":90,"attribute_id":59,"name":"加可乐","language":"ja-JP","created_at":"2020-05-11 12:50:12","updated_at":"2020-05-11 12:50:12"}}]}]
             * is_stocked : 1
             * stock : 25
             */

            private String id;
            private int category_id;
            private int merchant_id;
            private String price;
            private Object deleted_at;
            private String created_at;
            private String updated_at;
            private String price_format;
            private OrderMenuTranslateBean order_menu_translate;
            private int is_stocked;
            private String stock;
            private List<String> image;
            private List<AttributesBean> attributes;
            private List<LabelsBean> labels;
            private List<String> image_url;


            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

            public OrderMenuTranslateBean getOrder_menu_translate() {
                return order_menu_translate;
            }

            public void setOrder_menu_translate(OrderMenuTranslateBean order_menu_translate) {
                this.order_menu_translate = order_menu_translate;
            }

            public int getIs_stocked() {
                return is_stocked;
            }

            public void setIs_stocked(int is_stocked) {
                this.is_stocked = is_stocked;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public List<String> getImage() {
                return image;
            }

            public void setImage(List<String> image) {
                this.image = image;
            }

            public List<AttributesBean> getAttributes() {
                return attributes;
            }

            public void setAttributes(List<AttributesBean> attributes) {
                this.attributes = attributes;
            }

            public List<LabelsBean> getLabels() {
                return labels;
            }

            public void setLabels(List<LabelsBean> labels) {
                this.labels = labels;
            }

            public List<String> getImage_url() {
                return image_url;
            }

            public void setImage_url(List<String> image_url) {
                this.image_url = image_url;
            }

            public static class OrderMenuTranslateBean {
                /**
                 * id : 9
                 * menu_id : 7
                 * name : ポテトサラダ
                 * language : ja-JP
                 * unit : null
                 * desc : ポテトサラダポテトサラダポテトサラダポテトサラダ
                 * deleted_at : null
                 * created_at : 2019-06-11 10:42:24
                 * updated_at : 2019-06-11 10:59:16
                 */

                private int id;
                private int menu_id;
                private String name;
                private String language;
                private Object unit;
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

            public static class AttributesBean {
                /**
                 * id : 56
                 * merchant_id : 3
                 * label_id : 22
                 * price : 0
                 * created_at : 2020-05-11 12:49:45
                 * updated_at : 2020-05-11 12:49:45
                 * price_format : 0
                 * pivot : {"menu_id":7,"attribute_id":56}
                 */

                private int id;
                private int merchant_id;
                private int label_id;
                private int price;
                private String created_at;
                private String updated_at;
                private String price_format;
                private PivotBean pivot;

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

                public int getLabel_id() {
                    return label_id;
                }

                public void setLabel_id(int label_id) {
                    this.label_id = label_id;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
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

                public PivotBean getPivot() {
                    return pivot;
                }

                public void setPivot(PivotBean pivot) {
                    this.pivot = pivot;
                }

                public static class PivotBean {
                    /**
                     * menu_id : 7
                     * attribute_id : 56
                     */

                    private int menu_id;
                    private int attribute_id;

                    public int getMenu_id() {
                        return menu_id;
                    }

                    public void setMenu_id(int menu_id) {
                        this.menu_id = menu_id;
                    }

                    public int getAttribute_id() {
                        return attribute_id;
                    }

                    public void setAttribute_id(int attribute_id) {
                        this.attribute_id = attribute_id;
                    }
                }
            }

            public static class LabelsBean {
                /**
                 * id : 22
                 * merchant_id : 3
                 * type : 1
                 * created_at : 2020-05-11 12:46:58
                 * updated_at : 2020-05-11 12:46:58
                 * order_label_translate : {"id":37,"label_id":22,"name":"口味","language":"ja-JP","created_at":"2020-05-11 12:46:58","updated_at":"2020-05-11 12:46:58"}
                 * attributes : [{"id":"56","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45","price_format":"0","order_attribute_translate":{"id":87,"attribute_id":56,"name":"辣","language":"ja-JP","created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45"}},{"id":"57","merchant_id":3,"label_id":22,"price":0,"created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51","price_format":"0","order_attribute_translate":{"id":88,"attribute_id":57,"name":"不辣","language":"ja-JP","created_at":"2020-05-11 12:49:51","updated_at":"2020-05-11 12:49:51"}}]
                 */

                private String id;
                private String merchant_id;
                private int type;
                private String created_at;
                private String updated_at;
                private OrderLabelTranslateBean order_label_translate;
                private List<AttributesBeanX> attributes;

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

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
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

                public OrderLabelTranslateBean getOrder_label_translate() {
                    return order_label_translate;
                }

                public void setOrder_label_translate(OrderLabelTranslateBean order_label_translate) {
                    this.order_label_translate = order_label_translate;
                }

                public List<AttributesBeanX> getAttributes() {
                    return attributes;
                }

                public void setAttributes(List<AttributesBeanX> attributes) {
                    this.attributes = attributes;
                }

                public static class OrderLabelTranslateBean {
                    /**
                     * id : 37
                     * label_id : 22
                     * name : 口味
                     * language : ja-JP
                     * created_at : 2020-05-11 12:46:58
                     * updated_at : 2020-05-11 12:46:58
                     */

                    private int id;
                    private int label_id;
                    private String name;
                    private String language;
                    private String created_at;
                    private String updated_at;

                    private boolean isChecked;

                    public boolean isChecked() {
                        return isChecked;
                    }

                    public void setChecked(boolean checked) {
                        isChecked = checked;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getLabel_id() {
                        return label_id;
                    }

                    public void setLabel_id(int label_id) {
                        this.label_id = label_id;
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

                public static class AttributesBeanX {
                    /**
                     * id : 56
                     * merchant_id : 3
                     * label_id : 22
                     * price : 0
                     * created_at : 2020-05-11 12:49:45
                     * updated_at : 2020-05-11 12:49:45
                     * price_format : 0
                     * order_attribute_translate : {"id":87,"attribute_id":56,"name":"辣","language":"ja-JP","created_at":"2020-05-11 12:49:45","updated_at":"2020-05-11 12:49:45"}
                     */

                    private String id;
                    private int merchant_id;
                    private int label_id;
                    private int price;
                    private String created_at;
                    private String updated_at;
                    private String price_format;
                    private OrderAttributeTranslateBean order_attribute_translate;


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

                    public int getLabel_id() {
                        return label_id;
                    }

                    public void setLabel_id(int label_id) {
                        this.label_id = label_id;
                    }

                    public int getPrice() {
                        return price;
                    }

                    public void setPrice(int price) {
                        this.price = price;
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

                    public OrderAttributeTranslateBean getOrder_attribute_translate() {
                        return order_attribute_translate;
                    }

                    public void setOrder_attribute_translate(OrderAttributeTranslateBean order_attribute_translate) {
                        this.order_attribute_translate = order_attribute_translate;
                    }

                    public static class OrderAttributeTranslateBean {
                        /**
                         * id : 87
                         * attribute_id : 56
                         * name : 辣
                         * language : ja-JP
                         * created_at : 2020-05-11 12:49:45
                         * updated_at : 2020-05-11 12:49:45
                         */

                        private int id;
                        private int attribute_id;
                        private String name;//
                        private String language;
                        private String created_at;
                        private String updated_at;

                        private boolean isChecked;

                        public boolean isChecked() {
                            return isChecked;
                        }

                        public void setChecked(boolean checked) {
                            isChecked = checked;
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public int getAttribute_id() {
                            return attribute_id;
                        }

                        public void setAttribute_id(int attribute_id) {
                            this.attribute_id = attribute_id;
                        }

                        public String getName() {//
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
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "isSelected=" + isSelected +
                    ", id=" + id +
                    ", merchant_id=" + merchant_id +
                    ", parent_id=" + parent_id +
                    ", is_directory=" + is_directory +
                    ", level=" + level +
                    ", path='" + path + '\'' +
                    ", deleted_at=" + deleted_at +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", printer_id=" + printer_id +
                    ", order_category_translate=" + order_category_translate +
                    ", menus=" + menus +
                    '}';
        }
    }
//
//    private List<DataBean> data;
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        @Override
//        public String toString() {
//            return "DataBean{" +
//                    "id=" + id +
//                    ", merchant_id=" + merchant_id +
//                    ", parent_id=" + parent_id +
//                    ", is_directory=" + is_directory +
//                    ", level=" + level +
//                    ", path='" + path + '\'' +
//                    ", created_at='" + created_at + '\'' +
//                    ", updated_at='" + updated_at + '\'' +
//                    ", order_category_translate=" + order_category_translate +
//                    ", menus=" + menus +
//                    '}';
//        }
//
//        /**
//         * id : 1
//         * merchant_id : 1
//         * parent_id : null
//         * is_directory : false
//         * level : 0
//         * path : -
//         * created_at : 2019-06-09 13:59:40
//         * updated_at : 2019-06-09 13:59:40
//         * order_category_translate : {"id":1,"category_id":1,"name":"冷菜-JP","language":"ja-JP","created_at":"2019-06-09 13:59:40","updated_at":"2019-06-09 13:59:40"}
//         * menus : [{"id":1,"category_id":1,"merchant_id":1,"price":"1000","image":["https://qrorder.jydp.work/storage/manager/others/201906/09/1_1560060014_2P2xn37J2U.jpg","https://qrorder.jydp.work/storage/manager/others/201906/09/1_1560060067_IbfjbjDZh1.jpg","https://qrorder.jydp.work/storage/manager/others/201906/10/1_1560152112_0P7idoaA01.jpeg"],"created_at":"2019-06-09 14:00:37","updated_at":"2019-06-10 15:35:15","price_format":"1,000","order_menu_translate":{"id":1,"menu_id":1,"name":"还带","language":"ja-JP","unit":null,"desc":"还带","created_at":"2019-06-09 14:00:37","updated_at":"2019-06-09 14:00:37"},"is_stocked":0,"stock":""}]
//         */
//
//        private int id;
//        private int merchant_id;
//        private Object parent_id;
//        private boolean is_directory;
//        private int level;
//        private String path;
//        private String created_at;
//        private String updated_at;
//        private OrderCategoryTranslateBean order_category_translate;
//        private List<MenusBean> menus;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getMerchant_id() {
//            return merchant_id;
//        }
//
//        public void setMerchant_id(int merchant_id) {
//            this.merchant_id = merchant_id;
//        }
//
//        public Object getParent_id() {
//            return parent_id;
//        }
//
//        public void setParent_id(Object parent_id) {
//            this.parent_id = parent_id;
//        }
//
//        public boolean isIs_directory() {
//            return is_directory;
//        }
//
//        public void setIs_directory(boolean is_directory) {
//            this.is_directory = is_directory;
//        }
//
//        public int getLevel() {
//            return level;
//        }
//
//        public void setLevel(int level) {
//            this.level = level;
//        }
//
//        public String getPath() {
//            return path;
//        }
//
//        public void setPath(String path) {
//            this.path = path;
//        }
//
//        public String getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(String created_at) {
//            this.created_at = created_at;
//        }
//
//        public String getUpdated_at() {
//            return updated_at;
//        }
//
//        public void setUpdated_at(String updated_at) {
//            this.updated_at = updated_at;
//        }
//
//        public OrderCategoryTranslateBean getOrder_category_translate() {
//            return order_category_translate;
//        }
//
//        public void setOrder_category_translate(OrderCategoryTranslateBean order_category_translate) {
//            this.order_category_translate = order_category_translate;
//        }
//
//        public List<MenusBean> getMenus() {
//            return menus;
//        }
//
//        public void setMenus(List<MenusBean> menus) {
//            this.menus = menus;
//        }
//
//        public static class OrderCategoryTranslateBean {
//            @Override
//            public String toString() {
//                return "OrderCategoryTranslateBean{" +
//                        "id=" + id +
//                        ", category_id=" + category_id +
//                        ", name='" + name + '\'' +
//                        ", language='" + language + '\'' +
//                        ", created_at='" + created_at + '\'' +
//                        ", updated_at='" + updated_at + '\'' +
//                        '}';
//            }
//
//            /**
//             * id : 1
//             * category_id : 1
//             * name : 冷菜-JP
//             * language : ja-JP
//             * created_at : 2019-06-09 13:59:40
//             * updated_at : 2019-06-09 13:59:40
//             */
//
//            private int id;
//            private int category_id;
//            private String name;
//            private String language;
//            private String created_at;
//            private String updated_at;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCategory_id() {
//                return category_id;
//            }
//
//            public void setCategory_id(int category_id) {
//                this.category_id = category_id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getLanguage() {
//                return language;
//            }
//
//            public void setLanguage(String language) {
//                this.language = language;
//            }
//
//            public String getCreated_at() {
//                return created_at;
//            }
//
//            public void setCreated_at(String created_at) {
//                this.created_at = created_at;
//            }
//
//            public String getUpdated_at() {
//                return updated_at;
//            }
//
//            public void setUpdated_at(String updated_at) {
//                this.updated_at = updated_at;
//            }
//        }
//
//        public static class MenusBean {
//            @Override
//            public String toString() {
//                return "MenusBean{" +
//                        "id=" + id +
//                        ", category_id=" + category_id +
//                        ", merchant_id=" + merchant_id +
//                        ", price='" + price + '\'' +
//                        ", created_at='" + created_at + '\'' +
//                        ", updated_at='" + updated_at + '\'' +
//                        ", price_format='" + price_format + '\'' +
//                        ", order_menu_translate=" + order_menu_translate +
//                        ", is_stocked=" + is_stocked +
//                        ", stock='" + stock + '\'' +
//                        ", image=" + image +
//                        '}';
//            }
//
//            /**
//             * id : 1
//             * category_id : 1
//             * merchant_id : 1
//             * price : 1000
//             * image : ["https://qrorder.jydp.work/storage/manager/others/201906/09/1_1560060014_2P2xn37J2U.jpg","https://qrorder.jydp.work/storage/manager/others/201906/09/1_1560060067_IbfjbjDZh1.jpg","https://qrorder.jydp.work/storage/manager/others/201906/10/1_1560152112_0P7idoaA01.jpeg"]
//             * created_at : 2019-06-09 14:00:37
//             * updated_at : 2019-06-10 15:35:15
//             * price_format : 1,000
//             * order_menu_translate : {"id":1,"menu_id":1,"name":"还带","language":"ja-JP","unit":null,"desc":"还带","created_at":"2019-06-09 14:00:37","updated_at":"2019-06-09 14:00:37"}
//             * is_stocked : 0
//             * stock :
//             */
//
//            private int id;
//            private int category_id;
//            private int merchant_id;
//            private String price;
//            private String created_at;
//            private String updated_at;
//            private String price_format;
//            private OrderMenuTranslateBean order_menu_translate;
//            private int is_stocked;
//            private String stock;
//            private List<String> image;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCategory_id() {
//                return category_id;
//            }
//
//            public void setCategory_id(int category_id) {
//                this.category_id = category_id;
//            }
//
//            public int getMerchant_id() {
//                return merchant_id;
//            }
//
//            public void setMerchant_id(int merchant_id) {
//                this.merchant_id = merchant_id;
//            }
//
//            public String getPrice() {
//                return price;
//            }
//
//            public void setPrice(String price) {
//                this.price = price;
//            }
//
//            public String getCreated_at() {
//                return created_at;
//            }
//
//            public void setCreated_at(String created_at) {
//                this.created_at = created_at;
//            }
//
//            public String getUpdated_at() {
//                return updated_at;
//            }
//
//            public void setUpdated_at(String updated_at) {
//                this.updated_at = updated_at;
//            }
//
//            public String getPrice_format() {
//                return price_format;
//            }
//
//            public void setPrice_format(String price_format) {
//                this.price_format = price_format;
//            }
//
//            public OrderMenuTranslateBean getOrder_menu_translate() {
//                return order_menu_translate;
//            }
//
//            public void setOrder_menu_translate(OrderMenuTranslateBean order_menu_translate) {
//                this.order_menu_translate = order_menu_translate;
//            }
//
//            public int getIs_stocked() {
//                return is_stocked;
//            }
//
//            public void setIs_stocked(int is_stocked) {
//                this.is_stocked = is_stocked;
//            }
//
//            public String getStock() {
//                return stock;
//            }
//
//            public void setStock(String stock) {
//                this.stock = stock;
//            }
//
//            public List<String> getImage() {
//                return image;
//            }
//
//            public void setImage(List<String> image) {
//                this.image = image;
//            }
//
//            public static class OrderMenuTranslateBean {
//                @Override
//                public String toString() {
//                    return "OrderMenuTranslateBean{" +
//                            "id=" + id +
//                            ", menu_id=" + menu_id +
//                            ", name='" + name + '\'' +
//                            ", language='" + language + '\'' +
//                            ", unit=" + unit +
//                            ", desc='" + desc + '\'' +
//                            ", created_at='" + created_at + '\'' +
//                            ", updated_at='" + updated_at + '\'' +
//                            '}';
//                }
//
//                /**
//                 * id : 1
//                 * menu_id : 1
//                 * name : 还带
//                 * language : ja-JP
//                 * unit : null
//                 * desc : 还带
//                 * created_at : 2019-06-09 14:00:37
//                 * updated_at : 2019-06-09 14:00:37
//                 */
//
//                private int id;
//                private int menu_id;
//                private String name;
//                private String language;
//                private Object unit;
//                private String desc;
//                private String created_at;
//                private String updated_at;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public int getMenu_id() {
//                    return menu_id;
//                }
//
//                public void setMenu_id(int menu_id) {
//                    this.menu_id = menu_id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getLanguage() {
//                    return language;
//                }
//
//                public void setLanguage(String language) {
//                    this.language = language;
//                }
//
//                public Object getUnit() {
//                    return unit;
//                }
//
//                public void setUnit(Object unit) {
//                    this.unit = unit;
//                }
//
//                public String getDesc() {
//                    return desc;
//                }
//
//                public void setDesc(String desc) {
//                    this.desc = desc;
//                }
//
//                public String getCreated_at() {
//                    return created_at;
//                }
//
//                public void setCreated_at(String created_at) {
//                    this.created_at = created_at;
//                }
//
//                public String getUpdated_at() {
//                    return updated_at;
//                }
//
//                public void setUpdated_at(String updated_at) {
//                    this.updated_at = updated_at;
//                }
//            }
//        }
//    }

}
