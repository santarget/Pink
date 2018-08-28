package com.ssy.pink.bean.request;

import java.io.Serializable;

public class SyncRechargeRecordReq implements Serializable {
    private static final long serialVersionUID = -2709759786894383813L;
    private String customernum;//会员编号需要存在
    private String transactionid;//必须是未存在的
    private long addmountval;//金额
    private long beannum;//爱豆数量

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

    @Override
    public String toString() {
        return "SyncRechargeRecordReq{" +
                "customernum='" + customernum + '\'' +
                ", transactionid='" + transactionid + '\'' +
                ", addmountval=" + addmountval +
                ", beannum=" + beannum +
                '}';
    }
}
