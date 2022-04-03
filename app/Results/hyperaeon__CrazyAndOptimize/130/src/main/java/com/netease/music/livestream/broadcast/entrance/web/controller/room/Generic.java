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
public class Generic {

 public  String value;

public Generic() {
    super();
}public Generic(String value) {
    super();
    this.value = value;
}
public String getValue(){
    return value;
}


public void setValue(String value){
    this.value = value;
}


@Override
public String toString(){
    // TODO Auto-generated method stub
    return this.value;
}


}