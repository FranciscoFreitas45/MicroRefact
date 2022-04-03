package com.sobey.cmop.mvc.constant;
 import java.util.Map;
import com.google.common.collect.Maps;
public class StorageConstant {

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;


@Override
public Integer toInteger(){
    return this.code;
}


public String get(Integer code){
    return map.get(code);
}


@Override
public String toString(){
    return String.valueOf(this.code);
}


}