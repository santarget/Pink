package com.ssy.pink.common;


import com.ssy.pink.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigProp {
    /*为了方便引用，用public修饰，但不允许修改*/
    public static String serverUrl = "";
    public static String certName = "";
    public static boolean verifyCert;


    public static void loadConfigProp() {
        InputStream in = null;
        try {
            in = MyApplication.getInstance().getAssets().open("config.properties");
            Properties properties = new Properties();
            properties.load(in);
            serverUrl = properties.getProperty("SERVER_URL", "");
            certName = properties.getProperty("CERT_NAME", "");
            verifyCert = Boolean.valueOf(properties.getProperty("VERIFY_CERT", "false"));
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
