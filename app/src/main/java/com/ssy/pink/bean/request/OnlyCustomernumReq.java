package com.ssy.pink.bean.request;

import java.io.Serializable;

public class OnlyCustomernumReq implements Serializable {
    private static final long serialVersionUID = -2380221048732975379L;
    private String customernum;

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    @Override
    public String toString() {
        return "OnlyCustomernumReq{" +
                "customernum='" + customernum + '\'' +
                '}';
    }
}
