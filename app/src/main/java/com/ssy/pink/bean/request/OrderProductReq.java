package com.ssy.pink.bean.request;

import java.io.Serializable;

public class OrderProductReq implements Serializable {

    private static final long serialVersionUID = -2026643090089174554L;
    //    用户编号必须存在，产品编号必须存在合法
    private String customernum;
    private String transactionid;
    private String productnum;//产品编号

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getProductnum() {
        return productnum;
    }

    public void setProductnum(String productnum) {
        this.productnum = productnum;
    }

    @Override
    public String toString() {
        return "OrderProductReq{" +
                "customernum='" + customernum + '\'' +
                ", transactionid='" + transactionid + '\'' +
                ", productnum='" + productnum + '\'' +
                '}';
    }
}
