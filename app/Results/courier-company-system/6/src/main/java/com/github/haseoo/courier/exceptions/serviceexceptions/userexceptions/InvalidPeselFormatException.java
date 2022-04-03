package com.github.haseoo.courier.exceptions.serviceexceptions.userexceptions;
 import com.github.haseoo.courier.exceptions.BusinessLogicException;
import com.github.haseoo.courier.exceptions.ExceptionMessages.INVALID_PESEL_FORMAT_EXCEPTION;
public class InvalidPeselFormatException extends BusinessLogicException{

public InvalidPeselFormatException() {
    super(INVALID_PESEL_FORMAT_EXCEPTION);
}
}