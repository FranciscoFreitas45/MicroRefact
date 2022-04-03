package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sn.model.enums.NotificationTypeCode;
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotificationSettingRequest {

@JsonProperty("notification_type")
 private  NotificationTypeCode notificationType;

 private  boolean enable;


}