package sn.api.response;
 import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SimpleServiceResponse {

 private  String error;

 private  long timestamp;

 private  T data;

public SimpleServiceResponse() {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
}public SimpleServiceResponse(T data) {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    this.data = data;
}public SimpleServiceResponse(String error, T data) {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    this.error = error;
    this.data = data;
}
}