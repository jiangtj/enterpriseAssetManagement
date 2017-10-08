package com.jtj.web.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成随机字符串工具类
 * 
 * @author melodymao
 * @version $Id: RandomStringUtils.java, v 0.1 2015年6月23日 下午1:06:50  Exp $
 */
public class RandomStringUtils {
    private static final String src_number    = "0123456789";
    private static final String src_lower     = "abcdefghijklmnopqrstuvwxyz";
    private static final String src_upper     = src_lower.toUpperCase();
    private static final String src_hex_lower = "0123456789abcdef";
    private static final String src_hex_upper = src_hex_lower.toUpperCase();
    private static final String esc_char      = "?";

    private static final Object locker = new Object();
    public static String get(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_number + src_upper;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String get(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number + src_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String get(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number + src_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getNum(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_number;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getNum(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getNum(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_number;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getHex(int size) {
        StringBuffer r = new StringBuffer(size);
        String src = src_hex_upper;
        for (int i = 0; i < size; i++) {
            r.append(getRandomChar(src));
        }
        return r.toString();
    }

    public static String getHex(String format) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_hex_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(esc_char)) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    public static String getHex(String format, char esc) {
        StringBuffer r = new StringBuffer(format.length());
        String src = src_hex_upper;
        for (int i = 0; i < format.length(); i++) {
            String curr = String.valueOf(format.charAt(i));
            if (curr.equalsIgnoreCase(String.valueOf(esc))) {
                r.append(getRandomChar(src));
            } else {
                r.append(curr);
            }
        }
        return r.toString();
    }

    private static final String getRandomChar(String src) {
        if (null == src || "".equals(src)) {
            return "";
        }
        return String.valueOf((src.charAt((int) (Math.random() * src.length()))));
    }

    /**
     * 唯一码生成器 返回值26位 数字字符串（yyyyMMddHHmmssSSS+11位随机数）
     *
     * @return
     */
    public static String getNextVal() {
        synchronized (locker) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            StringBuilder builder = new StringBuilder();
            builder.append(formatDate.format(new Date()));
            return builder.append(Math.round(Math.random() * 899999999 + 100000000))
                    .toString();
        }
    }

}
