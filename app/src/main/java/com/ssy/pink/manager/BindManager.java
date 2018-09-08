package com.ssy.pink.manager;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 绑号管理器
 *
 * @author ssy
 * @date 2018/9/7
 */
public class BindManager {
    private static BindManager instance;
    public List<SmallInfo> smallInfos = new ArrayList<>();//待绑定的小号集合
    public GroupInfo groupInfo = new GroupInfo();//要添加进的分组

    private BindManager() {
    }

    public static BindManager getInstance() {
        if (instance == null) {
            synchronized (BindManager.class) {
                if (instance == null) {
                    instance = new BindManager();
                }
            }
        }
        return instance;
    }
}
