package com.ssy.pink.mvp.presenter;

import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.bean.ProductInfo;
import com.ssy.pink.bean.UserProductInfo;
import com.ssy.pink.bean.response.CommonListResp;
import com.ssy.pink.bean.response.CommonResp;
import com.ssy.pink.bean.response.NoBodyEntity;
import com.ssy.pink.common.ResponseCode;
import com.ssy.pink.manager.GroupManager;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.mvp.iview.IMonthVipActivityView;
import com.ssy.pink.network.api.PinkNet;
import com.ssy.pink.utils.MyUtils;

import rx.Subscriber;

/**
 * @author ssy
 * @date 2018/9/27
 */
public class MonthVipActivityPresenter extends BasePresenter {
    IMonthVipActivityView iView;

    public MonthVipActivityPresenter(IMonthVipActivityView iView) {
        this.iView = iView;
    }

    public void order(ProductInfo info, int quantity) {
        PinkNet.orderProduct(UserManager.getInstance().userInfo.getCustomernum(), MyUtils.getTransactionId(info),
                info.getProductnum(), quantity, new Subscriber<CommonResp<NoBodyEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        MyUtils.handleExcep(e);
                    }

                    @Override
                    public void onNext(CommonResp<NoBodyEntity> resp) {
                        iView.showToast(resp.getMsg());
                        if (ResponseCode.CODE_SUCCESS.equalsIgnoreCase(resp.getCode())) {
                            updateOrdered();
                        }
                    }
                });
    }

    public void updateOrdered() {
        PinkNet.listOrderedInfo(UserManager.getInstance().userInfo.getCustomernum(), new Subscriber<CommonListResp<UserProductInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommonListResp<UserProductInfo> userProductInfoCommonListResp) {
                if (ResponseCode.CODE_SUCCESS.equalsIgnoreCase(userProductInfoCommonListResp.getCode())) {
                    UserManager.getInstance().orderedInfos = userProductInfoCommonListResp.getData();
                    iView.updateOrdered();
                    UserManager.getInstance().getUserMoney();
                } else {
                    iView.showToast(userProductInfoCommonListResp.getMsg());
                }
            }
        });
    }
}
