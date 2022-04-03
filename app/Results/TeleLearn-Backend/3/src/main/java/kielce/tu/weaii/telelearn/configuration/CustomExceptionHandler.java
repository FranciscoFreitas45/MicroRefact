package kielce.tu.weaii.telelearn.configuration;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends DefaultHandlerExceptionResolver{

 private LocalDateTime timestamp;

 private String message;


@ExceptionHandler(BusinessLogicException.class)
public ResponseEntity<ErrorResponse> businessLogicExceptionHandler(BusinessLogicException ex){
    ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
    log.error(ex.getMessage());
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<ErrorResponse> illegalArgumentExceptionExceptionHandler(HttpMessageNotReadableException ex){
    Throwable cause = ex.getCause().getCause();
    ErrorResponse errors;
    if (cause instanceof IllegalArgumentException) {
        errors = new ErrorResponse(LocalDateTime.now(), cause.getMessage());
    } else {
        errors = new ErrorResponse(LocalDateTime.now(), "Niepoprawne dane wejściowe");
        Writer buffer = new StringWriter();
        PrintWriter pw = new PrintWriter(buffer);
        ex.printStackTrace(pw);
        log.warn(buffer.toString());
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(NotFoundException.class)
public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(NotFoundException ex){
    ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
    log.error(ex.getMessage());
    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> radiationException(MethodArgumentNotValidException ex){
    String errorMessage = getFieldErrorMessages(ex);
    return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), errorMessage), HttpStatus.BAD_REQUEST);
}


@ExceptionHandler(DisabledException.class)
public ResponseEntity<ErrorResponse> badCredentialsExceptionHandler(DisabledException ex){
    ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), "Konto zostało zablokowane");
    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
}


@ExceptionHandler(AuthorizationException.class)
public ResponseEntity<ErrorResponse> authorizationExceptionHandler(AuthorizationException ex){
    ErrorResponse errors = new ErrorResponse(LocalDateTime.now(), ex.getMessage());
    log.error(String.format("User %s has not permission to resource %s with id %s", ex.getUserId(), ex.getResourceName(), ex.getResourceId()));
    return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
}


public String getFieldErrorMessages(MethodArgumentNotValidException ex){
    return ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(". ", "", "."));
}


}