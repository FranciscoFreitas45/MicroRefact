package run.halo.app.exception;
 public class PropertyFormatException extends BadRequestException{

public PropertyFormatException(String message) {
    super(message);
}public PropertyFormatException(String message, Throwable cause) {
    super(message, cause);
}
}