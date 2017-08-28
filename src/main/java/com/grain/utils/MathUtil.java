package com.grain.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author anqi
 * @since 2014/7/21.
 */
public class MathUtil {
    public final static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(source.getBytes());
            byte[] md = md5.digest();
            char str[] = new char[md.length * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexChar[byte0 >>> 4 & 0xf];
                str[k++] = hexChar[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHashCode(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        String md5 = md5(source);
        BigInteger bi = new BigInteger(md5, 16);
        String s = bi.toString();
        return s.substring(0, 20);
    }
}
