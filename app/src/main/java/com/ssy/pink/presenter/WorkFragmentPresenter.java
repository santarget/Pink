package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.iview.IWorkFragmentView;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import rx.Subscriber;

public class WorkFragmentPresenter extends BasePresenter {
    private IWorkFragmentView iView;

    public WorkFragmentPresenter(IWorkFragmentView iView) {
        this.iView = iView;
    }

    public void listGroup() {
//        PinkNet.listGroup(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<GroupInfo>>() {
        PinkNet.listGroup("C0902100924280005699", new Subscriber<CommonListResp<GroupInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<GroupInfo> groupInfoCommonListResp) {
                GroupManager.getInstance().groupInfos = groupInfoCommonListResp.getData();
                listSmall();
                iView.loadGroups(groupInfoCommonListResp.getData());

            }
        });
    }

    public void listSmall() {
        PinkNet.listSmall("C0902100924280005699", new Subscriber<CommonListResp<SmallInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonListResp<SmallInfo> smallInfoCommonListResp) {
                GroupManager.getInstance().smallInfos = smallInfoCommonListResp.getData();
            }
        });
    }


}
