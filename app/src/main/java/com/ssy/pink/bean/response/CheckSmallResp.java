package com.ssy.pink.bean.response;

import java.io.Serializable;

public class CheckSmallResp implements Serializable {
    private static final long serialVersionUID = -4413861921978242294L;
    String weiboNum;
    boolean isExist;

    public String getWeiboNum() {
        return weiboNum;
    }

    public void setWeiboNum(String weiboNum) {
        this.weiboNum = weiboNum;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public String toString() {
        return "CheckSmallResp{" +
                "weiboNum='" + weiboNum + '\'' +
                ", isExist=" + isExist +
                '}';
    }
}
