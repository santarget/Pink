package com.ssy.pink.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ssy.pink.common.EventCode;
import com.ssy.pink.manager.LoopManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ssy
 * @date 2018/9/18
 */
public class WorkService extends Service {
    private static long accoutWait;
    private static long roundWait;

    //    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        LoopManager.getInstance().work();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        switch (eventCode) {
            case EventCode.WORK_NEXT:
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        LoopManager.getInstance().work();
                    }
                }, accoutWait);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        LoopManager.getInstance().work();
//                    }
//                }, accoutWait);
                break;
            case EventCode.WORK_WAITING:
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        LoopManager.getInstance().work();
                    }
                }, roundWait);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        LoopManager.getInstance().work();
//                    }
//                }, roundWait);
                break;
        }
    }
}
