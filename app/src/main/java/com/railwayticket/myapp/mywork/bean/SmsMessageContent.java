package com.railwayticket.myapp.mywork.bean;

import java.io.Serializable;

/**
 * Created by tianyuanyuan on 2016/8/23.
 */
public class SmsMessageContent implements Serializable{

    private String name;
    private String phoneNum;
    private String body;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "phoneNum=" + phoneNum +"\n\n"+
                "body=" + body+"\n" ;
    }
}
