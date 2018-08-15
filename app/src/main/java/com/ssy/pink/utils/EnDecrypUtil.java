package com.ssy.pink.utils;

import android.os.Build;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Tys on 2017/1/27.
 */

public class EnDecrypUtil {

    private final static String HEX = "0123456789abcdef";
    private static byte[] SEED = new byte[]{10,2,3,4,2,5,2,3,4,1};

    private static String BASE64decode(String b64e) {
        String res = null;
        try {
            byte[] bytes = Base64.decode(b64e, Base64.DEFAULT);
            res = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static String BASE64Encode(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /**
     * byte[] 加密
     *
     * @param bytes   要加密的byte[]
     * @param encrypt 加密方式(sha-256, md5)
     * @param len byt[]长度
     * @return 加密后的字符串（16进制）
     */
    public static String byteEncrypt(byte[] bytes,  int len, String encrypt) {
        String re = "";
        try {
            MessageDigest md = MessageDigest.getInstance(encrypt);
            md.update(bytes, 0, len);
            re = byte2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re;
    }

    public static String AesEncrypt(String context) {
        try {
            byte[] enCodeFormat = getRawkey();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(context.getBytes());
            return byte2Hex(result);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] getRawkey() throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom sr;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
             sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        }else{
             sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(SEED);
        keygen.init(256, sr);
        SecretKey secretKey = keygen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return enCodeFormat;
    }

    public static String AesDecrypt(String content)  {
        try {
            byte[] enCodeFormat = getRawkey();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(hex2Byte(content));
            String aa = new String(result);
            return aa;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2Hex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    private static byte[] hex2Byte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
          result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

}
