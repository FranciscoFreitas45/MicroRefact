package com.uec.imonitor.common.exception;
 public class RequestParamException extends BaseException{

 private  long serialVersionUID;

 static  String errorcode;

public RequestParamException() {
    super(errorcode);
}public RequestParamException(Object[] params) {
    super(errorcode, params);
}
}