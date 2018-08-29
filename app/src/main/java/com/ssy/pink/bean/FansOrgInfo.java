package com.ssy.pink.bean;

import java.io.Serializable;

public class FansOrgInfo implements Serializable {
    private static final long serialVersionUID = -6608509276449178841L;
    private String createtime;

    private String createuser;

    private String fansorginfoname;

    private String fansorginfonum;

    private String fansorginfostate;

    private String fansorginfotdesc;

    private transient boolean isSelected;

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public void setFansorginfoname(String fansorginfoname) {
        this.fansorginfoname = fansorginfoname;
    }

    public String getFansorginfoname() {
        return this.fansorginfoname;
    }

    public void setFansorginfonum(String fansorginfonum) {
        this.fansorginfonum = fansorginfonum;
    }

    public String getFansorginfonum() {
        return this.fansorginfonum;
    }

    public void setFansorginfostate(String fansorginfostate) {
        this.fansorginfostate = fansorginfostate;
    }

    public String getFansorginfostate() {
        return this.fansorginfostate;
    }

    public void setFansorginfotdesc(String fansorginfotdesc) {
        this.fansorginfotdesc = fansorginfotdesc;
    }

    public String getFansorginfotdesc() {
        return this.fansorginfotdesc;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "FansOrgInfo{" +
                "createtime=" + createtime +
                ", createuser='" + createuser + '\'' +
                ", fansorginfoname='" + fansorginfoname + '\'' +
                ", fansorginfonum='" + fansorginfonum + '\'' +
                ", fansorginfostate='" + fansorginfostate + '\'' +
                ", fansorginfotdesc='" + fansorginfotdesc + '\'' +
                '}';
    }
}
