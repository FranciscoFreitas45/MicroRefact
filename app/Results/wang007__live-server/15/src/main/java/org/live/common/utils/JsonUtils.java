package org.live.common.utils;
 import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
public class JsonUtils {

 public  ObjectMapper JACKSON;


public String toJson(Object obj){
    try {
        return JACKSON.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
    }
}


public Map<String,Object> convertBean(Object bean) throws Exception{
    Class<?> type = bean.getClass();
    Map<String, Object> returnMap = new HashMap<String, Object>();
    /**
     * 内省
     */
    BeanInfo beanInfo = Introspector.getBeanInfo(type);
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
    for (int i = 0; i < propertyDescriptors.length; i++) {
        PropertyDescriptor descriptor = propertyDescriptors[i];
        String propertyName = descriptor.getName();
        if (!propertyName.equals("class")) {
            Method readMethod = descriptor.getReadMethod();
            Object result = readMethod.invoke(bean, new Object[0]);
            if (result != null) {
                returnMap.put(propertyName, result);
            } else {
                returnMap.put(propertyName, "");
            }
        }
    }
    return returnMap;
}


public T fromJson(String src,Class<T> srcType){
    try {
        return JACKSON.readValue(src, srcType);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


public T convertMap2Object(Map<String,Object> map,Class<T> clazz){
    return JACKSON.convertValue(map, clazz);
}


}