package com.zis.oldapi.response;
 public class BaseApiResponse {

 private  Integer code;

 private  String msg;

 public  Integer CODE_SUCCESS;

 public  Integer CODE_ILLEGAL_ARGUMENT;

 public  Integer CODE_INNER_ERROR;


public String getMsg(){
    return msg;
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


}