package com.ssy.pink.common;

/**
 * Created by Tys on 2017/1/28.
 */
public class ConstantType {
    /*0表示文件夹，1表示文件，2表示版本文件*/
    public static final byte FOLDER = 0;
    public static final byte FILE = 1;
    public static final byte FILE_VERSION = 2;

    public static final String IMG = "IMAGE_TYPE";
    public static final String WORD = "WORD_TYPE";
    public static final String PPT = "PPT_TYPE";
    public static final String EXCEL = "EXCEL_TYPE";
    public static final String PDF = "PDF_TYPE";
    public static final String RAR = "RAR_TYPE";
    public static final String ZIP = "ZIP_TYPE";
    public static final String MUSIC = "MUSIC_TYPE";
    public static final String VIDEO = "VIDEO_TYPE";
    public static final String TEXT = "TXT_TYPE";
    public static final String APK = "APK_TYPE";
    public static final String OTHER = "OTHER";

    public static final String[][] FileMimeType = {
            {IMG, ".png", ".jpg", ".gif", ".bmp", ".jpeg", ".pg"},
            {WORD, ".doc", ".docx"},
            {PPT, ".ppt", ".pptx"},
            {EXCEL, ".xls", ".xlsx"},
            {PDF, ".pdf"},
            {RAR, ".rar"},
            {ZIP, ".zip"},
            {MUSIC, ".mp3"},
            {VIDEO, ".avi", ".rmvb", ".mp4", ".mpeg4", ".gpp", ".3gpp2", ".mp2t", ".MP2T", ".mpeg",
                    ".rmvb", ".real", ".mov", ".wma", ".wmv"},
            {TEXT, ".txt", ".log", ".xml"},
            {APK, ".apk"}
    };
    /*====================== 收藏 ====================*/
    public static final String FAVORITE_CONTAINER = "containor";
    public static final String FAVORITE_NODE_FILE = "file";
    public static final String FAVORITE_NODE_FOLDER = "folder";

    //parent
    public static final int FAVORITE_ID_TEAMSPACE = 5;
    public static final int FAVORITE_ID_SHARELINK = 4;
    public static final int FAVORITE_ID_SHARE = 3;
    public static final int FAVORITE_ID_MYFILES = 2;
    //type
    public static final String FAVORITE_TYPE_MY_SPACE = "myspace";
    public static final String FAVORITE_TYPE_TEAM_SPACE = "teamspace";
    public static final String FAVORITE_TYPE_SHARE = "share";
    public static final String FAVORITE_TYPE_LINK = "link";

}
