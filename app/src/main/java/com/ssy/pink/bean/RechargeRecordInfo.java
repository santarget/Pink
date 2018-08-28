package com.ssy.pink.bean;

import java.io.Serializable;

public class RechargeRecordInfo implements Serializable {
    private static final long serialVersionUID = -7080293630556780449L;
    private String addmoneydesc;
    private String addmoneyinfopk;
    private long addmountval;
    private long beannum;
    private String createtime;
    private String createuser;
    private String customernum;
    private String transactionid;

    public String getAddmoneydesc() {
        return addmoneydesc;
    }

    public void setAddmoneydesc(String addmoneydesc) {
        this.addmoneydesc = addmoneydesc;
    }

    public String getAddmoneyinfopk() {
        return addmoneyinfopk;
    }

    public void setAddmoneyinfopk(String addmoneyinfopk) {
        this.addmoneyinfopk = addmoneyinfopk;
    }

    public long getAddmountval() {
        return addmountval;
    }

    public void setAddmountval(long addmountval) {
        this.addmountval = addmountval;
    }

    public long getBeannum() {
        return beannum;
    }

    public void setBeannum(long beannum) {
        this.beannum = beannum;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    @Override
    public String toString() {
        return "RechargeRecordInfo{" +
                "addmoneydesc='" + addmoneydesc + '\'' +
                ", addmoneyinfopk='" + addmoneyinfopk + '\'' +
                ", addmountval=" + addmountval +
                ", beannum=" + beannum +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                ", customernum='" + customernum + '\'' +
                ", transactionid='" + transactionid + '\'' +
                '}';
    }
}
