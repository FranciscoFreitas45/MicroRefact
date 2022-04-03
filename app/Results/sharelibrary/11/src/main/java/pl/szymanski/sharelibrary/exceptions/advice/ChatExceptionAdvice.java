package pl.szymanski.sharelibrary.exceptions.advice;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.szymanski.sharelibrary.exceptions.chat.RoomNotExist;
import java.time.LocalDateTime;
@ControllerAdvice
public class ChatExceptionAdvice {


@ExceptionHandler(RoomNotExist.class)
public ResponseEntity<ErrorInfo> chatRoomNotExists(RoomNotExist exception){
    ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
    return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
}


}