package com.ssy.pink.bean;

import java.io.Serializable;

public class WeiboCustomerInfo implements Serializable {
    private static final long serialVersionUID = -5217524867985702580L;
    private String customernum;

    private String fansorginfonum;

    private String fansorginfoname;

    private String weiboid;

    private String weibonum;

    private String weiboname;

    private String customerstatus;

    private String specialflag;

    private String memo;

    private String createtime;

    private String createuser;

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getCustomernum() {
        return this.customernum;
    }

    public void setFansorginfonum(String fansorginfonum) {
        this.fansorginfonum = fansorginfonum;
    }

    public String getFansorginfonum() {
        return this.fansorginfonum;
    }

    public void setFansorginfoname(String fansorginfoname) {
        this.fansorginfoname = fansorginfoname;
    }

    public String getFansorginfoname() {
        return this.fansorginfoname;
    }

    public void setWeiboid(String weiboid) {
        this.weiboid = weiboid;
    }

    public String getWeiboid() {
        return this.weiboid;
    }

    public void setWeibonum(String weibonum) {
        this.weibonum = weibonum;
    }

    public String getWeibonum() {
        return this.weibonum;
    }

    public void setWeiboname(String weiboname) {
        this.weiboname = weiboname;
    }

    public String getWeiboname() {
        return this.weiboname;
    }

    public void setCustomerstatus(String customerstatus) {
        this.customerstatus = customerstatus;
    }

    public String getCustomerstatus() {
        return this.customerstatus;
    }

    public void setSpecialflag(String specialflag) {
        this.specialflag = specialflag;
    }

    public String getSpecialflag() {
        return this.specialflag;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    @Override
    public String toString() {
        return "WeiboCustomerInfo{" +
                "customernum='" + customernum + '\'' +
                ", fansorginfonum='" + fansorginfonum + '\'' +
                ", fansorginfoname='" + fansorginfoname + '\'' +
                ", weiboid='" + weiboid + '\'' +
                ", weibonum='" + weibonum + '\'' +
                ", weiboname='" + weiboname + '\'' +
                ", customerstatus='" + customerstatus + '\'' +
                ", specialflag='" + specialflag + '\'' +
                ", memo='" + memo + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                '}';
    }
}
