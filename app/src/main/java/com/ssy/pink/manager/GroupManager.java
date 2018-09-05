package com.ssy.pink.manager;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 分组和小号管理器
 *
 * @author ssy
 * @date 2018/9/4
 */
public class GroupManager {
    public static final String DEFAULT_GROUP = "default";
    private static GroupManager instance;
    public List<GroupInfo> groupInfos;
    public List<SmallInfo> smallInfos;//所有小号集合

    private GroupManager() {
    }

    public static GroupManager getInstance() {
        if (instance == null) {
            synchronized (GroupManager.class) {
                if (instance == null) {
                    instance = new GroupManager();
                }
            }
        }
        return instance;
    }

    /**
     * 给小号归类
     */
    public void classifySmall(Subscriber<List<GroupInfo>> subscriber) {
        if (ListUtils.isEmpty(groupInfos) || ListUtils.isEmpty(smallInfos)) {
            return;
        }

        Subscription subscription = Observable.from(groupInfos)
                .map(new Func1<GroupInfo, List<GroupInfo>>() {
                    @Override
                    public List<GroupInfo> call(GroupInfo groupInfo) {
                        List<SmallInfo> allSmalls = new ArrayList<>();
                        List<SmallInfo> validSmalls = new ArrayList<>();
                        for (SmallInfo smallInfo : smallInfos) {
                            if (smallInfo.getCustomerGroupNum().equalsIgnoreCase(groupInfo.getCustomerGroupNum())) {
                                allSmalls.add(smallInfo);
                                if (smallInfo.getSmallNumStatus().equals("1")) {
                                    validSmalls.add(smallInfo);
                                }
                            }
                        }
                        groupInfo.setAllSmallInfos(allSmalls);
                        groupInfo.setValidSmallInfos(validSmalls);
                        return groupInfos;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 退出登录，重置
     */
    public void reset() {
        groupInfos = null;
        smallInfos = null;
    }
}
