package com.example.steam.utils;
 import java.util.Stack;
public class ResultMsg {

 public  ResultMsg SUCCESS;

 public  ResultMsg LOGIN_SUCCESS;

 public  ResultMsg REGISTER_SUCCESS;

 public  ResultMsg SPIKE_SUCCESS;

 public  ResultMsg NO_EMAIL;

 public  ResultMsg PASS_ERROR;

 public  ResultMsg HAD_Login;

 public  ResultMsg HAD_REGISTER;

 public  ResultMsg CODE_ERROR;

 public  ResultMsg CODE_INVALID;

 public  ResultMsg STOCK_IS_NULL;

 public  ResultMsg NO_LOGIN;

 public  ResultMsg NO_GAME;

 public  ResultMsg SPIKE_ING;

 public  ResultMsg SPIKE_REPEAT;

 public  ResultMsg LABEL_PRESENCE;

 public  ResultMsg SPIKE_END;

 public  ResultMsg SPIKE_NO_START;

 public  ResultMsg USER_NO;

 public  ResultMsg IMAGE_OVERSIZE;

 public  ResultMsg IMAGE_TYPE_ERROR;

 public  ResultMsg SPIKE_LIMIT_ERROR;

 public  ResultMsg SPIKE_PATH_ERROR;

 public  ResultMsg GAME_HAD;

 public  ResultMsg ADMIN_NO;

 public  ResultMsg PASS_NOT_EQUAL;

 private  String code;

 private  Object msg;

private ResultMsg(String code, Object msg) {
    this.code = code;
    this.msg = msg;
}
public ResultMsg SUCCESS(Object object){
    return new ResultMsg("200", object);
}


public Object getMsg(){
    return msg;
}


public void setCode(String code){
    this.code = code;
}


public ResultMsg FAILURE(Object object){
    return new ResultMsg("500", object);
}


public String getCode(){
    return code;
}


public void setMsg(Object msg){
    this.msg = msg;
}


}