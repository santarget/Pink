package com.ssy.pink.mvp.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.bean.FansOrgInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.mvp.iview.ILoginActivityView;
import com.ssy.pink.manager.UserManager;
import com.ssy.pink.manager.WeiboManager;
import com.ssy.pink.mvp.presenter.LoginActivityPresenter;
import com.ssy.pink.utils.CommonUtils;
import com.ssy.pink.view.WaitingDialog;
import com.ssy.pink.view.dialog.FansOrgDialog;
import com.ssy.pink.view.dialog.LoginQuestionDialog;
import com.ssy.pink.view.dialog.LoginUseDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginActivityView {


    @BindView(R.id.etAccout)
    EditText etAccout;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvOrg)
    TextView tvOrg;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.tvTips)
    TextView tvTips;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    private WaitingDialog dialog;
    private LoginUseDialog useDialog;
    private LoginQuestionDialog questionDialog;
    private FansOrgDialog chooseDialog;
    private String accout;
    private String password;
    private LoginActivityPresenter presenter;
    private List<FansOrgInfo> orgsList;
    private boolean hasGotOrgs;

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        mSsoHandler = new SsoHandler(this);
        presenter = new LoginActivityPresenter(this);
        presenter.listFansOrg();
    }

    private void initView() {
        int size = (int) CommonUtils.dip2px(15);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_accout);
        drawable1.setBounds(0, 0, size, size);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        etAccout.setCompoundDrawables(drawable1, null, null, null);//只放左边

        Drawable drawable2 = getResources().getDrawable(R.drawable.ic_password);
        drawable2.setBounds(0, 0, size, size);
        etPassword.setCompoundDrawables(drawable2, null, null, null);

        Drawable drawable3 = getResources().getDrawable(R.drawable.ic_orgnization);
        drawable3.setBounds(0, 0, size, size);
        Drawable drawable4 = getResources().getDrawable(R.drawable.ic_arrow_right);
        drawable4.setBounds(0, 0, size, size);
        tvOrg.setCompoundDrawables(drawable3, null, drawable4, null);

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Store values at the time of the login attempt.
        accout = etAccout.getText().toString();
        password = etPassword.getText().toString();


        if (TextUtils.isEmpty(accout) || TextUtils.isEmpty(password)) {
            showToast(R.string.a_or_p_not_blank);
        } else if (UserManager.getInstance().fansOrgInfo == null) {
            showToast(R.string.please_choose_org);
        } else {
//            showProgress(true);
//            WeiboManager.getInstance().login(accout, password);
//            mSsoHandler.authorize(new SelfWbAuthListener());
            mSsoHandler.authorizeWeb(new SelfWbAuthListener());
//            presenter.syncCustomer("C0912110618837004971",etAccout.getText().toString(),"weibo name",UserManager.getInstance().fansOrgInfo.getFansorginfonum());


        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(final boolean show) {
        if (show) {
            if (dialog == null) {
                dialog = new WaitingDialog(this);
            }
            if (!dialog.isShowing()) {
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        toMainActivity();
                    }
                }, 1000l);
            }
        } else {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @OnClick({R.id.tvLogin, R.id.tvTips, R.id.tvQuestion, R.id.tvOrg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                attemptLogin();
                break;
            case R.id.tvTips:
                showLoginUseDialog();
                break;
            case R.id.tvQuestion:
                showLoginQuestionDialog();
                break;
            case R.id.tvOrg:
                showLoginChooseDialog();
                break;
        }
    }

    @Override
    public void toMainActivity() {
        Intent loginActivity = new Intent(this, MainActivity.class);
        startActivity(loginActivity);
        finish();
    }

    private void showLoginUseDialog() {
        if (useDialog == null) {
            useDialog = new LoginUseDialog(this);
        }
        useDialog.show();
    }

    private void showLoginQuestionDialog() {
        if (questionDialog == null) {
            questionDialog = new LoginQuestionDialog(this);
        }
        questionDialog.show();
    }

    private void showLoginChooseDialog() {
        if (chooseDialog == null) {
            chooseDialog = new FansOrgDialog(this);
        }
        if (!hasGotOrgs) {
            presenter.listFansOrg();
        }
        chooseDialog.show();
        if (orgsList != null) {
            chooseDialog.setDatas(orgsList);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventWithObj event) {
        if (EventCode.LOGIN_CHOOSE_ORG == event.eventCode) {
            UserManager.getInstance().fansOrgInfo = (FansOrgInfo) event.obj;
            if (UserManager.getInstance().fansOrgInfo != null) {
                tvOrg.setText(UserManager.getInstance().fansOrgInfo.getFansorginfoname());
            }
        }
    }

    @Override
    public void setOrgsList(List<FansOrgInfo> orgsList) {
        this.orgsList = orgsList;
        if (chooseDialog != null && chooseDialog.isShowing()) {
            chooseDialog.setDatas(orgsList);
        }
    }

    @Override
    public void hasGotOrgs(boolean hasGot) {
        hasGotOrgs = hasGot;
        if (!hasGot && chooseDialog != null && chooseDialog.isShowing()) {
            chooseDialog.dismiss();
        }
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    WeiboManager.getInstance().mAccessToken = token;
                    if (WeiboManager.getInstance().mAccessToken.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, token);
                        presenter.getWeiboUserInfo(etAccout.getText().toString(), UserManager.getInstance().fansOrgInfo.getFansorginfonum());
                    }
                }
            });
        }

        @Override
        public void cancel() {
            showToast("取消登录");
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            showToast("登录失败");
        }
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

    }
}

