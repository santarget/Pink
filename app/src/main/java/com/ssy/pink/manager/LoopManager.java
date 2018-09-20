package com.ssy.pink.manager;

import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.service.WorkService;

import java.util.ArrayList;
import java.util.List;

/**
 * 抡博管理器
 */
public class LoopManager {
    private static LoopManager instance;
    public WeiboInfo weibo;//要抡博的微博
    public List<SmallInfo> smallList = new ArrayList<>();

    //配置项
    public boolean customOn;//开启自定义
    public String customContent;//自定义内容
    public boolean randomOn;//开启随机表情
    public boolean keepOthers;//其他人转发内容保留
    public int speed;// 0慢速  1稳定 2快速
    public int count;//数量设置

    private LoopManager() {
    }

    public static LoopManager getInstance() {
        if (instance == null) {
            synchronized (LoopManager.class) {
                if (instance == null) {
                    instance = new LoopManager();
                }
            }
        }
        return instance;
    }

    public void startWork() {
        WorkService.startService(MyApplication.getInstance());
    }

    public void stopWork() {
        WorkService.stopService(MyApplication.getInstance());
    }
}
