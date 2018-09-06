package com.ssy.pink.mvp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ssy.pink.R;
import com.ssy.pink.base.BaseActivity;
import com.ssy.pink.common.Constants;
import com.ssy.pink.mvp.iview.ISplashActivityView;
import com.ssy.pink.mvp.presenter.SplashActivityPresenter;
import com.ssy.pink.view.CommonDialog;

import java.util.ArrayList;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class SplashActivity extends BaseActivity implements ISplashActivityView {
    private int PERMISSION_REQUEST = 10;
    private SplashActivityPresenter presenter;
    private CommonDialog permissionDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        presenter = new SplashActivityPresenter(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkNeedPermission(Constants.PERMISSION_NEED);
                } else {
                    presenter.permissionOk();
                }
            }
        }, 2000l);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void checkNeedPermission(String[] permissionNeed) {
        ArrayList<String> permissions = new ArrayList<>();
        for (String permission : permissionNeed) {
            if (!mCheckSelfPermission(permission)) {
                permissions.add(permission);
            }
        }
        if (!permissions.isEmpty()) {
            String[] p = new String[permissions.size()];
            requestPermission(permissions.toArray(p));
        } else {
            presenter.permissionOk();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showPermissionDialog(final ArrayList<String> deniedPermission, int requestCode) {
        if (permissionDialog == null) {
            permissionDialog = new CommonDialog.Builder(this)
                    .setMessage(R.string.need_permission)
                    .setMessageGravity(Gravity.LEFT)
                    .setPositiveButton(R.string.accredit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermission(deniedPermission.toArray(new String[deniedPermission.size()]));
                            permissionDialog.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .create();
        }
        permissionDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void requestPermission(String[] needPermission) {
        mRequestPermission(needPermission, PERMISSION_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void permissionDenied(ArrayList<String> deniedPermission, int requestCode) {
        if (requestCode == PERMISSION_REQUEST)
            showPermissionDialog(deniedPermission, requestCode);
    }

    @Override
    protected void permissionGranted(int requestCode) {
        if (requestCode == PERMISSION_REQUEST)
            presenter.permissionOk();
    }

    @Override
    public void toLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    @Override
    public void toMainActivity() {
        Intent loginActivity = new Intent(this, MainActivity.class);
        startActivity(loginActivity);
        finish();
    }
}
