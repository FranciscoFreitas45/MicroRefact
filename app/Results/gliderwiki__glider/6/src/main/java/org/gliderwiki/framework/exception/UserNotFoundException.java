package org.gliderwiki.framework.exception;
 public class UserNotFoundException extends RuntimeException{

 private  long serialVersionUID;

// 생성자
public UserNotFoundException(String message) {
    super(message);
}public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
}public UserNotFoundException(Throwable cause) {
    super(cause);
}
}