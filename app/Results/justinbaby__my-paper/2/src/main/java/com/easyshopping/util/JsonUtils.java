package com.easyshopping.util;
 import java.io.IOException;
import java.io.Writer;
import org.springframework.util.Assert;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonUtils {

 private  ObjectMapper mapper;

/**
 * 不可实例化
 */
private JsonUtils() {
}
public String toJson(Object value){
    try {
        return mapper.writeValueAsString(value);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


public T toObject(String json,JavaType javaType){
    Assert.hasText(json);
    Assert.notNull(javaType);
    try {
        return mapper.readValue(json, javaType);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


public void writeValue(Writer writer,Object value){
    try {
        mapper.writeValue(writer, value);
    } catch (JsonGenerationException e) {
        e.printStackTrace();
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}