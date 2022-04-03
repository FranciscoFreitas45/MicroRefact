package com.github.haseoo.courier.configuration.CustomGlobalExceptionHandler;
 import com.github.haseoo.courier.exceptions.AuthException;
import com.github.haseoo.courier.exceptions.BusinessLogicException;
import com.github.haseoo.courier.exceptions.ResourceNotFoundException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
@Value
public class ErrorResponse {

 private  LocalDateTime timestamp;

 private  String message;


}