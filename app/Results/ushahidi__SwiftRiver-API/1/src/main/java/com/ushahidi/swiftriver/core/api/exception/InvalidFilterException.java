package com.ushahidi.swiftriver.core.api.exception;
 public class InvalidFilterException extends RuntimeException{

 private  long serialVersionUID;

public InvalidFilterException() {
    super();
}public InvalidFilterException(String message) {
    super(message);
}
}