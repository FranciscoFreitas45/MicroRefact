package com.cg.sprint.exceptions;
 @SuppressWarnings("serial")
public class AccountNotFoundException extends Exception{

public AccountNotFoundException() {
    super();
}public AccountNotFoundException(String message) {
    super(message);
}
}