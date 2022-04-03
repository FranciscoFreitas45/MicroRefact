package com.meli.weather.commons.exception;
 public class NotFoundException extends RuntimeException{

public NotFoundException(String message) {
    super(message);
}public NotFoundException(String message, Throwable cause) {
    super(message, cause);
}
}