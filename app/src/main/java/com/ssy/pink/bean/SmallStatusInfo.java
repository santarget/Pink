package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class SmallStatusInfo implements Serializable {
    private static final long serialVersionUID = -8813194860603651152L;
    private int countnum;
    private String smallnumstatus;//“0”:无效 “1”：表示有效

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public String getSmallnumstatus() {
        return smallnumstatus;
    }

    public void setSmallnumstatus(String smallnumstatus) {
        this.smallnumstatus = smallnumstatus;
    }

    @Override
    public String toString() {
        return "SmallStatusInfo{" +
                "countnum=" + countnum +
                ", smallnumstatus='" + smallnumstatus + '\'' +
                '}';
    }
}
