package com.jtj.web.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * gson工具类
 * 
 * @author melodymao
 * @version $Id: GsonUtils.java, v 0.1 2015年6月18日 上午11:18:40  Exp $
 */
public class GsonUtils {

    private static Gson gson;
    private static GsonBuilder builder;
    private static Gson getInstants() {
        if (null == gson) {
            builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
    
    /**
     * 对象转换成json
     * 
     * @param bean
     * @param type
     * @return
     */
    public static String toJson(Object bean,Type type){
        Gson gson= getInstants();
        return gson.toJson(bean, type);
    }
    
    /**
     * 转换成json对象
     * @Title: fromJson
     * @param <T>
     * @param json
     * @param type
     * @return T 返回类型
     * @throws：
     */
    public static <T> T fromJson(String json,Type type){
        Gson gson = getInstants();
        return gson.fromJson(json, type);
    }
    
    /**
     *  转换成json对象
     * @Title: fromJson
     * @param <T>
     * @param json
     * @param classOfT
     * @return T 返回类型
     * @throws：
     */
    public static <T> T fromJson(String json,Class<T> classOfT){
        Gson gson= getInstants();
        return gson.fromJson(json, classOfT);
    }
    
    /**
     * 获取指定节点的值
     * 
     * @param data
     * @return
     */
    public static String getValueOfKey(String data,String key){
     // 创建一个JsonParser  
        JsonParser parser = new JsonParser();
        JsonElement jsonEl = parser.parse(data);
        JsonObject jsonObj = null;
        jsonObj = jsonEl.getAsJsonObject();//转换成Json对象  
        return jsonObj.get(key).getAsString();//key节点 
    }
    
    /**
     * Null Adapter
     * 
     * @author melodymao
     * @version $Id: GsonUtils.java, v 0.1 2015年6月18日 上午11:10:32  Exp $
     */
 /*   @SuppressWarnings("unchecked")
    private static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        @SuppressWarnings("hiding")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    private static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }*/
   
}
