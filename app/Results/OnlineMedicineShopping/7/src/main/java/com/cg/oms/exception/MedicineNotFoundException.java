package com.cg.oms.exception;
 public class MedicineNotFoundException extends Exception{

 private  long serialVersionUID;

/**
 * Paramterized Constructor
 * @param message
 */
public MedicineNotFoundException(String message) {
    super(message);
}
}