package com.ssy.pink.bean.weibo;

import java.io.Serializable;

public class RankInfo implements Serializable {
    private static final long serialVersionUID = -3773146889811284781L;
    long uid;
    int rank;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "RankInfo{" +
                "uid=" + uid +
                ", rank=" + rank +
                '}';
    }
}
