package com.ssy.pink.bean.response;

import com.ssy.pink.bean.weibo.WeiboInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ssy
 * @date 2018/9/14
 */
public class WeiboListResp implements Serializable {
    private static final long serialVersionUID = -6155787315027281667L;
    List<WeiboInfo> statuses;
    long total_number;

    public List<WeiboInfo> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<WeiboInfo> statuses) {
        this.statuses = statuses;
    }

    public long getTotal_number() {
        return total_number;
    }

    public void setTotal_number(long total_number) {
        this.total_number = total_number;
    }

    @Override
    public String toString() {
        return "WeiboListResp{" +
                "statuses=" + statuses +
                ", total_number=" + total_number +
                '}';
    }
}
