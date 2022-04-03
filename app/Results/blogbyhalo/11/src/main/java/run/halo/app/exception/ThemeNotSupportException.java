package run.halo.app.exception;
 public class ThemeNotSupportException extends BadRequestException{

public ThemeNotSupportException(String message) {
    super(message);
}public ThemeNotSupportException(String message, Throwable cause) {
    super(message, cause);
}
}