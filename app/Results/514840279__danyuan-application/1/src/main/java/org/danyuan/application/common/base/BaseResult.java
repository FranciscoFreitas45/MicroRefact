package org.danyuan.application.common.base;
 public class BaseResult {

 private  Integer code;

 private  String msg;

 private  T data;


public String getMsg(){
    return msg;
}


public void setData(T data){
    this.data = data;
}


public void setCode(Integer code){
    this.code = code;
}


public Integer getCode(){
    return code;
}


public void setMsg(String msg){
    this.msg = msg;
}


public T getData(){
    return data;
}


}