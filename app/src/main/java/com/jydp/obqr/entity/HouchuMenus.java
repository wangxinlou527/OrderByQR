package com.jydp.obqr.entity;

import java.util.List;

/*
2020/10/16 15:58 星期五
作用 ：
*/
public class HouchuMenus {

    private String name;
    private String count;
    private String attribute;
    private String number;
    private String time;
    private String people;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "HouchuMenus{" +
                "name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", attribute='" + attribute + '\'' +
                '}';
    }
}
