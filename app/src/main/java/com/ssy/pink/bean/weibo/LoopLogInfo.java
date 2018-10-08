package com.ssy.pink.bean.weibo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ssy
 * @date 2018/9/29
 */
@Entity
public class LoopLogInfo implements Serializable {
    private static final long serialVersionUID = 670015756883531293L;

    @Id(autoincrement = true)
    @Unique
    Long logId;//轮博日志主键，自增长

    String time;
    String weiboUid;
    String log;

    @Generated(hash = 1399392705)
    public LoopLogInfo(Long logId, String time, String weiboUid, String log) {
        this.logId = logId;
        this.time = time;
        this.weiboUid = weiboUid;
        this.log = log;
    }

    @Generated(hash = 737786222)
    public LoopLogInfo() {
    }

    public Long getLogId() {
        return this.logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeiboUid() {
        return this.weiboUid;
    }

    public void setWeiboUid(String weiboUid) {
        this.weiboUid = weiboUid;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "LoopLogInfo{" +
                "logId=" + logId +
                ", time='" + time + '\'' +
                ", weiboUid='" + weiboUid + '\'' +
                ", log='" + log + '\'' +
                '}';
    }
}
