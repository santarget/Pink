package com.ssy.pink.utils;

import android.text.TextUtils;
import android.util.Log;

import com.ssy.pink.common.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by tys on 2017/4/4.
 */

public class LogUtil {
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;
    private static int logLevel = LogUtil.INFO;
    private static String logPath = Constants.LOG_PATH;

    public static int v(String tag, String msg) {
        Log.v(tag, msg);
        return writeLogInfo2File(tag, msg, LogUtil.VERBOSE);
    }

    public static int v(String tag, String msg, Throwable tr) {
        Log.v(tag, msg, tr);
        return writeLogInfo2File(tag, msg, tr, LogUtil.VERBOSE);
    }

    public static int d(String tag, String msg) {
        Log.d(tag, msg);
        return writeLogInfo2File(tag, msg, LogUtil.DEBUG);
    }

    public static int d(String tag, String msg, Throwable tr) {
        Log.d(tag, msg, tr);
        return writeLogInfo2File(tag, msg, tr, LogUtil.DEBUG);
    }

    public static int i(String tag, String msg) {
        Log.i(tag, msg);
        return writeLogInfo2File(tag, msg, LogUtil.INFO);
    }

    public static int i(String tag, String msg, Throwable tr) {
        Log.i(tag, msg, tr);
        return writeLogInfo2File(tag, msg, tr, LogUtil.INFO);
    }

    public static int w(String tag, String msg) {
        Log.w(tag, msg);
        return writeLogInfo2File(tag, msg, LogUtil.WARN);
    }

    public static int w(String tag, String msg, Throwable tr) {
        Log.w(tag, msg, tr);
        return writeLogInfo2File(tag, msg, tr, LogUtil.WARN);
    }

    public static int e(String tag, String msg) {
        Log.e(tag, msg);
        return writeLogInfo2File(tag, msg, LogUtil.ERROR);
    }

    public static int e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
        return writeLogInfo2File(tag, msg, tr, LogUtil.ERROR);
    }

    private static int writeLogInfo2File(String tag, String msg, int logLevel) {
        if (LogUtil.logLevel <= logLevel) {
            return writeLogInfo(tag, msg, null);
        } else {
            return 0;
        }
    }

    private static int writeLogInfo2File(String tag, String msg, Throwable tr, int logLevel) {
        if (LogUtil.logLevel <= logLevel) {
            return writeLogInfo(tag, msg, tr);
        } else {
            return 0;
        }
    }

    private static int writeLogInfo(String tag, String msg, Throwable ex) {
        if (!TextUtils.isEmpty(logPath)) {
            File logFolder = new File(logPath);
            if (!logFolder.exists()) {
                logFolder.mkdirs();
            }
        }
        File logFile = new File(logPath, CommonUtils.formatData("yyyyMMdd", System.currentTimeMillis()) + ".log");
        FileWriter fw = null;
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuilder ret = new StringBuilder();
        try {
            fw = new FileWriter(logFile, true);
            if (null == ex) {
                ret.append(CommonUtils.formatData("yyyy-MM-dd HH:mm", System.currentTimeMillis())).append(" ");
                ret.append("TAG:").append(tag).append("_").append(msg).append("\n");
                fw.write(ret.toString());
            } else {
                Writer writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                ex.printStackTrace(printWriter);
                Throwable cause = ex.getCause();
                if (cause != null) {
                    cause.printStackTrace(printWriter);
                }
                printWriter.close();
                ret.append(CommonUtils.formatData("yyyy-MM-dd HH:mm", System.currentTimeMillis())).append(" ");
                ret.append("TAG:").append(tag).append("_").append(msg);
                ret.append(writer.toString()).append("\n");
                fw.write(ret.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret.length();
    }

    static void setLogLevel(int logLevel) {
        LogUtil.logLevel = logLevel;
    }

    static int getLogLevel() {
        return logLevel;
    }

    static void setLogPath(String logPath) {
        LogUtil.logPath = logPath;
    }

    static String getLogPath() {
        return logPath;
    }
}
