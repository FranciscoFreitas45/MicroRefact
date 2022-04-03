package com.kingen.util;
 import java.util.List;
import java.util.Map;
import org.apache.commons.collections.KeyValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
public class FastJsonUtil {

 private  SerializeConfig config;

 private  SerializerFeature[] features;


public Object beanToJson(KeyValue keyvalue){
    String textJson = JSON.toJSONString(keyvalue);
    Object objectJson = JSON.parse(textJson);
    return objectJson;
}


@SuppressWarnings("rawtypes")
public Map stringToCollect(String s){
    Map m = JSONObject.parseObject(s);
    return m;
}


public String toJSONString(Object object){
    return JSON.toJSONString(object, config, features);
}


public String toJSONNoFeatures(Object object){
    return JSON.toJSONString(object, config);
}


public T toBean(String text,Class<T> clazz){
    return JSON.parseObject(text, clazz);
}


public String collectToString(Map m){
    String s = JSONObject.toJSONString(m);
    return s;
}


public Object[] toArray(String text,Class<T> clazz){
    return JSON.parseArray(text, clazz).toArray();
}


public List<T> toList(String text,Class<T> clazz){
    return JSON.parseArray(text, clazz);
}


public Object textToJson(String text){
    Object objectJson = JSON.parse(text);
    return objectJson;
}


}