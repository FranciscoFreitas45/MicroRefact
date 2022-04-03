package run.halo.app.exception;
 public class ThemeConfigMissingException extends BadRequestException{

public ThemeConfigMissingException(String message) {
    super(message);
}public ThemeConfigMissingException(String message, Throwable cause) {
    super(message, cause);
}
}