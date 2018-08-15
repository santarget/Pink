package com.ssy.pink.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.common.EventWithObj;
import com.ssy.pink.manager.LoginManager;
import com.ssy.pink.utils.StatusBarUtil;
import com.ssy.pink.view.CircleImageView;
import com.ssy.pink.view.WaitingDialog;
import com.ssy.pink.view.dialog.LoginChooseDialog;
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
public class LoginActivity extends BaseActivity {


    @BindView(R.id.etAccout)
    EditText etAccout;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvOrg)
    TextView tvOrg;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvTips)
    TextView tvTips;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    private WaitingDialog dialog;
    private LoginUseDialog useDialog;
    private LoginQuestionDialog questionDialog;
    private LoginChooseDialog chooseDialog;
    private String accout;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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

        boolean cancel = false;

        if (TextUtils.isEmpty(accout) || TextUtils.isEmpty(password)) {
            showToast(R.string.a_or_p_not_blank);
            cancel = true;
        } else if (!isEmailValid(accout)) {
            showToast(R.string.error_invalid_email);
            cancel = true;
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            LoginManager.getInstance().login(accout, password);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
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
                }, 2000l);
            }
        } else {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @OnClick({R.id.btnLogin, R.id.tvTips, R.id.tvQuestion, R.id.tvOrg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
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
            chooseDialog = new LoginChooseDialog(this);
        }
        chooseDialog.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventWithObj event) {
        if (EventCode.LOGIN_CHOOSE_ORG == event.eventCode) {
            tvOrg.setText((String) event.obj);
        }
    }
}

