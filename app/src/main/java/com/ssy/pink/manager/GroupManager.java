package com.ssy.pink.manager;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author ssy
 * @date 2018/9/4
 */
public class GroupManager {
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
                        List<SmallInfo> smalls = new ArrayList<>();
                        for (SmallInfo smallInfo : smallInfos) {
                            if (smallInfo.getCustomerGroupNum().equalsIgnoreCase(groupInfo.getCustomerGroupNum())) {
                                smalls.add(smallInfo);
                            }
                        }
                        groupInfo.setSmallInfos(smalls);
                        return groupInfos;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
