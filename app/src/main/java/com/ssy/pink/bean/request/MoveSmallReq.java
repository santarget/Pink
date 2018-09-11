package com.ssy.pink.bean.request;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class MoveSmallReq implements Serializable {
    private static final long serialVersionUID = -3652885752433598130L;
    private String customernum;
    private String weibosmallnumid;//weibosmallnumid可以是多个小号的id,多个之间采用“;”进行拼接
    private String customergroupnum;//目标分组编号

    public MoveSmallReq() {
    }

    public MoveSmallReq(String customernum, String weibosmallnumid, String customergroupnum) {
        this.customernum = customernum;
        this.weibosmallnumid = weibosmallnumid;
        this.customergroupnum = customergroupnum;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getWeibosmallnumid() {
        return weibosmallnumid;
    }

    public void setWeibosmallnumid(String weibosmallnumid) {
        this.weibosmallnumid = weibosmallnumid;
    }

    public String getCustomergroupnum() {
        return customergroupnum;
    }

    public void setCustomergroupnum(String customergroupnum) {
        this.customergroupnum = customergroupnum;
    }

    @Override
    public String toString() {
        return "MoveSmallReq{" +
                "customernum='" + customernum + '\'' +
                ", weibosmallnumid='" + weibosmallnumid + '\'' +
                ", customergroupnum='" + customergroupnum + '\'' +
                '}';
    }
}
