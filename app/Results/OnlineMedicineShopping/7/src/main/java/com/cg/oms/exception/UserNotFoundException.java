package com.cg.oms.exception;
 public class UserNotFoundException extends Exception{

 private  long serialVersionUID;

/**
 * Parameterized constructor
 *
 * @param message
 */
public UserNotFoundException(String message) {
    super(message);
}
}