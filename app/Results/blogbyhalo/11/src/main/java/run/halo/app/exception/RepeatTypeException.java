package run.halo.app.exception;
 public class RepeatTypeException extends ServiceException{

public RepeatTypeException(String message) {
    super(message);
}public RepeatTypeException(String message, Throwable cause) {
    super(message, cause);
}
}