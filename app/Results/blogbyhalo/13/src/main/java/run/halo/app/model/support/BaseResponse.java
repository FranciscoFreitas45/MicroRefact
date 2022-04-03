package run.halo.app.model.support;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

 private  Integer status;

 private  String message;

 private  String devMessage;

 private  T data;

public BaseResponse(Integer status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
}
public BaseResponse<T> ok(T data){
    return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
}


}