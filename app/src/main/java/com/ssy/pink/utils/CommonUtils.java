package com.ssy.pink.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.ssy.pink.MyApplication;
import com.ssy.pink.bean.VersionInfo;
import com.ssy.pink.common.ConfigProp;
import com.ssy.pink.common.ConstantType;

import org.greenrobot.greendao.query.WhereCondition;

import java.io.File;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Tys on 2017/1/27.
 */

public class CommonUtils {


    private static final String TAG = "CommonUtils";

    public static float dip2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, MyApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static float px2dip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, MyApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static float getScreenWidth() {
        DisplayMetrics dm = MyApplication.getInstance().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static float getScreenHeight() {
        DisplayMetrics dm = MyApplication.getInstance().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }


    private static PackageInfo getPackageInfo() {
        PackageInfo packageInfo = null;
        Context context = MyApplication.getInstance();
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * @param fileName
     * @return 根据文件名返回文件类型
     */
    public static String getFileMimeType(String fileName) {
        String fileSuffix = getSuffix(fileName);//得到文件类型名字
        if (TextUtils.isEmpty(fileSuffix)) {
            return ConstantType.OTHER;
        } else {
            for (int i = 0; i < ConstantType.FileMimeType.length; i++) {
                for (int j = 1; j < ConstantType.FileMimeType[i].length; j++) {
                    if (fileSuffix.equalsIgnoreCase(ConstantType.FileMimeType[i][j])) {//匹配文件类型属于哪一类
                        return ConstantType.FileMimeType[i][0];
                    }
                }
            }
            return ConstantType.OTHER;
        }
    }


    /**
     * @return "WIFI" or "MOBILE" 网络不可用时返回""
     */
    public static String getNetStatus() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return netInfo.getTypeName();
        } else {
            return "";
        }
    }

    /**
     * 获取文件后缀 .MP4
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index >= 0) {
            return fileName.substring(index, fileName.length());
        } else {
            return "";
        }
    }

    /**
     * 获取没有扩展名的文件名
     *
     * @param fileName
     * @return
     */
    public static String getFileNameWithoutSuffix(String fileName) {
        String name = getFileName(fileName);
        int index = name.lastIndexOf(".");
        if (index >= 0) {
            return name.substring(0, index);
        } else {
            return name;
        }
    }

    /**
     * 获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        int index = filePath.lastIndexOf("/");
        if (index >= 0) {
            return filePath.substring(index + 1, filePath.length());
        } else {
            return filePath;
        }
    }


    /**
     * @param ownerId
     * @param fileId
     * @param objectId 非版本文件objectId为空
     * @param fileName
     * @return 构建出下载后文件的名称(为区分不同版本 ， 加上objectId)
     */
    public static String getDownloadFileName(long ownerId, long fileId, String objectId, String fileName) {
        byte[] bytes = (ownerId + fileId + objectId).getBytes();
        return getFileNameWithoutSuffix(fileName) + "_" + EnDecrypUtil.byteEncrypt(bytes, bytes.length, "MD5") + CommonUtils.getSuffix(fileName);
    }

    /**
     * 格式化时间。
     *
     * @param inFormat 格式化输出的样式，为null则使用默认样式  默认：yyyy/MM/dd HH:mm
     * @param longTime 时间毫秒值 <=0 返回 “”
     * @return 格式化后的字符串
     */
    public static CharSequence formatData(@Nullable String inFormat, long longTime) {
        if (longTime <= 0) {
            return "";
        } else {
            return DateFormat.format(TextUtils.isEmpty(inFormat) ? "yyyy-MM-dd HH:mm" : inFormat, longTime);
        }
    }

    /**
     * 格式化时间。
     *
     * @param inFormat 格式化输出的样式，为null则使用默认样式  默认：yyyy.MM.dd HH:mm
     * @param longTime 时间毫秒值 <=0 返回 “”
     * @return 格式化后的字符串
     */
    public static CharSequence formatData2(@Nullable String inFormat, long longTime) {
        if (longTime <= 0) {
            return "";
        } else {
            return DateFormat.format(TextUtils.isEmpty(inFormat) ? "yyyy.MM.dd  HH:mm" : inFormat, longTime);
        }
    }

