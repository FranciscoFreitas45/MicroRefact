package run.halo.app.exception;
 public class FrequentAccessException extends BadRequestException{

public FrequentAccessException(String message) {
    super(message);
}public FrequentAccessException(String message, Throwable cause) {
    super(message, cause);
}
}