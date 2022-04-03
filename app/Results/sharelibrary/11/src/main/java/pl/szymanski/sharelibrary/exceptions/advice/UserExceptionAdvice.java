package pl.szymanski.sharelibrary.exceptions.advice;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.szymanski.sharelibrary.exceptions.users.EmailAlreadyExist;
import pl.szymanski.sharelibrary.exceptions.users.InvalidEmailAddress;
import pl.szymanski.sharelibrary.exceptions.users.UserNotFoundById;
import pl.szymanski.sharelibrary.exceptions.users.UsernameAlreadyExists;
import java.time.LocalDateTime;
@ControllerAdvice
public class UserExceptionAdvice {


@ExceptionHandler(InvalidEmailAddress.class)
public ResponseEntity<ErrorInfo> invalidEmailAddress(InvalidEmailAddress exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(UserNotFoundById.class)
public ResponseEntity<ErrorInfo> userNotFoundById(UserNotFoundById exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
}


@ExceptionHandler(EmailAlreadyExist.class)
public ResponseEntity<ErrorInfo> emailAlreadyExists(EmailAlreadyExist exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.FOUND);
}


@ExceptionHandler(UsernameAlreadyExists.class)
public ResponseEntity<ErrorInfo> usernameAlreadyExists(UsernameAlreadyExists exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.FOUND);
}


}