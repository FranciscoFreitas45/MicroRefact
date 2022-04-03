package run.halo.app.exception;
 import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
public class AbstractHaloException extends RuntimeException{

 private  Object errorData;

public AbstractHaloException(String message) {
    super(message);
}public AbstractHaloException(String message, Throwable cause) {
    super(message, cause);
}
@Nullable
public Object getErrorData(){
    return errorData;
}


@NonNull
public HttpStatus getStatus()


@NonNull
public AbstractHaloException setErrorData(Object errorData){
    this.errorData = errorData;
    return this;
}


}