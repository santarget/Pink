package com.ssy.pink.common;

/**
 * Created by Tys on 2017/1/28.
 */
public class ConstantType {

    public static final byte FILE = 1;

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

}
