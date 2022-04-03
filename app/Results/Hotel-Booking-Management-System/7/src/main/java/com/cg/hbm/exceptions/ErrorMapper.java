package com.cg.hbm.exceptions;
 public class ErrorMapper {

 private  int errorCode;

 private  String errorMessage;


public void setErrorMessage(String errorMessage){
    this.errorMessage = errorMessage;
}


public int getErrorCode(){
    return errorCode;
}


public void setErrorCode(int errorCode){
    this.errorCode = errorCode;
}


public String getErrorMessage(){
    return errorMessage;
}


}