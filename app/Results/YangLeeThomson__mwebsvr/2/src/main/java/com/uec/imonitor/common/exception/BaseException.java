package com.uec.imonitor.common.exception;
 import com.uec.imonitor.common.log.ErrorCodeManager;
public class BaseException extends Exception{

 private  long serialVersionUID;

 private  String errorCode;

 private  String message;

 private  Object[] params;

 private  Throwable cause;

public BaseException() {
    super();
}public BaseException(Throwable cause) {
    super(cause);
    this.cause = cause;
}public BaseException(String errorCode) {
    super(ErrorCodeManager.getText(errorCode));
    this.message = ErrorCodeManager.getText(errorCode);
    this.errorCode = errorCode;
}// public BaseException(String message){
// super(message);
// this.message = message;
// }
public BaseException(String errorCode, Throwable cause) {
    super(ErrorCodeManager.getText(errorCode), cause);
    this.message = ErrorCodeManager.getText(errorCode);
    this.errorCode = errorCode;
    this.cause = cause;
}public BaseException(String errorCode, Object[] params) {
    super(ErrorCodeManager.getText(errorCode, params));
    this.message = ErrorCodeManager.getText(errorCode, params);
    this.errorCode = errorCode;
    this.params = params;
}public BaseException(String errorCode, Object[] params, Throwable cause) {
    super(ErrorCodeManager.getText(errorCode, params), cause);
    this.message = ErrorCodeManager.getText(errorCode, params);
    this.errorCode = errorCode;
    this.params = params;
    this.cause = cause;
}
public String getErrorCode(){
    return errorCode;
}


public String getMessage(){
    return message;
}


public void setCause(Throwable cause){
    this.cause = cause;
}


public void setMessage(String message){
    this.message = message;
}


public Throwable getCause(){
    return cause;
}


public void setErrorCode(String errorCode){
    this.errorCode = errorCode;
}


public Object[] getParams(){
    return params;
}


public void setParams(Object[] params){
    this.params = params;
}


}