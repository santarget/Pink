package com.ssy.pink.bean.request;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class DeleteGroupReq implements Serializable {
    private static final long serialVersionUID = 5662701423797950850L;
    private String customernum;
    private String customergroupnum;

    public DeleteGroupReq(String customernum, String customergroupnum) {
        this.customernum = customernum;
        this.customergroupnum = customergroupnum;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getCustomergroupnum() {
        return customergroupnum;
    }

    public void setCustomergroupnum(String customergroupnum) {
        this.customergroupnum = customergroupnum;
    }

    @Override
    public String toString() {
        return "DeleteGroupReq{" +
                "customernum='" + customernum + '\'' +
                ", customergroupnum='" + customergroupnum + '\'' +
                '}';
    }
}
