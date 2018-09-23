package com.ssy.pink.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ssy.pink.MyApplication;

public class SharedPreferencesUtil {

    private static final String SHARED_PREFERENCES_NAME = "settings";
    private static final String LOGIN_TYPE = "loginType";
    private static final String ACCOUNT_INFO = "accountInfo";
    private static final String WIFI_PROMPT = "wifi_prompt";
    private static final String CURRENT_LANGUAGE = "current_language";
    private static final String SYSTEM_LANGUAGE = "system_language";
    private static final String NEED_CHANGE_PASSWORD = "need_change_password";
    private static final String LAST_VERSION = "last_version";
    private static final String FIRST_USE_TIME = "first_use_time";//第一次使用app时间


    private static SharedPreferences getSharedPreferences() {
        return MyApplication.getInstance().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_APPEND);
    }

    /**
     * 设置登录类型(自动，手动登录)
     */
    public static void setLoginType(int loginType) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(LOGIN_TYPE, loginType);
        editor.apply();
    }


    /**
     * @return 获取登录的类型。
     */
    public static int getLoginType() {
        return getSharedPreferences().getInt(LOGIN_TYPE, 0);
    }

    /**
     * 保存账户信息
     *
     * @param account
     */
    public static void saveAccountInfo(String account) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(ACCOUNT_INFO, account);
        editor.apply();
    }

    /**
     * 获取账户信息
     */
    public static String getAccountInfo() {
        return getSharedPreferences().getString(ACCOUNT_INFO, "");
    }

    /**
     * 设置非Wifi下载时是否提示
     *
     * @param checkState
     */
    public static void setWifiPrompt(boolean checkState) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(WIFI_PROMPT, checkState);
        editor.apply();
    }

    /**
     * @return 非Wifi下载是否提示
     * true 提示
     */
    public static boolean getWifiPrompt() {
        return getSharedPreferences().getBoolean(WIFI_PROMPT, true);
    }

    /**
     * 保存当前语言
     *
     * @param appLanguage system 跟随系统
     *                    zh 简体中文
     *                    en 英文
     */
    public static void saveCurrentLanguage(String appLanguage) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(CURRENT_LANGUAGE, appLanguage);
        editor.apply();
    }

    /**
     * @return system 跟随系统
     * zh 简体中文
     * en 英文
     */
    public static String getCurrentLanguage() {
        return getSharedPreferences().getString(CURRENT_LANGUAGE, "system");
    }


    /**
     * 保存系统语言
     *
     * @param systemLanguage zh 简体中文
     *                       en 英文
     */
    public static void saveSystemLanguage(String systemLanguage) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(SYSTEM_LANGUAGE, systemLanguage);
        editor.apply();
    }

    /**
     * 获取系统语言
     * <p>
     * zh 简体中文
     * en 英文
     */
    public static String getSystemLanguage() {
        return getSharedPreferences().getString(SYSTEM_LANGUAGE, "");
    }

    /**
     * 是否需要更改密码
     *
     * @param needChangePassword
     */
    public static void saveNeedChangePassword(boolean needChangePassword) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(NEED_CHANGE_PASSWORD, needChangePassword);
        editor.apply();
    }


    /**
     * 是否需要更改密码
     */
    public static boolean getNeedChangePassword() {
        return getSharedPreferences().getBoolean(NEED_CHANGE_PASSWORD, false);
    }


    /**
     * 设置之前版本
     *
     * @param versionCode
     */
    public static void setLastVersion(int versionCode) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(LAST_VERSION, versionCode);
        editor.apply();
    }

    /**
     * 获取当前版本
     */
    public static int getLastVersion() {
        return getSharedPreferences().getInt(LAST_VERSION, 0);
    }

    /**
     * 设置第一次使用时间
     *
     * @param timeStamp
     */
    public static void setFirstTime(long timeStamp) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(FIRST_USE_TIME, timeStamp);
        editor.apply();
    }

    /**
     * 获取第一次使用时间
     */
    public static long getFirstUseTime() {
        return getSharedPreferences().getLong(FIRST_USE_TIME, 0l);
    }


}
