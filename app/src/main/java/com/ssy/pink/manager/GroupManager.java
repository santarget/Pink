package com.ssy.pink.manager;

import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.utils.ListUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ssy
 * @date 2018/9/4
 */
public class GroupManager {
    private static GroupManager instance;
    public List<GroupInfo> groupInfos;
    public List<SmallInfo> smallInfos;

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
    public void classifySmall() {
        if (ListUtils.isEmpty(groupInfos) || ListUtils.isEmpty(smallInfos)) {
            return;
        }
        Subscription subscription = Observable.create(new Observable.OnSubscribe<GroupInfo>() {
            @Override
            public void call(Subscriber<? super GroupInfo> subscriber) {

            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void classfifySmall() {

    }
}
