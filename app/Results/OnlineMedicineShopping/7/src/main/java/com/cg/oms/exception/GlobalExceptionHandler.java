package com.cg.oms.exception;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(MedicineNotFoundException.class)
public ResponseEntity<Object> handleMedicineNotFoundException(MedicineNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
}


@ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleException(Exception e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(RoleNotFoundException.class)
public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
}


@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
}


@ExceptionHandler(OrderNotFoundException.class)
public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
}


@ExceptionHandler(CartNotFoundException.class)
public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    // 
    return new ResponseEntity<>(error, HttpStatus.OK);
}


@ExceptionHandler(AddressNotFoundException.class)
public ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException e){
    ErrorMessage error = new ErrorMessage();
    error.setErrorCode(HttpStatus.NOT_FOUND.value());
    error.setErrorInformation(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.OK);
}


}