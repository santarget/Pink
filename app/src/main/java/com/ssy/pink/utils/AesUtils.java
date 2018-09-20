package com.ssy.pink.utils;

import android.os.Build;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ssy
 * @date 2018/3/13
 */
public class AesUtils {
    //    private static final String CipherMode = "AES/ECB/PKCS5Padding";使用ECB加密，不需要设置IV，但是不安全
//    private static final String CipherMode = "AES/CFB/NoPadding";//使用CFB加密，需要设置IV
    private static final String CipherMode = "AES/CBC/PKCS5Padding";
    private final static String HEX = "0123456789abcdef";
    private static byte[] SEED = new byte[]{10, 2, 3, 4, 2, 5, 2, 3, 4, 1};

    public static String encrypt(String context) {
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

    public static String decrypt(String content) {
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

    private static byte[] getRawkey() throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom sr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(SEED);
        keygen.init(256, sr);
        SecretKey secretKey = keygen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return enCodeFormat;
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

    private static byte[] hex2Byte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
