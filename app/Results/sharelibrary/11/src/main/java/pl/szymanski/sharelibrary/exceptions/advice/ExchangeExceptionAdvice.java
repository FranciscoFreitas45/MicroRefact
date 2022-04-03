package pl.szymanski.sharelibrary.exceptions.advice;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.szymanski.sharelibrary.exceptions.exchanges.ExchangeNotExist;
import java.time.LocalDateTime;
@ControllerAdvice
public class ExchangeExceptionAdvice {


@ExceptionHandler(ExchangeNotExist.class)
public ResponseEntity<ErrorInfo> exchangeNotExists(ExchangeNotExist exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
}


@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ErrorInfo> incorrectArgumentValue(IllegalArgumentException exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
}


}