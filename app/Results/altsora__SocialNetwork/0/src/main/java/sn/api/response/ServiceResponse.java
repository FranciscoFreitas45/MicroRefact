package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {

 private  String error;

 private  Number timestamp;

 private  Integer total;

 private  Integer offset;

 private  Integer perPage;

 private  T data;

public ServiceResponse() {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
}public ServiceResponse(T data) {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    this.data = data;
}public ServiceResponse(String error, T data) {
    this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
    this.error = error;
    this.data = data;
}public ServiceResponse(int total, int offset, int perPage, T data) {
    this.total = total;
    this.offset = offset;
    this.perPage = perPage;
    this.data = data;
}
}