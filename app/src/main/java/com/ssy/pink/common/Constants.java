package com.ssy.pink.common;

import android.Manifest;

import com.ssy.pink.MyApplication;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class Constants {

    public static final String BUGLY_APP_ID = "f1ae86160c";
    public static final String BUGLY_APP_KEY = "e79cefac-664b-4eaa-9c64-04d2841e6f05";
    public static final String BUGOUT_APP_KEY = "edfd8ce2fcb47df5150a9f0337c772a6";
    //permission
    public static final String[] PERMISSION_NEED = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * SDCard/Android/data/应用的包名/files/  用户无需关心的内容放在此路径下
     */
    public static final String BASE_PATH = MyApplication.getInstance().getExternalFilesDir(null) + "";
    public static final String LOG_PATH = BASE_PATH + "/log";

    public static final String INTENT_KEY_DATA = "data";
}
