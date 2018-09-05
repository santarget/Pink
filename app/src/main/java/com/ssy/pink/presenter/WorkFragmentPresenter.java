package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.SmallStatusInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.iview.IWorkFragmentView;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import java.util.List;

import rx.Subscriber;

public class WorkFragmentPresenter extends BasePresenter {
    private IWorkFragmentView iView;

    public WorkFragmentPresenter(IWorkFragmentView iView) {
        this.iView = iView;
    }

    public void listGroup() {
        PinkNet.listGroup(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<GroupInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<GroupInfo> groupInfoCommonListResp) {
                GroupManager.getInstance().groupInfos.clear();
                GroupManager.getInstance().groupInfos.addAll(groupInfoCommonListResp.getData());
                listSmall();
                iView.loadGroups();

            }
        });
    }

    public void listSmall() {
        PinkNet.listSmall(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<SmallInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonListResp<SmallInfo> smallInfoCommonListResp) {
                GroupManager.getInstance().smallInfos = smallInfoCommonListResp.getData();
                GroupManager.getInstance().classifySmall(new Subscriber<List<GroupInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GroupInfo> groupInfos) {
                        iView.loadGroups();
                    }
                });
            }
        });
    }
}
