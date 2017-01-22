package com.jtj.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 文件名工具类
 * 
 * @author melodymao
 * @version $Id: FileNameUtils.java, v 0.1 2015年6月23日 下午12:55:43  Exp $
 */
public class FileNameUtils {
    
 
    private static String getTimeString(Calendar calendar) {      
        StringBuffer sb = new StringBuffer();
        sb.append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)),2))
          .append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)),3));       
        return sb.toString();
    }  
     
    public static String getFileName(String time){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDateFormat(time));
        return getTimeString(calendar);
    }
     
    public static String getFileName(){
        Calendar calendar = new GregorianCalendar();
        return getTimeString(calendar);
    }
    
    /**
     * 获取文件名字的前缀
     * 
     * @param suffix
     * @return
     */
    public static String getFileNameWithSuffix(String suffix){
        String prefix = RandomStringUtils.get(4);
        String fileName = prefix + "_" + getFileName();
        StringBuilder builder = new StringBuilder();
        builder.append(fileName).append(suffix);
        return builder.toString();
    }
    
    private static String valueOfString(String str, int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len - str.length(); i++) {
            sb.append("0");
        }
        return (sb.length() == 0) ? (str) : (sb.toString() + str);
    }
     
    private static Date getDateFormat(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(time);
        } catch (ParseException e) {           
            e.printStackTrace();
        }  
        return null;
    }

}
