package switchtwentytwenty.project.exception;
 import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler{


@ExceptionHandler
public ResponseEntity<String> elementNotFoundException(ElementNotFoundException exception){
    HttpStatus status = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(exception.getMessage(), status);
}


public String prepareErrorJSON(HttpStatus status,Exception exception){
    JSONObject errorJSON = new JSONObject();
    try {
        errorJSON.put("status", status.value());
        errorJSON.put("error", status.getReasonPhrase());
        errorJSON.put("message", exception.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return errorJSON.toString();
}


@ExceptionHandler
public ResponseEntity<String> invalidDateException(InvalidDateException exception){
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    return new ResponseEntity<>(prepareErrorJSON(status, exception), status);
}


}