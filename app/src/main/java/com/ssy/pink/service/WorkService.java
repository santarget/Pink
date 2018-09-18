package com.ssy.pink.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author ssy
 * @date 2018/9/18
 */
public class WorkService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
}
