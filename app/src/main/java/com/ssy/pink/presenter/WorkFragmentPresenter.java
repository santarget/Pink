package com.ssy.pink.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.iview.IWorkFragmentView;
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
        PinkNet.listGroup("C0903092659627006960", new Subscriber<CommonListResp<GroupInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<GroupInfo> groupInfoCommonListResp) {
                iView.loadGroups(groupInfoCommonListResp.getData());
            }
        });
    }


}
