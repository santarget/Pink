package com.ssy.pink.bean.request;

import java.io.Serializable;

public class ShareWeiboReq implements Serializable {
    private static final long serialVersionUID = 4661939091307130383L;
    String access_token;
    String status;

    public ShareWeiboReq() {
    }

    public ShareWeiboReq(String access_token, String status) {
        this.access_token = access_token;
        this.status = status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShareWeiboReq{" +
                "access_token='" + access_token + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
