package com.ssy.pink.bean;

import java.io.Serializable;

public class ProductInfo implements Serializable{
    private static final long serialVersionUID = -4887988961685937383L;
    private String productnum;

    private String productname;

    private int price;

    private String productdesc;

    private String producttype;

    private String productstate;

    private String createtime;

    private String createuser;

    public void setProductnum(String productnum){
        this.productnum = productnum;
    }
    public String getProductnum(){
        return this.productnum;
    }
    public void setProductname(String productname){
        this.productname = productname;
    }
    public String getProductname(){
        return this.productname;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setProductdesc(String productdesc){
        this.productdesc = productdesc;
    }
    public String getProductdesc(){
        return this.productdesc;
    }
    public void setProducttype(String producttype){
        this.producttype = producttype;
    }
    public String getProducttype(){
        return this.producttype;
    }
    public void setProductstate(String productstate){
        this.productstate = productstate;
    }
    public String getProductstate(){
        return this.productstate;
    }
    public void setCreatetime(String createtime){
        this.createtime = createtime;
    }
    public String getCreatetime(){
        return this.createtime;
    }
    public void setCreateuser(String createuser){
        this.createuser = createuser;
    }
    public String getCreateuser(){
        return this.createuser;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productnum='" + productnum + '\'' +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                ", productdesc='" + productdesc + '\'' +
                ", producttype='" + producttype + '\'' +
                ", productstate='" + productstate + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                '}';
    }
}
