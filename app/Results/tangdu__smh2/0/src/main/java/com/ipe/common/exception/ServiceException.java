package com.ipe.common.exception;
 public class ServiceException extends RuntimeException{

 private  int code;

public ServiceException(Exception e) {
    this.code = 100;
}public ServiceException(Exception e, ExceptionCode code) {
    this.code = code.getCode();
}
public int getCode(){
    return code;
}


}