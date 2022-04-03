package com.cg.oms.exception;
 public class AddressNotFoundException extends Exception{

 private  long serialVersionUID;

/**
 * Super class for Exception
 * @param message
 */
public AddressNotFoundException(String message) {
    super(message);
}
}