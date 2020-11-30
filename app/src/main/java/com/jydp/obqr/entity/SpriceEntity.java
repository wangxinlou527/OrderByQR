package com.jydp.obqr.entity;

/*
2020/10/17 10:43 星期六
作用 ：
*/
public class SpriceEntity {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class  Data{
        private int price;
        private String price_format;
        private int total;
        private String total_format;
        private int tax_fee;
        private String tax_fee_format;
        private int discount;
        private String discount_format;

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

        public int getTax_fee() {
            return tax_fee;
        }

        public void setTax_fee(int tax_fee) {
            this.tax_fee = tax_fee;
        }

        public String getTax_fee_format() {
            return tax_fee_format;
        }

        public void setTax_fee_format(String tax_fee_format) {
            this.tax_fee_format = tax_fee_format;
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
    }

}
