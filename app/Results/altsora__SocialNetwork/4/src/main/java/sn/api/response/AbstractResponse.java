package sn.api.response;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class AbstractResponse {


public ResponseEntity<ServiceResponse<AbstractResponse>> createErrorResponse(String error,String errorDescription){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>(new ErrorResponse(error, errorDescription)));
}


}