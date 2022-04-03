package com.qidian.hcm.common.utils;
 import com.alibaba.fastjson.JSON;
public class Result {

 private  String code;

 private  String message;

 private  T data;


public Result setData(T data){
    this.data = data;
    return this;
}


public Result setCode(String code){
    this.code = code;
    return this;
}


public String getMessage(){
    return message;
}


@Override
public String toString(){
    return JSON.toJSONString(this);
}


public Result setMessage(String message){
    this.message = message;
    return this;
}


public String getCode(){
    return code;
}


public T getData(){
    return data;
}


}