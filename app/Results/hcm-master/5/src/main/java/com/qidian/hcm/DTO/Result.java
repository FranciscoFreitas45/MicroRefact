package com.qidian.hcm.DTO;
 import com.alibaba.fastjson.JSON;
public class Result {

 private  String code;

 private  String message;

 private  T data;


public String getMessage(){
    return message;
}


public String getCode(){
    return code;
}


public T getData(){
    return data;
}


}