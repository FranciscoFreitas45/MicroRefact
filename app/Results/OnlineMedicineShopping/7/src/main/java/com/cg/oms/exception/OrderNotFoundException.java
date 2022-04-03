package com.cg.oms.exception;
 public class OrderNotFoundException extends Exception{

 private  long serialVersionUID;

/**
 * Parameterized Constructor
 * @param message
 */
public OrderNotFoundException(String message) {
    super(message);
}
}