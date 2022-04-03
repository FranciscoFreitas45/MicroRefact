package org.live.common.exception;
 public class CustomException extends RuntimeException{

 private  int errorCode;

/**
 * 构造方法
 */
public CustomException() {
}public CustomException(int errorCode) {
    super();
    this.errorCode = errorCode;
}public CustomException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
}public CustomException(int errorCode, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
}public CustomException(String message, Throwable cause) {
    super(message, cause);
}public CustomException(int errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
}public CustomException(String message) {
    super(message);
}public CustomException(Throwable cause) {
    super(cause);
}
public int getErrorCode(){
    return errorCode;
}


public void setErrorCode(int errorCode){
    this.errorCode = errorCode;
}


}