package com.byr.warehouse.myexception;
 public class StoreException extends Exception{

public StoreException(String message) {
    super(message);
}public StoreException(String message, Throwable throwable) {
    super(message, throwable);
}
}