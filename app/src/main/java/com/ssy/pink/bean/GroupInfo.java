package com.ssy.pink.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class GroupInfo implements Serializable {
    private static final long serialVersionUID = 3999468443934077500L;
    private String customerGroupName;//分组名
    private String customerGroupNum;//分组id
    private String customerNum;//会员id
    private String createTime;
    private String createUser;

    //以下由本地遍历
    public int totalCount;
    public int normalCount;
    public List<SmallInfo> smallInfos;//属于这个分组的小号集合

    private transient boolean isChecked;

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupNum() {
        return customerGroupNum;
    }

    public void setCustomerGroupNum(String customerGroupNum) {
        this.customerGroupNum = customerGroupNum;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(int normalCount) {
        this.normalCount = normalCount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<SmallInfo> getSmallInfos() {
        return smallInfos;
    }

    public void setSmallInfos(List<SmallInfo> smallInfos) {
        this.smallInfos = smallInfos;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "customerGroupName='" + customerGroupName + '\'' +
                ", customerGroupNum='" + customerGroupNum + '\'' +
                ", customerNum='" + customerNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", totalCount=" + totalCount +
                ", normalCount=" + normalCount +
                ", smallInfos=" + smallInfos +
                ", isChecked=" + isChecked +
                '}';
    }
}
