package com.jtj.web.common.utils;


import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

    //bean copy
    public  static <T> T fromBean(Object source,Class<T> targetClass) {
        return fromBean(source,targetClass,null);
    }

    //bean copy with converter
    public  static <T> T fromBean(Object source, Class<T> targetClass, Converter converter) {
        BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, converter != null);
        T target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception ignore) {}
        copier.copy(source,target,converter);
        return target;
    }

    //bean to map
    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        BeanMap m = BeanMap.create(obj);
        map.putAll(m);
        return map;
    }
}
