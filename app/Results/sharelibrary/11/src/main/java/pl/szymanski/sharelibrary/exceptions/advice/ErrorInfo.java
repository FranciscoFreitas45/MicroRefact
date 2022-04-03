package pl.szymanski.sharelibrary.exceptions.advice;
 import lombok.Value;
import java.time.LocalDateTime;
@Value
public class ErrorInfo {

 private LocalDateTime timestamp;

 private String message;


}