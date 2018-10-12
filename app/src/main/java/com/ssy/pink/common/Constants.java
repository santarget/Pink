package com.ssy.pink.common;

import android.Manifest;

import com.ssy.pink.MyApplication;

/**
 * @author ssy
 * @date 2018/8/9
 */
public class Constants {

    public static final String BUGOUT_APP_KEY = "edfd8ce2fcb47df5150a9f0337c772a6";
    public static final String WE_PAY_APP_ID = "wxb4ba3c02aa476ea1";//wxb4ba3c02aa476ea1  wxd930ea5d5a258f4f
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
