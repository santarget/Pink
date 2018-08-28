package com.ssy.pink.presenter;

import android.text.TextUtils;

import com.ssy.pink.R;
import com.ssy.pink.base.BasePresenter;
import com.ssy.pink.iview.ISplashActivityView;
import com.ssy.pink.manager.LoginManager;
import com.ssy.pink.utils.CommonUtils;
import com.ssy.pink.utils.LogUtil;
import com.ssy.pink.utils.SharedPreferencesUtil;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class SplashActivityPresenter extends BasePresenter {
    public static final int LOGIN = 0;//手动登录
    public static final int FIRST_LOGIN = 1;//第一次登录
    public static final int AUTO_LOGIN = 2;//自动登录
    private ISplashActivityView iView;

    public SplashActivityPresenter(ISplashActivityView iView) {
        this.iView = iView;
    }

    public void permissionOk() {
        handleInit();
        iView.toLoginActivity();
//        choseActivity();
    }

    /**
     * 初始化操作，减轻Application压力
     */
    private void handleInit() {

    }

    public void choseActivity() {
        int loginType = SharedPreferencesUtil.getLoginType();
        switch (loginType) {
            case FIRST_LOGIN:
                iView.toLoginActivity();
                break;
            case AUTO_LOGIN:
                autoLogin();
                break;
            case LOGIN:
                iView.toLoginActivity();
                break;
            default:
                iView.toLoginActivity();
                break;
        }
    }
    public void autoLogin() {
        if (TextUtils.isEmpty(CommonUtils.getNetStatus())) {
            iView.showToast(R.string.network_not_available);
            iView.toLoginActivity();
        } else {
            LoginManager.getInstance().autoLogin();
        }
    }
}
