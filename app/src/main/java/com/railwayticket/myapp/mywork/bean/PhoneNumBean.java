package com.railwayticket.myapp.mywork.bean;

/**
 * Created by tianyuanyuan on 2016/8/24.
 */
public class PhoneNumBean {

    private String name;
    private String phoneNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "PhoneNumBean{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
