package com.ssy.pink.bean.request;

import java.io.Serializable;

public class WeiboReq implements Serializable {
    private static final long serialVersionUID = -2914817028629568706L;
    String access_token;
    String uid;

    public WeiboReq() {
    }

    public WeiboReq(String access_token, String uid) {
        this.access_token = access_token;
        this.uid = uid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "WeiboReq{" +
                "access_token='" + access_token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
