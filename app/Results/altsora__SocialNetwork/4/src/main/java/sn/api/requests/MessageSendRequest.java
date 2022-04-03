package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MessageSendRequest {

@JsonProperty("message_text")
 private  String messageText;


}