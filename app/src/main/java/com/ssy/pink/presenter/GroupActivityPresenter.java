package com.ssy.pink.presenter;

import com.ssy.pink.R;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.Constants;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.iview.IGroupActivityView;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.network.api.PinkNet;

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
        PinkNet.deleteGroup(UserManager.getInstance().userInfo.getCustomernum(), groupInfo.getCustomerGroupNum(), new Subscriber<CommonResp<NoBodyEntity>>() {
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
}
