package com.ssy.pink.mvp.presenter;

import com.ssy.pink.R;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.GroupInfo;
import com.ssy.pink.bean.SmallInfo;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IGroupDetailActivityView;
import com.ssy.pink.network.api.PinkNet;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/5
 */
public class GroupDetailActivityPresenter extends BasePresenter {
    private IGroupDetailActivityView iView;
    GroupInfo groupInfo;
    List<SmallInfo> selectList = new ArrayList<>();

    public GroupDetailActivityPresenter(IGroupDetailActivityView iView) {
        this.iView = iView;
    }

    public GroupDetailActivityPresenter setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
        return this;
    }

    public void deleteSmall(String smallWeiboId) {
        PinkNet.deleteSmall(UserManager.getInstance().userInfo.getCustomernum(), smallWeiboId, new Subscriber<CommonResp<NoBodyEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonResp<NoBodyEntity> noBodyEntityCommonResp) {
                if (noBodyEntityCommonResp.getCode().equals(ResponseCode.CODE_SUCCESS)) {
                    iView.showToast(R.string.delete_success);
                } else if (noBodyEntityCommonResp.getCode().equals(ResponseCode.CODE_999)) {

                }
            }
        });
    }

    /**
     * 全选或取消全选
     *
     * @param selectAll
     */
    public void selectAll(boolean selectAll) {
        for (SmallInfo smallInfo : groupInfo.getAllSmallInfos()) {
            smallInfo.setChecked(selectAll);
        }
        iView.getAdapter().notifyDataSetChanged();
    }

    /**
     * 所有异常账号
     */
    public void selectAbnormal() {
        selectAll(true);
        for (SmallInfo smallInfo : groupInfo.getValidSmallInfos()) {
            smallInfo.setChecked(false);
        }
        iView.getAdapter().notifyDataSetChanged();
    }

}
