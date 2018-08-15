package com.ssy.pink.common;

import android.Manifest;

import com.ssy.pink.MyApplication;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class Constants {
    //permission
    public static final String[] PERMISSION_NEED = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * SDCard/Android/data/应用的包名/files/  用户无需关心的内容放在此路径下
     */
    public static final String BASE_PATH = MyApplication.getInstance().getExternalFilesDir(null) + "";
    public static final String LOG_PATH = BASE_PATH + "/log";
}
