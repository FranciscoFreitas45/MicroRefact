package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import sn.model.NotificationType;
import sn.model.Person;
import javax.persistence;
import java.time.LocalDateTime;
@Getter
@Builder
public class NotificationResponse extends AbstractResponse{

@JsonProperty("id")
 private  long id;

@JsonProperty("type_id")
 private  long typeId;

@JsonProperty("sent_time")
 private  long sentTime;

@JsonProperty("entity_id")
 private  long entityId;

@JsonProperty("info")
 private  String info;


}