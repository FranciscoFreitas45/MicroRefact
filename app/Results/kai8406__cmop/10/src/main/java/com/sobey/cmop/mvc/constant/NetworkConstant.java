package com.sobey.cmop.mvc.constant;
 import java.util.Map;
import com.google.common.collect.Maps;
public class NetworkConstant {

 public  String SEPARATE_PORT_SYMBOL;

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<String,String> map;

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<Boolean,String> map;

 public  Map<String,String> mapKeyStr;

 private  boolean code;

 public  Map<Integer,String> map;

 public  Map<String,String> mapKeyStr;

 private  int code;

 public  Map<String,String> map;

 public  Map<Boolean,String> map;

 public  Map<String,String> mapKeyStr;

 private  boolean code;


@Override
public Integer toInteger(){
    return this.code ? 1 : 0;
}


public Boolean toBoolean(){
    return this.code;
}


public String get(Boolean code){
    return map.get(code);
}


@Override
public String toString(){
    return String.valueOf(this.code);
}


}