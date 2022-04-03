package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
@Getter
@Builder
@RequiredArgsConstructor
@ToString
public class MessageFullResponse extends AbstractResponse{

@JsonProperty("id")
 private  long id;

@JsonProperty("time")
 private  Long time;

@JsonProperty("author_id")
 private  long authorId;

@JsonProperty("recipient_id")
 private  Long recipientId;

@JsonProperty("message_text")
 private  String messageText;

@JsonProperty("read_status")
 private  String readStatus;


}