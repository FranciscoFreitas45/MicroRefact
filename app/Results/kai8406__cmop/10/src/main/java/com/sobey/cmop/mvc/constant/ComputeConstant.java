package com.sobey.cmop.mvc.constant;
 import java.util.Map;
import com.google.common.collect.Maps;
public class ComputeConstant {

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<Integer,String> OS_BIT_MAP;

 public  Map<String,String> OS_BIT_STRING_MAP;

 public  Map<Integer,String> OS_TYPE_MAP;

 public  Map<String,String> OS_TYPE_STRING_MAP;


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