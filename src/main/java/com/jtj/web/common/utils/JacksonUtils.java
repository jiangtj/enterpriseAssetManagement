package com.jtj.web.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/20 21:54 End.
 */
public class JacksonUtils {

    private static ObjectMapper objectMapper;
    private static Logger logger =  LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * @return ObjectMapper
     */
    private static ObjectMapper getObjectMapper(){
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    /**
     * json转换成对象
     * @param <T> 指定泛型
     * @param clazz 泛型类型
     * @param json json字符串
     * @return T 返回类型
     */
    public static <T> T fromJson(String json,Class<T> clazz){
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    /**
     * 对象转换成json
     * @param object java 对象
     * @return json 字符串
     */
    public static String toJson(Object object) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}
