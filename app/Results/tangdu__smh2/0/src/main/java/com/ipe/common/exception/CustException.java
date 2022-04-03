package com.ipe.common.exception;
 public class CustException extends Exception{

 private  int code;

public CustException(Exception e) {
    this.code = 200;
}public CustException(Exception e, ExceptionCode code) {
    this.code = code.getCode();
}
public int getCode(){
    return code;
}


}