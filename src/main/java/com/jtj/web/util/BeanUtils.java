package com.jtj.web.util;


import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * bean转换工具
 * Created by maokefeng on 2016/7/7.
 */
public class BeanUtils {

    /**
     * bean copy
     * @param map
     * @param obj
     */
    public  static <T> T beanCopy(Object source,Class<T> targetClass) {
        return beanCopy(source,targetClass,null);
    }

    /**
     * bean copy with converter
     * @param source
     * @param targetClass
     * @param converter
     * @param <T>
     * @return
     */
    public  static <T> T beanCopy(Object source, Class<T> targetClass, Converter converter) {
        BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, converter != null);
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
        }
        copier.copy(source,target,converter);
        return target;
    }

    /**
     * bean to map
     * @param obj
     * @return
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanMap m = BeanMap.create(obj);
        map.putAll(m);
        return map;
    }
}
