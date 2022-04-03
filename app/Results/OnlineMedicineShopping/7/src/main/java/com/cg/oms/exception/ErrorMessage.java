package com.cg.oms.exception;
 public class ErrorMessage {

 private  Integer errorCode;

 private  String errorInformation;


public void setErrorInformation(String errorInformation){
    this.errorInformation = errorInformation;
}


public String getErrorInformation(){
    return errorInformation;
}


public Integer getErrorCode(){
    return errorCode;
}


public void setErrorCode(Integer errorCode){
    this.errorCode = errorCode;
}


}