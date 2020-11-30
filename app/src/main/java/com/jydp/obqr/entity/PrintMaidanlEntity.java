package com.jydp.obqr.entity;

import java.util.List;

/*
2020/4/29 14:46 星期三
作用 ：
*/
public class PrintMaidanlEntity {

    /**
     * id : 7
     * merchant_id : 1
     * staff_id : 1
     * ip : 192.168.1.199
     * type : 1
     * created_at : 2019-11-06 15:55:51
     * updated_at : 2019-11-06 17:14:35
     */

    private String print_time;
    private SeatInfo seat_info;
    private List<MenuInfo> menu_info;
    private OrderInfo order_info;
    private Merchant merchant;

    public String getPrint_time() {
        return print_time;
    }

    public void setPrint_time(String print_time) {
        this.print_time = print_time;
    }

    public SeatInfo getSeat_info() {
        return seat_info;
    }

    public void setSeat_info(SeatInfo seat_info) {
        this.seat_info = seat_info;
    }

    public List<MenuInfo> getMenu_info() {
        return menu_info;
    }

    public void setMenu_info(List<MenuInfo> menu_info) {
        this.menu_info = menu_info;
    }

    public OrderInfo getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfo order_info) {
        this.order_info = order_info;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public static  class  SeatInfo{
        int count;
        String number;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    public static class MenuInfo{
        int id;
        int category_id;
        int merchant_id;
        int price;
        Object deleted_at;
        String created_at;
        String updated_at;
        String price_format;
        int count;
        int menu_id;
        String name;
        String language;
        String unit;
        String desc;
        int total;
        String total_format;
        String attributes;


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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getTotal_format() {
            return total_format;
        }

        public void setTotal_format(String total_format) {
            this.total_format = total_format;
        }

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }
    }

    public static class OrderInfo{
        int merchant_id;
        long sn;
        int total;
        String total_format;
        String opened_at;
        int price;
        String price_format;
        int discount;
        String discount_format;
        String staff_name;
        String tax_rate;
        String tax_fee;
        int payment_id;
        String payment_name;
        String tax_fee_format;
        int cash;
        String cash_format;
        String return_format;

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public long getSn() {
            return sn;
        }

        public void setSn(long sn) {
            this.sn = sn;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getTotal_format() {
            return total_format;
        }

        public void setTotal_format(String total_format) {
            this.total_format = total_format;
        }

        public String getOpened_at() {
            return opened_at;
        }

        public void setOpened_at(String opened_at) {
            this.opened_at = opened_at;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getPrice_format() {
            return price_format;
        }

        public void setPrice_format(String price_format) {
            this.price_format = price_format;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getDiscount_format() {
            return discount_format;
        }

        public void setDiscount_format(String discount_format) {
            this.discount_format = discount_format;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getTax_rate() {
            return tax_rate;
        }

        public void setTax_rate(String tax_rate) {
            this.tax_rate = tax_rate;
        }

        public String getTax_fee() {
            return tax_fee;
        }

        public void setTax_fee(String tax_fee) {
            this.tax_fee = tax_fee;
        }

        public int getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(int payment_id) {
            this.payment_id = payment_id;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getTax_fee_format() {
            return tax_fee_format;
        }

        public void setTax_fee_format(String tax_fee_format) {
            this.tax_fee_format = tax_fee_format;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        public String getCash_format() {
            return cash_format;
        }

        public void setCash_format(String cash_format) {
            this.cash_format = cash_format;
        }

        public String getReturn_format() {
            return return_format;
        }

        public void setReturn_format(String return_format) {
            this.return_format = return_format;
        }
    }
    public static class Merchant{
        int id;
        String name;
        String image;
        String phone;
        String address;
        int type;
        String tax_rate;
        Object deleted_at;
        String created_at;
        String updated_at;
        String image_url;


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

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }


    }

}
