package com.cg.oms.exception;
 public class CartNotFoundException extends Exception{

 private  long serialVersionUID;

public CartNotFoundException(String message) {
    super(message);
}
}