package com.gp.cricket.config.security.resource;
 public class AuthenticationException extends RuntimeException{

public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
}
}