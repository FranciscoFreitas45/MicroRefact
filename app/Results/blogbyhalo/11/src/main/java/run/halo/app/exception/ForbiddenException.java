package run.halo.app.exception;
 import org.springframework.http.HttpStatus;
public class ForbiddenException extends AbstractHaloException{

public ForbiddenException(String message) {
    super(message);
}public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
}
@Override
public HttpStatus getStatus(){
    return HttpStatus.FORBIDDEN;
}


}