    /**
     * 获取距离链接失效的天数
     *
     * @param expireAt 链接失效的日期
     * @return 失效的天数
     */
    public static int getExpireDays(long expireAt) {
        long currentTime = System.currentTimeMillis();
        long day = (expireAt - currentTime) / (24 * 3600 * 1000);
        return Integer.parseInt(String.valueOf(day));
    }

    /**
     * 获取距离链接失效的小时数(1天以内的小时数)
     *
     * @param expireAt 链接失效的日期
     * @return
     */
    public static int getExpireHours(long expireAt) {
        long currentTime = System.currentTimeMillis();
        long time = (expireAt - currentTime) / (3600 * 1000);
        int day = getExpireDays(expireAt);
        long hour = time - day * 24;
        return Integer.parseInt(String.valueOf(hour));
    }

    /**
     * 获取距离链接失效的分钟数(1小时以内的分钟数)
     *
     * @param expireAt 链接失效的日期
     * @return
     */
    public static int getExpireMinute(long expireAt) {
        long currentTime = System.currentTimeMillis();
        long time = (expireAt - currentTime) / (60 * 1000);
        int day = getExpireDays(expireAt);
        int hour = getExpireHours(expireAt);
        long minute = time - day * 24 * 60 - hour * 60;
        return Integer.parseInt(String.valueOf(minute));
    }

    /**
     * String  转换 Date
     *
     * @param format 格式 默认 yyyy/MM/dd HH:mm
     * @param s      带转换的字符串
     * @return
     */
    public static Date stringToData(@Nullable String format, String s) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(TextUtils.isEmpty(format) ? "yyyy/MM/dd HH:mm" : format, Locale.US);
            return sf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 构建链接地址
     *
     * @param id
     * @return
     */
    public static String buildLinkUrl(String id) {
        StringBuilder sb = new StringBuilder(ConfigProp.serverUrl)
                .append("/p/")
                .append(id);
        return sb.toString();
    }

    private static final String NUMBERS = "0123456789";
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String OTHERS = "!@#$^&*-+.";

    /**
     * 获取随机字符串
     *
     * @param stringNum 字符个数 从0开始计数
     * @return 随机字符串 >=3才有意义
     */
    public static String getRandomString(int stringNum) {
        SecureRandom sr;
        int[] plan = new int[stringNum];
        char[] chars = new char[stringNum];
        for (int i = 0; i < stringNum; i++) {
            plan[i] = i;
        }

        for (int i = 0; i < stringNum / 2; i++) {
            sr = new SecureRandom();
            int index = sr.nextInt(stringNum - 1);
            Integer tempVal = plan[index];
            plan[index] = plan[stringNum - index - 1];
            plan[stringNum - index - 1] = tempVal;
        }

        for (int i = 0; i < stringNum; i++) {
            switch (i) {
                case 0:
                    chars[plan[0]] = getNumberChar();
                    break;
                case 1:
                    chars[plan[1]] = getCharChar();
                    break;
                case 2:
                    chars[plan[2]] = getOtherChar();
                    break;
                default:
                    chars[plan[i]] = getRandomChar(new Random().nextInt(3));
                    break;
            }
        }
        return new String(chars);
    }

    /**
     * 格式化文件大小
     *
     * @param length
     * @return
     */
    public static CharSequence formatFileLength(Long length) {
        long size = length;
        if (size < 1024) {
            return size + "b";
        } else if (size < 1048576l) {
            return getRoundFloat(((float) size) / (float) 1024, 1) + "K";
        } else if (size < 1073741824l) {
            return getRoundFloat(((float) size) / (float) 1048576, 1) + "M";
        } else if (size < 1099511627776l) {
            return getRoundFloat(((float) size) / (float) 1073741824, 1) + "G";
        } else {
            return getRoundFloat(((float) size) / (float) 1099511627776l, 1) + "T";
        }
    }

    /**
     * 格式化文件大小
     *
     * @param length
     * @param decimals 小数点后位数
     * @return
     */
    public static CharSequence formatFileLength(long length, int decimals) {
        long size = length;
        if (size < 1024) {
            return size + "b";
        } else if (size < 1048576l) {
            return getRoundFloat(((float) size) / (float) 1024, decimals) + "K";
        } else if (size < 1073741824l) {
            return getRoundFloat(((float) size) / (float) 1048576, decimals) + "M";
        } else if (size < 1099511627776l) {
            return getRoundFloat(((float) size) / (float) 1073741824, decimals) + "G";
        } else {
            return getRoundFloat(((float) size) / (float) 1099511627776l, decimals) + "T";
        }
    }

