package com.ssy.pink.bean.request;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class DeleteSmallReq implements Serializable {
    private static final long serialVersionUID = 9170180384032269376L;
    private String customernum;
    private String weibosmallnumid;//weibosmallnumid可以是多个小号的id,多个之间采用“;”进行拼接

    public DeleteSmallReq() {
    }

    public DeleteSmallReq(String customernum, String weibosmallnumid) {
        this.customernum = customernum;
        this.weibosmallnumid = weibosmallnumid;
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

    @Override
    public String toString() {
        return "DeleteSmallReq{" +
                "customernum='" + customernum + '\'' +
                ", weibosmallnumid='" + weibosmallnumid + '\'' +
                '}';
    }
}
