package sn.api.response.DialogResponse.DialogData;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import sn.model.Message;
import sn.model.enums.MessageStatus;
import java.util.List;
import java.util.Set;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LastMessage {

 private  long id;

 private  long time;

@JsonProperty("author_id")
 private  long authorId;

@JsonProperty("recipient_id")
 private  long recipientId;

@JsonProperty("message_text")
 private  String messageText;

@JsonProperty("read_status")
 private  MessageStatus messageStatus;


}