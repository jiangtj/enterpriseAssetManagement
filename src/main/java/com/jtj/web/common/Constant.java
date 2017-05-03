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

    public enum OperationType{
        ADD(1,"记录"),
        BORROW(2,"借"),
        RETURN(3,"还");

        private Integer id;
        private String name;
        OperationType(Integer id,String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }
    }

    public enum AssetStatus{
        NORMAL(1,"正常"),
        BORROW(2,"租借"),
        MAINTENANCE(3,"维修"),
        ABANDONED(4,"报废");

        private Integer id;
        private String name;
        AssetStatus(Integer id,String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }
    }

}
