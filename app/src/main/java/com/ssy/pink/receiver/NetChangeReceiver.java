package com.ssy.pink.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.ssy.pink.MyApplication;
import com.ssy.pink.R;
import com.ssy.pink.utils.CommonUtils;

public class NetChangeReceiver extends BroadcastReceiver {

    private long showToastTimeStamp;

    @Override
    public void onReceive(Context context, Intent intent) {

        String ret = CommonUtils.getNetStatus();
        if (TextUtils.isEmpty(ret)) {
            if (System.currentTimeMillis() - showToastTimeStamp >= 6 * 1000) {
                Toast.makeText(context, R.string.network_not_available, Toast.LENGTH_SHORT).show();
                showToastTimeStamp = System.currentTimeMillis();
            }
        } else {
            netWorkUsable();
        }
    }

    private void netWorkUsable() {
      /*  if (TextUtils.isEmpty(MyApplication.getToken())) {
            LoginManager.getInstance().autoLogin();
        }*/
    }


}
