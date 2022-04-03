package com.cg.hbm.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlingException {
	/**
	 * 
	 * @param ex
	 * @return ErrorMapper
	 */

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorMapper> exceptionHotelNotFound(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.NOT_FOUND);
    }
    /**
     * 
     * @param ex
     * @return ErrorMapper
     */

    @ExceptionHandler(BookingDetailsNotFoundException.class)
    public ResponseEntity<ErrorMapper> exceptionHandler(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.BAD_REQUEST);
    }
    /**
     * 
     * @param ex
     * @return ErrorMapper
     */
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorMapper> exceptionPaymentNotFound(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.NOT_FOUND);
    }
    
    /**
	 * 
	 * @param ex
	 * @return ErrorMapper
	 */

    @ExceptionHandler(RoomDetailsNotFoundException.class)
    public ResponseEntity<ErrorMapper> exceptionRoomDetailsNotFound(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.NOT_FOUND);
    }
    /**
	 * 
	 * @param ex
	 * @return ErrorMapper
	 */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMapper> exceptionTransactionNotFound(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.NOT_FOUND);
    }
    
    
    /**
	 * 
	 * @param ex
	 * @return ErrorMapper
	 */

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMapper> exceptionUserNotFound(Exception ex) {
        ErrorMapper error = new ErrorMapper();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ErrorMapper>(error, HttpStatus.NOT_FOUND);
    }

}


