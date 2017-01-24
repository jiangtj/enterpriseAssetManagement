package com.jtj.web.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/22 21:31 End.
 */
public class GsonUtils {

    private static Gson gson;

    /**
     * 获取gson单例
     * @return gson
     */
    public static Gson getInstants(){
        if (gson == null){
            //个性化设置
            //gson = new GsonBuilder().create();
            //默认设置
            gson = new Gson();
        }
        return gson;
    }

    /**
     * 对象转换成json
     * @param object java 对象
     * @return json 字符串
     */
    public static String toJson(Object object){
        Gson gson= getInstants();
        return gson.toJson(object);
    }

    /**
     * json转换成对象
     * @param <T> 指定泛型
     * @param clazz 泛型类型
     * @param json json字符串
     * @return T 返回类型
     */
    public static <T> T fromJson(String json,Class<T> clazz){
        Gson gson = getInstants();
        return gson.fromJson(json,clazz);
    }

    /**
     * 获取指定节点的值
     * @param json json 字符串
     * @param key 关键字
     * @return value 值
     */
    public static String getValueOfKey(String json,String key){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonObject jsonObject = element.getAsJsonObject();
        return jsonObject.get(key).getAsString();
    }

}
