package run.halo.app.exception;
 public class ImageFormatException extends BadRequestException{

public ImageFormatException(String message) {
    super(message);
}public ImageFormatException(String message, Throwable cause) {
    super(message, cause);
}
}