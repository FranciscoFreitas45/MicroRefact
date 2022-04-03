package pl.szymanski.sharelibrary.exceptions.advice;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.szymanski.sharelibrary.exceptions.categories.CategoryNotExist;
import java.time.LocalDateTime;
@ControllerAdvice
public class CategoryExceptionAdvice {


@ExceptionHandler(CategoryNotExist.class)
public ResponseEntity<ErrorInfo> categoryNotExists(CategoryNotExist exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.FOUND);
}


}