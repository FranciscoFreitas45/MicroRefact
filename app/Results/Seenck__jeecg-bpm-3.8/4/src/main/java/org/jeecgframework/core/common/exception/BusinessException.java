package org.jeecgframework.core.common.exception;
 public class BusinessException extends RuntimeException{

 private  long serialVersionUID;

public BusinessException(String message) {
    super(message);
}public BusinessException(Throwable cause) {
    super(cause);
}public BusinessException(String message, Throwable cause) {
    super(message, cause);
}
}