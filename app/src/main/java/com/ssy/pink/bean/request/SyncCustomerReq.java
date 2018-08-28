package com.ssy.pink.bean.request;

import java.io.Serializable;

public class SyncCustomerReq implements Serializable {
    private static final long serialVersionUID = -7597532908612667297L;
    private String weiboid;

    private String weibonum;

    private String weiboname;

    private String fansorginfonum;//粉丝组织编号一定是要已经存在的组织编号

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

    public void setFansorginfonum(String fansorginfonum) {
        this.fansorginfonum = fansorginfonum;
    }

    public String getFansorginfonum() {
        return this.fansorginfonum;
    }

    @Override
    public String toString() {
        return "SyncCustomerReq{" +
                "weiboid='" + weiboid + '\'' +
                ", weibonum='" + weibonum + '\'' +
                ", weiboname='" + weiboname + '\'' +
                ", fansorginfonum='" + fansorginfonum + '\'' +
                '}';
    }
}
