package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/9/3
 */
//@Entity
public class SmallInfo implements Serializable {
    private static final long serialVersionUID = 6711175802828272175L;
    private String customerNum;//所属的大号的会员id
    private String customerGroupNum;//所属分组的编号
    private String customerGroupName;
    private String smallWeiboNum;
    private String usepwd;
    private String smallWeiboName;

//    @Unique
//    @Id
    private String weibosmallNumId;
    private String smallNumFansOrgInfoName;//所属粉丝组织
    private String smallNumFansOrgInfoNum;
    private String smallNumStatus;//0无效  1有效

    private transient String mAccessToken = "";
    private transient String mRefreshToken = "";
    private transient long mExpiresTime = 0L;//access_token的生命周期，单位是秒数 expires_in

//    @Transient
    transient boolean checked;

    public SmallInfo(String smallWeiboNum, String smallWeiboName) {
        this.smallWeiboNum = smallWeiboNum;
        this.smallWeiboName = smallWeiboName;
    }

    public SmallInfo() {
    }

    public SmallInfo(String customerNum, String customerGroupNum, String customerGroupName, String smallWeiboNum,
                     String usepwd, String smallWeiboName, String weibosmallNumId, String smallNumFansOrgInfoName,
                     String smallNumFansOrgInfoNum, String smallNumStatus) {
        this.customerNum = customerNum;
        this.customerGroupNum = customerGroupNum;
        this.customerGroupName = customerGroupName;
        this.smallWeiboNum = smallWeiboNum;
        this.usepwd = usepwd;
        this.smallWeiboName = smallWeiboName;
        this.weibosmallNumId = weibosmallNumId;
        this.smallNumFansOrgInfoName = smallNumFansOrgInfoName;
        this.smallNumFansOrgInfoNum = smallNumFansOrgInfoNum;
        this.smallNumStatus = smallNumStatus;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerGroupNum() {
        return customerGroupNum;
    }

    public void setCustomerGroupNum(String customerGroupNum) {
        this.customerGroupNum = customerGroupNum;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getSmallWeiboNum() {
        return smallWeiboNum;
    }

    public void setSmallWeiboNum(String smallWeiboNum) {
        this.smallWeiboNum = smallWeiboNum;
    }

    public String getUsepwd() {
        return usepwd;
    }

    public void setUsepwd(String usepwd) {
        this.usepwd = usepwd;
    }

    public String getSmallWeiboName() {
        return smallWeiboName;
    }

    public void setSmallWeiboName(String smallWeiboName) {
        this.smallWeiboName = smallWeiboName;
    }

    public String getWeibosmallNumId() {
        return weibosmallNumId;
    }

    public void setWeibosmallNumId(String weibosmallNumId) {
        this.weibosmallNumId = weibosmallNumId;
    }

    public String getSmallNumFansOrgInfoName() {
        return smallNumFansOrgInfoName;
    }

    public void setSmallNumFansOrgInfoName(String smallNumFansOrgInfoName) {
        this.smallNumFansOrgInfoName = smallNumFansOrgInfoName;
    }

    public String getSmallNumFansOrgInfoNum() {
        return smallNumFansOrgInfoNum;
    }

    public void setSmallNumFansOrgInfoNum(String smallNumFansOrgInfoNum) {
        this.smallNumFansOrgInfoNum = smallNumFansOrgInfoNum;
    }

    public String getSmallNumStatus() {
        return smallNumStatus;
    }

    public void setSmallNumStatus(String smallNumStatus) {
        this.smallNumStatus = smallNumStatus;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "SmallInfo{" +
                "customerNum='" + customerNum + '\'' +
                ", customerGroupNum='" + customerGroupNum + '\'' +
                ", customerGroupName='" + customerGroupName + '\'' +
                ", smallWeiboNum='" + smallWeiboNum + '\'' +
                ", usepwd='" + usepwd + '\'' +
                ", smallWeiboName='" + smallWeiboName + '\'' +
                ", weibosmallNumId='" + weibosmallNumId + '\'' +
                ", smallNumFansOrgInfoName='" + smallNumFansOrgInfoName + '\'' +
                ", smallNumFansOrgInfoNum='" + smallNumFansOrgInfoNum + '\'' +
                ", smallNumStatus='" + smallNumStatus + '\'' +
                '}';
    }
}
