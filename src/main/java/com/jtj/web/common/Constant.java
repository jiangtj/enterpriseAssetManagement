package com.jtj.web.common;

import com.jtj.web.entity.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
public class Constant {

    public final static String SESSION_USER = "user";
    public final static String SESSION_LOGIN_TIME = "loginTime";
    public final static String SESSION_PREVIOUS_PASSWORD = "previousPassword";

    public final static String SESSION_PERMISSION = "permission";
    public final static String SESSION_POINT = "point";

    public static List<Dictionary> dictionaries = new ArrayList<>();

}
