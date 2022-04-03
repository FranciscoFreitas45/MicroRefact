package org.gliderwiki.framework.exception;
 public class AuthenticationNotException extends RuntimeException{

 private  long serialVersionUID;

public AuthenticationNotException(String message, Throwable cause) {
    super(message, cause);
}public AuthenticationNotException(String message) {
    super(message);
}public AuthenticationNotException(Throwable cause) {
    super(cause);
}
}