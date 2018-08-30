package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * 某个用户的金额信息
 */
public class MoneyInfo implements Serializable {
    private static final long serialVersionUID = -5064674910377800164L;
    private long addRefillMountVal;//总充值的金额
    private long allGetBeanNum;//总的充值之后的爱豆数量
    private long allSpendBeanNum;//总的花费的爱豆数量
    private long restBeanNum;//剩余的爱豆数量
    private long allValidSmallNum;//绑定的有效状态小号数量
    private long allInValidSmallNum;//绑定的无效状态的小号数量
    private long allSmallNum;//总的小号数量

    public long getAddRefillMountVal() {
        return addRefillMountVal;
    }

    public void setAddRefillMountVal(long addRefillMountVal) {
        this.addRefillMountVal = addRefillMountVal;
    }

    public long getAllGetBeanNum() {
        return allGetBeanNum;
    }

    public void setAllGetBeanNum(long allGetBeanNum) {
        this.allGetBeanNum = allGetBeanNum;
    }

    public long getAllSpendBeanNum() {
        return allSpendBeanNum;
    }

    public void setAllSpendBeanNum(long allSpendBeanNum) {
        this.allSpendBeanNum = allSpendBeanNum;
    }

    public long getRestBeanNum() {
        return restBeanNum;
    }

    public void setRestBeanNum(long restBeanNum) {
        this.restBeanNum = restBeanNum;
    }

    public long getAllValidSmallNum() {
        return allValidSmallNum;
    }

    public void setAllValidSmallNum(long allValidSmallNum) {
        this.allValidSmallNum = allValidSmallNum;
    }

    public long getAllInValidSmallNum() {
        return allInValidSmallNum;
    }

    public void setAllInValidSmallNum(long allInValidSmallNum) {
        this.allInValidSmallNum = allInValidSmallNum;
    }

    public long getAllSmallNum() {
        return allSmallNum;
    }

    public void setAllSmallNum(long allSmallNum) {
        this.allSmallNum = allSmallNum;
    }

    @Override
    public String toString() {
        return "MoneyInfo{" +
                "addRefillMountVal=" + addRefillMountVal +
                ", allGetBeanNum=" + allGetBeanNum +
                ", allSpendBeanNum=" + allSpendBeanNum +
                ", restBeanNum=" + restBeanNum +
                ", allValidSmallNum=" + allValidSmallNum +
                ", allInValidSmallNum=" + allInValidSmallNum +
                ", allSmallNum=" + allSmallNum +
                '}';
    }
}
