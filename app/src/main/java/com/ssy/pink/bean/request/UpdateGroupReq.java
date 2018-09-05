package com.ssy.pink.bean.request;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class UpdateGroupReq implements Serializable {
    private static final long serialVersionUID = -6763665612354280957L;
    private String customernum;
    private String customergroupnum;
    private String customergroupname;//修改后的小组名称

    public UpdateGroupReq(String customernum, String customergroupnum, String customergroupname) {
        this.customernum = customernum;
        this.customergroupnum = customergroupnum;
        this.customergroupname = customergroupname;
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

    public String getCustomergroupname() {
        return customergroupname;
    }

    public void setCustomergroupname(String customergroupname) {
        this.customergroupname = customergroupname;
    }


    @Override
    public String toString() {
        return "UpdateGroupReq{" +
                "customernum='" + customernum + '\'' +
                ", customergroupnum='" + customergroupnum + '\'' +
                ", customergroupname='" + customergroupname + '\'' +
                '}';
    }
}
