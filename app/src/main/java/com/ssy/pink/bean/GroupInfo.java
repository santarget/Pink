package com.ssy.pink.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class GroupInfo implements Serializable {
    private static final long serialVersionUID = 3999468443934077500L;
    private String customergroupname;//分组名
    private String customergroupnum;//分组id
    private String customernum;//会员id
    private String createtime;
    private String createuser;

    //以下由本地遍历
    public List<SmallInfo> allSmallInfos = new ArrayList<>();//属于这个分组的所有小号集合
    public List<SmallInfo> validSmallInfos = new ArrayList<>();//属于这个分组的有效小号集合

    private transient boolean isChecked;

    public GroupInfo() {
    }

    public String getCustomergroupname() {
        return customergroupname;
    }

    public void setCustomergroupname(String customergroupname) {
        this.customergroupname = customergroupname;
    }

    public String getCustomergroupnum() {
        return customergroupnum;
    }

    public void setCustomergroupnum(String customergroupnum) {
        this.customergroupnum = customergroupnum;
    }

    public String getCustomernum() {
        return customernum;
    }

    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public List<SmallInfo> getAllSmallInfos() {
        return allSmallInfos;
    }

    public void setAllSmallInfos(List<SmallInfo> allSmallInfos) {
        this.allSmallInfos = allSmallInfos;
    }

    public List<SmallInfo> getValidSmallInfos() {
        return validSmallInfos;
    }

    public void setValidSmallInfos(List<SmallInfo> validSmallInfos) {
        this.validSmallInfos = validSmallInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInfo groupInfo = (GroupInfo) o;
        return Objects.equals(customergroupnum, groupInfo.customergroupnum) &&
                Objects.equals(customernum, groupInfo.customernum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customergroupnum, customernum);
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "customergroupname='" + customergroupname + '\'' +
                ", customergroupnum='" + customergroupnum + '\'' +
                ", customernum='" + customernum + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createuser='" + createuser + '\'' +
                ", allSmallInfos=" + allSmallInfos +
                ", validSmallInfos=" + validSmallInfos +
                ", isChecked=" + isChecked +
                '}';
    }
}
