package com.ssy.pink.bean.request;

import java.io.Serializable;

public class BindSmallReq implements Serializable {
    private static final long serialVersionUID = -4131974621337834008L;
    String customernum;
    String weibosmallnumid;
    String smallweibonum;
    String usepwd;
    String smallweiboname;
    String customergroupnum;

    public BindSmallReq() {
    }

    public BindSmallReq(String customernum, String weibosmallnumid, String smallweibonum, String usepwd, String smallweiboname, String customergroupnum) {
        this.customernum = customernum;
        this.weibosmallnumid = weibosmallnumid;
        this.smallweibonum = smallweibonum;
        this.usepwd = usepwd;
        this.smallweiboname = smallweiboname;
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

    public String getSmallweibonum() {
        return smallweibonum;
    }

    public void setSmallweibonum(String smallweibonum) {
        this.smallweibonum = smallweibonum;
    }

    public String getUsepwd() {
        return usepwd;
    }

    public void setUsepwd(String usepwd) {
        this.usepwd = usepwd;
    }

    public String getSmallweiboname() {
        return smallweiboname;
    }

    public void setSmallweiboname(String smallweiboname) {
        this.smallweiboname = smallweiboname;
    }

    public String getCustomergroupnum() {
        return customergroupnum;
    }

    public void setCustomergroupnum(String customergroupnum) {
        this.customergroupnum = customergroupnum;
    }

    @Override
    public String toString() {
        return "BindSmallReq{" +
                "customernum='" + customernum + '\'' +
                ", weibosmallnumid='" + weibosmallnumid + '\'' +
                ", smallweibonum='" + smallweibonum + '\'' +
                ", usepwd='" + usepwd + '\'' +
                ", smallweiboname='" + smallweiboname + '\'' +
                ", customergroupnum='" + customergroupnum + '\'' +
                '}';
    }
}
