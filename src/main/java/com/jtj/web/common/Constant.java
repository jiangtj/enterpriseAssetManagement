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
    public final static String SESSION_ROLE = "role";

    public static List<Dictionary> dictionaries = new ArrayList<>();

    public enum OperationType{
        ADD(1,"记录"),
        BORROW(2,"借"),
        RETURN(3,"还"),
        LOSE(4,"丢失"),
        MAINTENANCE(5,"维修"),
        ABANDONED(6,"作废"),
        PAN(7,"盘点");

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

    public enum StockTakeItemStatus{
        HANDLING(1,"待处理"),
        NORMAL(2,"正常"),
        ABNORMAL(3,"异常");

        private Integer id;
        private String name;
        StockTakeItemStatus(Integer id,String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }
    }

}
