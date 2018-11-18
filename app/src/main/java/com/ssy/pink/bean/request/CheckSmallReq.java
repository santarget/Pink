package com.ssy.pink.bean.request;

import java.io.Serializable;

public class CheckSmallReq implements Serializable{
    private static final long serialVersionUID = 892551450581576476L;
    String customernum;
    String weiboNumStr;//"weiboNum1; weiboNum2; weiboNum2"

    public CheckSmallReq() {
    }

    public CheckSmallReq(String customernum, String weiboNumStr) {
        this.customernum = customernum;
        this.weiboNumStr = weiboNumStr;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getWeiboNumStr() {
        return weiboNumStr;
    }

    public void setWeiboNumStr(String weiboNumStr) {
        this.weiboNumStr = weiboNumStr;
    }

    @Override
    public String toString() {
        return "CheckSmallReq{" +
                "customernum='" + customernum + '\'' +
                ", weiboNumStr='" + weiboNumStr + '\'' +
                '}';
    }
}
