package com.cg.oms.exception;
 public class RoleNotFoundException extends Exception{

 private  long serialVersionUID;

/**
 * parameterized Constructor
 * @param message
 */
public RoleNotFoundException(String message) {
    super(message);
}
}