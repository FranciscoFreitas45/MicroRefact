package com.kingen.util.mapper;
 import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
public class JsonMapper2 {

 private  Logger logger;

 private  ObjectMapper mapper;

public JsonMapper2() {
    this(null);
}public JsonMapper2(Include include) {
    mapper = new ObjectMapper();
    // 设置输出时包含属性的风格
    if (include != null) {
        mapper.setSerializationInclusion(include);
    }
    // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
}
public String toJson(Object object){
    try {
        return mapper.writeValueAsString(object);
    } catch (IOException e) {
        logger.warn("write to json string error:" + object, e);
        return null;
    }
}


public ObjectMapper getMapper(){
    return mapper;
}


public JsonMapper nonEmptyMapper(){
    return new JsonMapper(Include.NON_EMPTY);
}


public JsonMapper nonDefaultMapper(){
    return new JsonMapper(Include.NON_DEFAULT);
}


public JavaType contructCollectionType(Class<? extends Collection> collectionClass,Class<?> elementClass){
    return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
}


public String toJsonP(String functionName,Object object){
    return toJson(new JSONPObject(functionName, object));
}


public T fromJson(String jsonString,JavaType javaType){
    if (StringUtils.isEmpty(jsonString)) {
        return null;
    }
    try {
        return (T) mapper.readValue(jsonString, javaType);
    } catch (IOException e) {
        logger.warn("parse json string error:" + jsonString, e);
        return null;
    }
}


public void update(String jsonString,Object object){
    try {
        mapper.readerForUpdating(object).readValue(jsonString);
    } catch (JsonProcessingException e) {
        logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
    } catch (IOException e) {
        logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
    }
}


public void enableEnumUseToString(){
    mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
}


public JavaType contructMapType(Class<? extends Map> mapClass,Class<?> keyClass,Class<?> valueClass){
    return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
}


}