    public static float getRoundFloat(float flt, int i) {
        if (i > 0) {
            float f = (float) Math.pow(10, i);
            return Math.round(flt * f) / f;
        } else {
            return flt;
        }
    }

    private static char getRandomChar(int i) {
        char c;
        switch (i) {
            case 0:
                c = getOtherChar();
                break;
            case 1:
                c = getCharChar();
                break;
            case 2:
                c = getNumberChar();
                break;
            default:
                c = getCharChar();
        }
        return c;
    }

    private static char getOtherChar() {
        Random random = new Random();
        int index = random.nextInt(OTHERS.length());
        return OTHERS.charAt(index);
    }

    private static char getCharChar() {
        Random random = new Random();
        int index = random.nextInt(CHARS.length());
        return CHARS.charAt(index);
    }

    private static char getNumberChar() {
        Random random = new Random();
        int index = random.nextInt(NUMBERS.length());
        return NUMBERS.charAt(index);
    }

    /**
     * 设置APP语言
     *
     * @param context
     * @param appLanguage en 英文
     *                    zh 简体中文
     *                    system 跟随系统
     */
    public static void setAppLanguage(Context context, String appLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        switch (appLanguage) {
            case "en":
                configuration.locale = Locale.ENGLISH;
                break;
            case "zh":
                configuration.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            default:
                String systemLanguage = SharedPreferencesUtil.getSystemLanguage();
                if (systemLanguage.equals("en")) {               //非英文时设置为中文
                    configuration.locale = Locale.ENGLISH;
                } else {
                    configuration.locale = Locale.SIMPLIFIED_CHINESE;
                }
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
        SharedPreferencesUtil.saveCurrentLanguage(appLanguage);
    }

    /**
     * 构建更新时下载的apk名称
     *
     * @param version
     * @return
     */
    public static String getUpgradeApkName(VersionInfo version) {
        return "CsiEcs_" + version.getVersionName() + "_" + version.getVersionNum() + ".apk";
    }

    /**
     * 构建缩略图的名字
     *
     * @param ownedId
     * @param fileId
     * @return
     */
    public static String getThumbnailName(Long ownedId, Long fileId) {
        return "thumb" + ownedId + "_" + fileId + ".jpg";
    }

    /**
     * 构建缩略图的名字（有重复的风险）
     *
     * @param ownedId
     * @param fileId
     * @param fileSize 文件大小
     * @return
     */
    public static String getThumbnailNameByFileSize(Long ownedId, Long fileId, long fileSize) {
        return "thumb" + ownedId + "_" + fileId + "_" + fileSize + ".jpg";
    }

    /**
     * 根据用户信息创建数据库(任务表，文件数据)
     */
    public static void initUserSubmeterDao() {
       /* UserInfo localUser = DaoFactory.getUserInfoDao().uniqueQuery(null, new WhereCondition[]{UserInfoDao.Properties.IsLastLogin.eq(true)});
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(),
                "CSI_ECS_DATABASE_" + CommonUtils.resolveAccountToDbSuffix(localUser.getLoginName()), null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        MyApplication.getInstance().setUserSubmeterDaoSession(daoSession);*/
    }

    /**
     * 检查文件名是否合法
     *
     * @param fileName
     * @return true可用 false不可用
     */
    public static boolean checkFileNameLegal(String fileName) {
        return !(fileName.startsWith(".") || fileName.endsWith(".")) && fileName.matches("[^/\\\\*?\":<>|]{1,255}")
                && !containsEmoji(fileName);
    }


    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    private static long tapTime;
    private static View view;

    /**
     * 是否双击
     *
     * @param v
     * @return
     */
    public static boolean isDoubleTap(View v) {
        if (view == v && System.currentTimeMillis() - tapTime < 300) {
            view = null;
            tapTime = 0;
            return true;
        } else {
            view = v;
            tapTime = System.currentTimeMillis();
            return false;
        }
    }

}