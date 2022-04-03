package com.netease.music.livestream.broadcast.entrance.web.controller.room;
 import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.msgpack.jackson.dataformat.MessagePackFactory;
public class DeSerializable {

 private  ObjectMapper objectMapper;


public TypeFactory getTypeFactory(){
    return objectMapper.getTypeFactory();
}


public JavaType createJavaTypeFromType(Type type){
    if (type instanceof ParameterizedType) {
        ParameterizedType ptype = (ParameterizedType) type;
        Type[] argTypes = ptype.getActualTypeArguments();
        JavaType[] tmpTypes = new JavaType[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            tmpTypes[i] = createJavaTypeFromType(argTypes[i]);
        }
        String typeString = ptype.getRawType().toString();
        System.out.println(typeString);
        int index = typeString.indexOf(" ");
        if (index > 0) {
            typeString = typeString.substring(index).trim();
        }
        Class<?> rawClass = Class.forName(typeString);
        return getTypeFactory().constructParametrizedType(rawClass, rawClass, tmpTypes);
    } else {
        return getTypeFactory().constructType(type);
    }
}


public List<List<Generic>> test(){
    return null;
}


@SuppressWarnings("unchecked")
public void main(String[] args){
    Method method = DeSerializable.class.getMethod("test");
    Type returnType = method.getGenericReturnType();
    System.out.println(returnType.getTypeName());
    if (returnType instanceof ParameterizedType) {
        Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
        for (Type type : types) {
            System.out.println("泛型类型: " + type);
        }
    }
    String jsonStr = "[[{\"value\":\"123\"}]]";
    JavaType javaType = createJavaTypeFromType(returnType);
    List<Generic> result = objectMapper.readValue(jsonStr, javaType);
    System.out.println(result);
}


}