package com.ssy.pink.bean;

import java.io.Serializable;

public class UserProductInfo implements Serializable{
    private static final long serialVersionUID = 4190648463309032883L;
    private String customernum;
    private String productnum;
    private String productname;
    private String deadlinetime;
    private String createtime;
    private long price;
    private String productdesc;
    private String producttype;
    private String productstate;

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDeadlinetime() {
        return deadlinetime;
    }

    public void setDeadlinetime(String deadlinetime) {
        this.deadlinetime = deadlinetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProductstate() {
        return productstate;
    }

    public void setProductstate(String productstate) {
        this.productstate = productstate;
    }

    @Override
    public String toString() {
        return "UserProductInfo{" +
                "customernum='" + customernum + '\'' +
                ", productnum='" + productnum + '\'' +
                ", productname='" + productname + '\'' +
                ", deadlinetime='" + deadlinetime + '\'' +
                ", createtime='" + createtime + '\'' +
                ", price=" + price +
                ", productdesc='" + productdesc + '\'' +
                ", producttype='" + producttype + '\'' +
                ", productstate='" + productstate + '\'' +
                '}';
    }
}
