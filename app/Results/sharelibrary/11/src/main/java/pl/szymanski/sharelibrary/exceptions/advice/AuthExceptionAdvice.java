package pl.szymanski.sharelibrary.exceptions.advice;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
import pl.szymanski.sharelibrary.exceptions.auth.InvalidUsernameEmailOrPassword;
import java.time.LocalDateTime;
@ControllerAdvice
public class AuthExceptionAdvice {


@ExceptionHandler(InvalidUsernameEmailOrPassword.class)
public ResponseEntity<ErrorInfo> invalidUserNameEmailOrPassword(InvalidUsernameEmailOrPassword exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.FORBIDDEN);
}


@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ErrorInfo> badCredentialsExceptionHandler(BadCredentialsException ex){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), ExceptionMessages.INVALID_USERNAME_EMAIL_OR_PASSWORD);
    return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
}


}