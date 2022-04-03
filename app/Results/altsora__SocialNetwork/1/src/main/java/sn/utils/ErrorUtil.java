package sn.utils;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sn.api.response.AbstractResponse;
import sn.api.response.ErrorResponse;
import sn.api.response.ServiceResponse;
public class ErrorUtil {

private ErrorUtil() {
}
public ResponseEntity<ServiceResponse<AbstractResponse>> badRequest(String errorDescription){
    ErrorResponse errorResponse = ErrorResponse.builder().error("Bad request").errorDescription(errorDescription).build();
    return ResponseEntity.badRequest().body(new ServiceResponse<>(errorResponse));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> unauthorized(){
    ErrorResponse errorResponse = ErrorResponse.builder().error("Invalid request").errorDescription("Person not authorized").build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ServiceResponse<>(errorResponse));
}


}