package run.halo.app.exception;
 public class NotInstallException extends BadRequestException{

public NotInstallException(String message) {
    super(message);
}public NotInstallException(String message, Throwable cause) {
    super(message, cause);
}
}