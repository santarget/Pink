package com.ssy.pink.manager;

import com.ssy.greendao.helper.HelperFactory;
import com.ssy.greendao.helper.SmallStatusDbHelper;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.SmallStatus;
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
 * 分组和小号管理器
 *
 * @author ssy
 * @date 2018/9/4
 */
public class GroupManager {
    private static GroupManager instance;
    public List<GroupInfo> groupInfos = new ArrayList<>();
    public List<SmallInfo> smallInfos = new ArrayList<>();//所有小号集合
    public List<SmallInfo> validSmallInfos = new ArrayList<>();//所有有效小号集合
    private SmallStatusDbHelper statusDbHelper;

    private GroupManager() {
        statusDbHelper = HelperFactory.getSmallStatusDbHelper();
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
    public boolean classifySmall(Subscriber<List<GroupInfo>> subscriber) {
        if (ListUtils.isEmpty(groupInfos)) {
            return false;
        }
        if (ListUtils.isEmpty(smallInfos)) {
            for (GroupInfo groupInfo : groupInfos) {
                groupInfo.getAllSmallInfos().clear();
                groupInfo.getValidSmallInfos().clear();
            }
            return false;
        }
        validSmallInfos.clear();
        Subscription subscription = Observable.from(groupInfos)
                .map(new Func1<GroupInfo, List<GroupInfo>>() {
                    @Override
                    public List<GroupInfo> call(GroupInfo groupInfo) {
                        List<SmallInfo> allSmalls = new ArrayList<>();
                        List<SmallInfo> validSmalls = new ArrayList<>();
                        for (SmallInfo smallInfo : smallInfos) {
                            if (groupInfo.getCustomergroupnum().equalsIgnoreCase(smallInfo.getCustomerGroupNum())) {
                                allSmalls.add(smallInfo);
                                /*if (smallInfo.getSmallNumStatus().equals("1")) {
                                    validSmalls.add(smallInfo);
                                }*/
                                SmallStatus smallStatus = statusDbHelper.uniqueQuery(smallInfo.getWeibosmallNumId());
                                if (smallStatus == null) {
                                    statusDbHelper.insertOrReplace(smallInfo.getWeibosmallNumId(), true);
                                    validSmalls.add(smallInfo);
                                } else if (smallStatus.getNormal()) {
                                    validSmalls.add(smallInfo);
                                }
                            }
                        }
                        groupInfo.setAllSmallInfos(allSmalls);
                        groupInfo.setValidSmallInfos(validSmalls);
                        validSmallInfos.addAll(validSmalls);
                        return groupInfos;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return true;
    }

    /**
     * 退出登录，重置
     */
    public void reset() {
        groupInfos.clear();
        smallInfos.clear();
        validSmallInfos.clear();
    }
}
