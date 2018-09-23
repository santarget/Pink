package com.ssy.pink.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.ssy.pink.bean.WeiboTokenInfo;
import com.ssy.pink.common.EventCode;
import com.ssy.pink.manager.LoopManager;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.network.api.WeiboNet2;
import com.ssy.pink.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ssy
 * @date 2018/9/18
 */
public class WorkService extends Service {

    private static long accoutWait;
    private static long roundWait;

    private final Timer timer = new Timer();
    private TimerTask task;
    private int status;//0 工作   1 暂停

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        task = new TimerTask() {
            @Override
            public void run() {
                if (status == 1) {//等待
                } else {
                    LoopManager.getInstance().work();
                }

            }
        };

        timer.schedule(task, 0l, accoutWait);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        timer.cancel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public static void startService(Context context, long accoutWait, long roundWait) {
        Intent i = new Intent(context, WorkService.class);
        context.startService(i);
        WorkService.accoutWait = accoutWait;
        WorkService.roundWait = roundWait;
    }

    public static void stopService(Context context) {
        Intent i = new Intent(context, WorkService.class);
        context.stopService(i);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(Integer eventCode) {
        if (eventCode == EventCode.WORK_WAITING) {
            status = 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    status = 0;
                }
            }, roundWait);
        }
    }
}
