package com.ssy.pink.mvp.presenter;

import com.ssy.pink.R;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.MoneyInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.mvp.iview.IGroupActivityView;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.ListUtils;
import com.ssy.pink.utils.MyUtils;

import java.util.List;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class GroupActivityPresenter extends BasePresenter {
    private IGroupActivityView iView;

    public GroupActivityPresenter(IGroupActivityView iView) {
        this.iView = iView;
    }

    public void deleteGroup(final GroupInfo groupInfo) {
        PinkNet.deleteGroup(UserManager.getInstance().userInfo.getCustomernum(), groupInfo.getCustomergroupnum(), new Subscriber<CommonResp<NoBodyEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonResp<NoBodyEntity> resp) {
                if (resp.getCode().equalsIgnoreCase(ResponseCode.CODE_SUCCESS)) {
                    iView.showToast(R.string.delete_success);
                    GroupManager.getInstance().groupInfos.remove(groupInfo);
                    iView.loadGroups();
                }
            }
        });
    }

    public void listGroup() {
        PinkNet.listGroup(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<GroupInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                iView.finishRefresh();
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<GroupInfo> groupInfoCommonListResp) {
                GroupManager.getInstance().groupInfos.clear();
                if (!ListUtils.isEmpty(groupInfoCommonListResp.getData())) {
                    GroupManager.getInstance().groupInfos.addAll(groupInfoCommonListResp.getData());
                }
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
                iView.finishRefresh();
                MyUtils.handleExcep(e);
            }

            @Override
            public void onNext(CommonListResp<SmallInfo> smallInfoCommonListResp) {
                GroupManager.getInstance().smallInfos.clear();
                if (ListUtils.isEmpty(smallInfoCommonListResp.getData())) {
                    iView.finishRefresh();
                    return;
                }
                GroupManager.getInstance().smallInfos.addAll(smallInfoCommonListResp.getData());
                GroupManager.getInstance().classifySmall(new Subscriber<List<GroupInfo>>() {
                    @Override
                    public void onCompleted() {
                        iView.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.finishRefresh();
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(List<GroupInfo> groupInfos) {
                        iView.loadGroups();
                    }
                });
            }
        });
    }

    public void updateMoneyInfo() {
        PinkNet.getUserMoney(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonResp<MoneyInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonResp<MoneyInfo> moneyInfoCommonResp) {
                if(moneyInfoCommonResp.getData()!=null){
                    UserManager.getInstance().moneyInfo = moneyInfoCommonResp.getData();
                    iView.updateDefaultGroup();
                }
            }
        });
    }
}
