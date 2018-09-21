package com.ssy.pink.manager;

import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.EmotionInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.WeiboInfo;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.service.WorkService;
import com.ssy.pink.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Subscriber;

/**
 * 抡博管理器
 */
public class LoopManager {
    public static final int MAX_COUNT = 5;
    private static LoopManager instance;
    public WeiboInfo weibo;//要抡博的微博
    public List<SmallInfo> smallList = new ArrayList<>();
    public StringBuilder log = new StringBuilder();
    private List<EmotionInfo> emotionInfoList = new ArrayList<>();
    //配置项
    public boolean customOn;//开启自定义
    public String customContent;//自定义内容
    public boolean randomOn;//开启随机表情
    public boolean keepOthers;//其他人转发内容保留
    public int speed;// 0慢速  1稳定 2快速
    public int count;//数量设置
    public String url;//要抡博的链接

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

    private String getEmotions() {
        if (ListUtils.isEmpty(emotionInfoList)) {
            WeiboNet.getEmotions(new Subscriber<List<EmotionInfo>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(List<EmotionInfo> emotionInfos) {

                }
            });
            return "[呵呵]";
        } else {
            //获取随机表情
            int random = new Random().nextInt(emotionInfoList.size());
            return emotionInfoList.get(random).getValue();
        }
    }
}
