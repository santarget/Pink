package com.ssy.pink.bean.request;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class AddGroupReq implements Serializable {
    private static final long serialVersionUID = 5662701423797950850L;
    private String customernum;
    private String customergroupname;

    public AddGroupReq(String customernum, String customergroupname) {
        this.customernum = customernum;
        this.customergroupname = customergroupname;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getCustomergroupname() {
        return customergroupname;
    }

    public void setCustomergroupname(String customergroupname) {
        this.customergroupname = customergroupname;
    }

    @Override
    public String toString() {
        return "AddGroupReq{" +
                "customernum='" + customernum + '\'' +
                ", customergroupname='" + customergroupname + '\'' +
                '}';
    }
}
