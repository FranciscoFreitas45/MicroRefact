package run.halo.app.exception;
 public class ThemeNotFoundException extends BadRequestException{

public ThemeNotFoundException(String message) {
    super(message);
}public ThemeNotFoundException(String message, Throwable cause) {
    super(message, cause);
}
}