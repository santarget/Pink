package com.ssy.pink.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ssy.pink.common.EventCode;
import com.ssy.pink.network.api.WeiboNet;
import com.ssy.pink.network.api.WeiboNet2;
import com.ssy.pink.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

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

    private final Timer timer = new Timer();
    private TimerTask task;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

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
                repostWeibo();
            }
        };

//        timer.schedule(task, 0, Constants.ALBUM_BACK_PERIOD);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public static void startService(Context context) {
        Intent i = new Intent(context, WorkService.class);
        context.startService(i);
    }

    public static void stopService(Context context) {
        Intent i = new Intent(context, WorkService.class);
        context.stopService(i);
    }

    Callback callback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {

        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    };

    private void repostWeibo() {
//        WeiboNet.shareWeibo(status, callback);
    }

}
