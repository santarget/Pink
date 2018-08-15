package com.ssy.pink.common;


import com.ssy.pink.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigProp {
    /*为了方便引用，用public修饰，但不允许修改*/
    public static String serverUrl = "";
    public static String certName = "";
    public static boolean showDomain;
    public static boolean verifyCert;
    public static String crashReportUrl;//配置了就走压缩zip包上传逻辑，为空则维持log文件，且最多保留最新的3个
    public static boolean showDownloadPath;


    public static void loadConfigProp() {
        InputStream in = null;
        try {
            in = MyApplication.getInstance().getAssets().open("config.properties");
            Properties properties = new Properties();
            properties.load(in);
            serverUrl = properties.getProperty("CSI_ECS_server_URL", "");
            certName = properties.getProperty("CERT_NAME", "");
            crashReportUrl = properties.getProperty("CRASH_REPORT_URL", "");
            showDomain = "true".equalsIgnoreCase(properties.getProperty("SHOW_DOMAIN", ""));
            verifyCert = "true".equalsIgnoreCase(properties.getProperty("VERIFY_CERT", ""));
            showDownloadPath = "true".equalsIgnoreCase(properties.getProperty("SHOW_DOWNLOAD_PATH", ""));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
