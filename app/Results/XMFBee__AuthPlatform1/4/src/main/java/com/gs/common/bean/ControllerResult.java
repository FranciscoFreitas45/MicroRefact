package com.gs.common.bean;
 public class ControllerResult {

 public  int SUCCESS_CODE;

 public  int FAIL_CODE;

 public  int NOT_LOGIN_CODE;

 public  int NOT_ROLE_CODE;

 public  int IS_OWNER_CODE;

 public  int NOT_PHONE_CODE;

 public  String SUCCESS_RESULT;

 public  String FAIL_RESULT;

 public  String NOT_LOGIN_RESULT;

 public  String NOT_ROLE_RESULT;

 public  String IS_OWNER_RESULT;

 public  String NOT_PHONE_RESULT;

 private  int code;

 private  String result;

 private  String message;

public ControllerResult() {
}public ControllerResult(int code, String result, String message) {
    this.code = code;
    this.result = result;
    this.message = message;
}
public ControllerResult getSuccessResult(String message){
    return new ControllerResult(ControllerResult.SUCCESS_CODE, ControllerResult.SUCCESS_RESULT, message);
}


public ControllerResult getNotPhoneCodeResult(String message){
    return new ControllerResult(ControllerResult.NOT_PHONE_CODE, ControllerResult.NOT_PHONE_RESULT, message);
}


public void setResult(String result){
    this.result = result;
}


public void setCode(int code){
    this.code = code;
}


public ControllerResult getIsOwnerResult(String message){
    return new ControllerResult(ControllerResult.IS_OWNER_CODE, ControllerResult.IS_OWNER_RESULT, message);
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public ControllerResult getFailResult(String message){
    return new ControllerResult(ControllerResult.FAIL_CODE, ControllerResult.FAIL_RESULT, message);
}


public String getResult(){
    return result;
}


@Override
public String toString(){
    return "ControllerResult{" + "code='" + code + '\'' + ", result='" + result + '\'' + ", message='" + message + '\'' + '}';
}


public int getCode(){
    return code;
}


public ControllerResult getNotLoginResult(String message){
    return new ControllerResult(ControllerResult.NOT_LOGIN_CODE, ControllerResult.NOT_LOGIN_RESULT, message);
}


public ControllerResult getNotRoleResult(String message){
    return new ControllerResult(ControllerResult.NOT_ROLE_CODE, ControllerResult.NOT_ROLE_RESULT, message);
}


}