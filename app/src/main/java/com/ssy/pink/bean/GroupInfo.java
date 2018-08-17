package com.ssy.pink.bean;

import java.io.Serializable;

/**
 * @author ssy
 * @date 2018/8/17
 */
public class GroupInfo implements Serializable {
    private static final long serialVersionUID = 3999468443934077500L;
    public long id;
    public String name;
    public int totalCount;
    public int normalCount;

    public GroupInfo(String name, int totalCount, int normalCount) {
        this.name = name;
        this.totalCount = totalCount;
        this.normalCount = normalCount;
    }
}
