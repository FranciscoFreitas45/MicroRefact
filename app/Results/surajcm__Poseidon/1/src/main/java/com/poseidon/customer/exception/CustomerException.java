package com.poseidon.customer.exception;
 public class CustomerException extends Exception{

 public  String DATABASE_ERROR;

 public  long serialVersionUID;

 private  String exceptionType;

public CustomerException(final String exceptionType) {
    super();
    this.exceptionType = exceptionType;
}
public String getExceptionType(){
    return exceptionType;
}


}