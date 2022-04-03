package run.halo.app.exception;
 public class MissingPropertyException extends BadRequestException{

public MissingPropertyException(String message) {
    super(message);
}public MissingPropertyException(String message, Throwable cause) {
    super(message, cause);
}
}