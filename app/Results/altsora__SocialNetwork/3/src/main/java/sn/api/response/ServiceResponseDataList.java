package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import sn.utils.TimeUtil;
import java.time.LocalDateTime;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponseDataList {

 private  String error;

 private  Long timestamp;

 private  int total;

 private  int offset;

 private  int perPage;

 private  List<T> data;

private ServiceResponseDataList() {
    this.timestamp = TimeUtil.getTimestampFromLocalDateTime(LocalDateTime.now(TimeUtil.TIME_ZONE));
}public ServiceResponseDataList(String error) {
    this();
    this.error = error;
}public ServiceResponseDataList(List<T> data) {
    this(data.size(), 0, data.size(), data);
}public ServiceResponseDataList(int total, int offset, int perPage, List<T> data) {
    this();
    this.data = data;
    this.total = total;
    this.offset = offset;
    this.perPage = perPage;
}
}