package com.ssy.pink.bean.request;

import java.io.Serializable;

public class SyncSpendRecordReq implements Serializable {
    private static final long serialVersionUID = -7026356385032853988L;
    private String customernum;//会员编号需要存在
    private String transactionid;//必须是未存在的
    private long spendbeannum;
    private long spenddesc;//爱豆数量

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

    public long getSpendbeannum() {
        return spendbeannum;
    }

    public void setSpendbeannum(long spendbeannum) {
        this.spendbeannum = spendbeannum;
    }

    public long getSpenddesc() {
        return spenddesc;
    }

    public void setSpenddesc(long spenddesc) {
        this.spenddesc = spenddesc;
    }

    @Override
    public String toString() {
        return "SyncSpendRecordReq{" +
                "customernum='" + customernum + '\'' +
                ", transactionid='" + transactionid + '\'' +
                ", spendbeannum=" + spendbeannum +
                ", spenddesc=" + spenddesc +
                '}';
    }
}
