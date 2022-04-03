package com.zis.youzan.response;
 import com.alibaba.fastjson.annotation.JSONField;
public class ErrorResponse {

@JSONField(name = "code")
 private  String code;

@JSONField(name = "msg")
 private  String msg;


public String getMsg(){
    return msg;
}


public void setCode(String code){
    this.code = code;
}


@Override
public String toString(){
    return "rep [code=" + code + ", msg=" + msg + "]";
}


public String getCode(){
    return code;
}


public void setMsg(String msg){
    this.msg = msg;
}


}