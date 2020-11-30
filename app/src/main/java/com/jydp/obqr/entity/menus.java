package com.jydp.obqr.entity;

import java.util.List;

/*
2020/10/16 15:58 星期五
作用 ：
*/
public class menus {

    private int menu_id;
    private String count;
    private List<String> attribute_ids;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<String> getAttribute_ids() {
        return attribute_ids;
    }

    public void setAttribute_ids(List<String> attribute_ids) {
        this.attribute_ids = attribute_ids;
    }
}
