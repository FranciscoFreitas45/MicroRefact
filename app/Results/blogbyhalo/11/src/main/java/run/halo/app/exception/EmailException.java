package run.halo.app.exception;
 public class EmailException extends ServiceException{

public EmailException(String message) {
    super(message);
}public EmailException(String message, Throwable cause) {
    super(message, cause);
}
}