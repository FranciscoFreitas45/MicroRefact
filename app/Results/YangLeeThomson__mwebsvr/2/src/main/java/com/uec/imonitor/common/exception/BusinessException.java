package com.uec.imonitor.common.exception;
 public class BusinessException extends BaseException{

 private  long serialVersionUID;

public BusinessException(String errorCode) {
    super(errorCode);
// TODO Auto-generated constructor stub
}public BusinessException(String errorCode, Object[] params) {
    super(errorCode, params);
}
}